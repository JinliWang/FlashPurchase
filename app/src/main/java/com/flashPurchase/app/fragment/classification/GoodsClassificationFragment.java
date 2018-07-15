package com.flashPurchase.app.fragment.classification;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.adapter.GoodByIdAdapter;
import com.flashPurchase.app.adapter.ShoppingLeftAdapter;
import com.flashPurchase.app.event.HomeEvent;
import com.flashPurchase.app.model.bean.GoodClassification;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;

/**
 * 商品分类
 * Created by 10951 on 2018/4/11.
 */

public class GoodsClassificationFragment extends BaseFragment implements AdapterView.OnItemClickListener {


    @BindView(R.id.lv_left)
    ListView mLvLeft;
    @BindView(R.id.refresh_layout_left)
    RefreshLayout mRlLeft;
    @BindView(R.id.recycler_right)
    ListView mRecyclerRight;
    @BindView(R.id.refresh_layout_right)
    RefreshLayout mRlRight;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;

    private WebSocketClient mWebSocketClient;

    private ShoppingLeftAdapter mLeftAdapter;
    private GoodByIdAdapter mGoodByIdAdapter;
    private boolean isLoadMore = false;

    private int pageIndex = 0;
    private int p = 0;
    private String mType;
    private GoodClassification mClassification;
    private List<GoodClassification.ResponseBean.GoodsCategoriesBean> mCategories;
    private List<GoodClassification.ResponseBean.GoodsBean> mGoods;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_good_classification2;
    }

    @Override
    protected void initView(View view) {
        initTitle("商品分类");
        mIvLeft.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
        mCategories = new ArrayList<>();
        mLeftAdapter = new ShoppingLeftAdapter(mCategories);
        mLvLeft.setAdapter(mLeftAdapter);
        mLvLeft.setItemChecked(0, true);
        mLvLeft.setOnItemClickListener(this);

        mGoods = new ArrayList<>();
        mGoodByIdAdapter = new GoodByIdAdapter(mGoods);
        mRecyclerRight.setAdapter(mGoodByIdAdapter);
        mGoodByIdAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mGoods.get(position).getId());
                bundle.putString("time", mGoods.get(position).getTime());
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });
        mRlRight.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                isLoadMore = false;
                mGoodByIdAdapter.clearData();
                pageIndex = 0;
                getGoods(p);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                isLoadMore = true;
                getGoods(p);
            }
        });
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        super.loadData(savedInstanceState);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {

                }

                @Override
                public void onMessage(String message) {
                    LogUtil.d(message);
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else if (message.contains("goods-goodsList")) {
                        Gson gson = new Gson();
                        mClassification = gson.fromJson(message, GoodClassification.class);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    } else if (message.contains("goods-goodsByCid")) {
                        Gson gson = new Gson();
                        mClassification = gson.fromJson(message, GoodClassification.class);
                        Message msg = new Message();
                        msg.what = 2;
                        handler.sendMessage(msg);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setPageNum(String.valueOf(pageIndex));
                    parameter.setPageSize("10");
                    more.setUrlMapping("goods-goodsList");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.toString());
                    break;
                case 1:
                    mCategories = mClassification.getResponse().getGoodsCategories();
                    Collections.sort(mCategories, new Comparator<GoodClassification.ResponseBean.GoodsCategoriesBean>() {

                        @Override
                        public int compare(GoodClassification.ResponseBean.GoodsCategoriesBean o1, GoodClassification.ResponseBean.GoodsCategoriesBean o2) {
                            // 升序排列
                            if (Integer.parseInt(o1.getId()) > Integer.parseInt(o2.getId())) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mLeftAdapter.addData(mCategories);
                    mRlLeft.setData(mCategories);
                    if (TextUtils.isEmpty(mType)) {
                        getGoods(0);
                    }
                    if (!TextUtils.isEmpty(mType) && mType.equals("phone")) {
                        for (int i = 0; i < mCategories.size(); i++) {
                            if (mCategories.get(i).getName().equals("手机专区")) {
                                mGoodByIdAdapter.clearData();
                                getGoods(i);
                                mLvLeft.setItemChecked(i, true);
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(mType) && mType.equals("computer")) {
                        for (int i = 0; i < mCategories.size(); i++) {
                            if (mCategories.get(i).getName().equals("电脑平板")) {
                                mGoodByIdAdapter.clearData();
                                getGoods(i);
                                mLvLeft.setItemChecked(i, true);
                            }
                        }
                    }
//                    mPhoneListAdapter.addData(mHomeBean.getResponse().getPhoneGoods());
                    break;
                case 2:
                    mGoodByIdAdapter.addData(mClassification.getResponse().getGoods());
                    mRlRight.setData(mClassification.getResponse().getGoods());
                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mGoodByIdAdapter.clearData();
        p = i;
        getGoods(i);
    }

    private void getGoods(int position) {
        MyRequset myRequset = new MyRequset();
        MyRequset.Parameter parameter1 = new MyRequset.Parameter();
        parameter1.setPageNum(String.valueOf(pageIndex));
        parameter1.setPageSize("10");
        parameter1.setCategoryId(mCategories.get(position).getId());
        myRequset.setUrlMapping("goods-goodsByCid");
        myRequset.setParameter(parameter1);
        mWebSocketClient.send(myRequset.getGoodsById());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HomeEvent event) {
        mType = event.getType();
        if (mType.equals("phone")) {
            if (mCategories != null && mCategories.size() > 0) {
                for (int i = 0; i < mCategories.size(); i++) {
                    if (mCategories.get(i).getName().equals("手机专区")) {
                        mGoodByIdAdapter.clearData();
                        getGoods(i);
                        mLvLeft.setItemChecked(i, true);
                    }
                }
            } else {
                MyRequset more = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setPageNum(String.valueOf(pageIndex));
                parameter.setPageSize("10");
                more.setUrlMapping("goods-goodsList");
                more.setParameter(parameter);
                mWebSocketClient.send(more.toString());
            }
        }
        if (event.getType().equals("computer")) {
            if (mCategories != null && mCategories.size() > 0) {
                for (int i = 0; i < mCategories.size(); i++) {
                    if (mCategories.get(i).getName().equals("电脑平板")) {
                        mGoodByIdAdapter.clearData();
                        getGoods(i);
                        mLvLeft.setItemChecked(i, true);
                    }
                }
            } else {
                MyRequset more = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setPageNum(String.valueOf(pageIndex));
                parameter.setPageSize("10");
                more.setUrlMapping("goods-goodsList");
                more.setParameter(parameter);
                mWebSocketClient.send(more.toString());
            }

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
