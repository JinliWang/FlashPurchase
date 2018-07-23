package com.flashPurchase.app.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.app.library.view.ScrollGridView;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.adapter.AucingDownAdapter;
import com.flashPurchase.app.adapter.CollectDownAdapter;
import com.flashPurchase.app.adapter.RecommendAdapter;
import com.flashPurchase.app.adapter.RecommendDownAdapter;
import com.flashPurchase.app.event.ChangeTabEvent;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
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
import java.util.List;

import butterknife.BindView;

/**
 * Created by 王金礼 on 2018/7/22.
 */

public class RecommendFragment3 extends BaseFragment implements AdapterView.OnItemClickListener {


    @BindView(R.id.list)
    ScrollGridView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private RecommendMoreResponse mResponse1;
    private RecommendMoreResponse mResponse2;
    private RecommendMoreResponse mResponse3;
    private RecommendDownAdapter mAdapter1;
    private AucingDownAdapter mAdapter2;
    private CollectDownAdapter mAdapter3;
    private int position;
    private int pageIndex = 1;
    private List<RecommendMoreResponse.ResponseBean.GoodsBean> mGoodsBeanList;
    private List<RecommendMoreResponse.ResponseBean.GoodsBean> mGoodsBeanList2;
    private List<RecommendMoreResponse.ResponseBean.GoodsBean> mGoodsBeanList3;
    private WebSocketClient mWebSocketClient;

    public static RecommendFragment3 getIntance(int positon) {
        RecommendFragment3 fragment = new RecommendFragment3();
        Bundle bundle = new Bundle();
        bundle.putInt("position", positon);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_list_down;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        mGoodsBeanList = new ArrayList<>();
        mGoodsBeanList2 = new ArrayList<>();
        mGoodsBeanList3 = new ArrayList<>();
        mAdapter1 = new RecommendDownAdapter(mGoodsBeanList);
        mAdapter2 = new AucingDownAdapter(mGoodsBeanList2);
        mAdapter3 = new CollectDownAdapter(mGoodsBeanList3);
        mList.setOnItemClickListener(this);
        switch (position) {
            case 0:
                mList.setAdapter(mAdapter1);
                mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                    @Override
                    public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                        super.onLoadMore(refreshLayout);
                        pageIndex++;
                        initRequest();
                    }
                });
                break;
            case 1:
//                clearAdapter();
                mList.setAdapter(mAdapter2);
                mRefreshLayout.setEnableLoadmore(false);
                break;
            case 2:
//                clearAdapter();
                mList.setAdapter(mAdapter3);
                mRefreshLayout.setEnableLoadmore(false);
                break;
        }
        mRefreshLayout.setEnableRefresh(false);

    }

    private void clearAdapter() {
//        if (mAdapter2 != null) {
//            mAdapter2.clearDatas();
//        }
        if (mAdapter1 != null) {
            mAdapter1.clearData();
        }
//        if (mAdapter3 != null) {
//            mAdapter3.clearDatas();
//        }
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        clearAdapter();
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://39.104.102.255:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
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
                    } else {
                        Gson gson = new Gson();
                        if (position == 0 && message.contains("goods-recommen")) {
                            mResponse1 = gson.fromJson(message, RecommendMoreResponse.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                        if (position == 1 && message.contains("goods-getMyAucIng")) {
                            mResponse2 = gson.fromJson(message, RecommendMoreResponse.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                        if (position == 2 && message.contains("collect-myCollect")) {
                            mResponse3 = gson.fromJson(message, RecommendMoreResponse.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }

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
                    initRequest();
                    break;
                case 1:
                    switch (position) {
                        case 0:
                            if (mAdapter2 != null) {
                                mAdapter2.clearData();
                            }
                            if (mAdapter3 != null) {
                                mAdapter3.clearData();
                            }
                            mAdapter1.addData(mResponse1.getResponse().getGoods());
                            mRefreshLayout.setData(mResponse1.getResponse().getGoods(), mAdapter1);
                            break;
                        case 1:
                            if (mAdapter1 != null) {
                                mAdapter1.clearData();
                            }
                            if (mAdapter3 != null) {
                                mAdapter3.clearData();
                            }
                            mAdapter2.refreshData(mResponse2.getResponse().getGoods());
                            mRefreshLayout.setData(mResponse2.getResponse().getGoods(), mAdapter2);
                            break;
                        case 2:
                            if (mAdapter2 != null) {
                                mAdapter2.clearData();
                            }
                            if (mAdapter1 != null) {
                                mAdapter1.clearData();
                            }
                            mAdapter3.refreshData(mResponse3.getResponse().getGoods());
                            mRefreshLayout.setData(mResponse3.getResponse().getGoods(), mAdapter3);
                            break;
                    }
                    break;
            }
        }
    };

    private void initRequest() {
        MyRequset more = new MyRequset();
        MyRequset.Parameter parameter = new MyRequset.Parameter();
        switch (position) {
            case 0:
                parameter.setPageNum(String.valueOf(pageIndex));
                parameter.setPageSize("10");
                more.setUrlMapping("goods-recommen");
                more.setParameter(parameter);
                mWebSocketClient.send(more.toString());
                break;
            case 1:
                parameter.setToken(SpManager.getToken());
                more.setUrlMapping("goods-getMyAucIng");
                more.setParameter(parameter);
                mWebSocketClient.send(more.myCollect());
                break;
            case 2:
                parameter.setToken(SpManager.getToken());
                more.setUrlMapping("collect-myCollect");
                more.setParameter(parameter);
                mWebSocketClient.send(more.myCollect());
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ChangeTabEvent event) {
        position = event.getP();
//       initAdapter();
        initRequest();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        if (position == 0) {
            bundle.putString("goodsid", mAdapter1.getAllDatas().get(i).getId());
            bundle.putString("time", mAdapter1.getAllDatas().get(i).getTime());
            if (!TextUtils.isEmpty(mAdapter1.getAllDatas().get(i).getStatus()) && mAdapter1.getAllDatas().get(i).getStatus().equals("2")) {
                bundle.putString("isnext", "1");
            } else {
                bundle.putString("isnext", "0");
            }
        }
        if (position == 1) {
            bundle.putString("goodsid", mAdapter2.getAllDatas().get(i).getId());
            bundle.putString("time", mAdapter2.getAllDatas().get(i).getTime());
            if (!TextUtils.isEmpty(mAdapter2.getAllDatas().get(i).getStatus()) && mAdapter2.getAllDatas().get(i).getStatus().equals("2")) {
                bundle.putString("isnext", "1");
            } else {
                bundle.putString("isnext", "0");
            }
        }
        if (position == 2) {
            bundle.putString("goodsid", mAdapter3.getAllDatas().get(i).getGoodsId());
            bundle.putString("time", mAdapter3.getAllDatas().get(i).getTime());
            if (!TextUtils.isEmpty(mAdapter3.getAllDatas().get(i).getStatus()) && mAdapter3.getAllDatas().get(i).getStatus().equals("2")) {
                bundle.putString("isnext", "1");
            } else {
                bundle.putString("isnext", "0");
            }
        }

        startActivity(GoodsDetailActivity.class, bundle);
    }
}
