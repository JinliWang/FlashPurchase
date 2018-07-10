package com.flashPurchase.app.fragment.classification;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.GoodByIdAdapter;
import com.flashPurchase.app.adapter.ShoppingLeftAdapter;
import com.flashPurchase.app.model.Dynamics;
import com.flashPurchase.app.model.bean.GoodClassification;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;

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

    private WebSocketClient mWebSocketClient;

    private ShoppingLeftAdapter mLeftAdapter;
    private GoodByIdAdapter mGoodByIdAdapter;

    private int pageIndex = 0;
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
        mCategories = new ArrayList<>();
        mLeftAdapter = new ShoppingLeftAdapter(mCategories);
        mLvLeft.setAdapter(mLeftAdapter);
        mLvLeft.setItemChecked(0, true);
        mLvLeft.setOnItemClickListener(this);

        mGoods = new ArrayList<>();
        mGoodByIdAdapter = new GoodByIdAdapter(mGoods);
        mRecyclerRight.setAdapter(mGoodByIdAdapter);
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        super.loadData(savedInstanceState);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=1"), new Draft_17()) {
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
                            // 按照学生的年龄进行升序排列
                            if (Integer.parseInt(o1.getId()) > Integer.parseInt(o2.getId())) {
                                return 1;
                            }
                            return -1;
                        }
                    });
                    mLeftAdapter.addData(mCategories);
                    mRlLeft.setData(mCategories);
                    MyRequset myRequset = new MyRequset();
                    MyRequset.Parameter parameter1 = new MyRequset.Parameter();
                    parameter1.setPageNum(String.valueOf(pageIndex));
                    parameter1.setPageSize("10");
                    parameter1.setCategoryId(mCategories.get(0).getId());
                    myRequset.setUrlMapping("goods-goodsByCid");
                    myRequset.setParameter(parameter1);
                    mWebSocketClient.send(myRequset.getGoodsById());
//                    mPhoneListAdapter.addData(mHomeBean.getResponse().getPhoneGoods());
                    break;
                case 2:
                    mGoods = mClassification.getResponse().getGoods();
                    mGoodByIdAdapter.addData(mGoods);
                    mRlRight.setData(mGoods);
                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mGoodByIdAdapter.clearData();
        MyRequset myRequset = new MyRequset();
        MyRequset.Parameter parameter1 = new MyRequset.Parameter();
        parameter1.setPageNum(String.valueOf(pageIndex));
        parameter1.setPageSize("10");
        parameter1.setCategoryId(mCategories.get(i).getId());
        myRequset.setUrlMapping("goods-goodsByCid");
        myRequset.setParameter(parameter1);
        mWebSocketClient.send(myRequset.getGoodsById());
    }
}
