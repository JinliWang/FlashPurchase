package com.flashPurchase.app.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.library.base.BaseFragment;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.adapter.HomeListAdapter;
import com.flashPurchase.app.adapter.MyFavoriteAdapter;
import com.flashPurchase.app.adapter.RecommendAdapter;
import com.flashPurchase.app.event.ChangeTabEvent;
import com.flashPurchase.app.event.RefreshGoodsEvent;
import com.flashPurchase.app.model.bean.MyOrder;
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

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/8.
 */

public class Recommend2Fragment extends BaseFragment implements BaseRecyclerAdapter.OnItemClickListener {
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private RecommendAdapter mAdapter;
    private HomeListAdapter mMyAucing;
    private MyFavoriteAdapter mMyFavoriteAdapter;
    private int pageIndex = 1;
    private int position;
    private RecommendMoreResponse mHomeBean;
    private MyOrder mMyOrder;

    public static Recommend2Fragment getIntance(int positon) {
        Recommend2Fragment fragment = new Recommend2Fragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", positon);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = getArguments();
        position = bundle.getInt("position");
        EventBus.getDefault().register(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mList.setLayoutManager(gridLayoutManager);
        mMyFavoriteAdapter = new MyFavoriteAdapter();
        mAdapter = new RecommendAdapter();
        mMyAucing = new HomeListAdapter();
        if (position == 0) {
            mList.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(this);
        } else if (position == 1) {
            mList.setAdapter(mMyFavoriteAdapter);
            mMyFavoriteAdapter.setOnItemClickListener(this);
            mRefreshLayout.setEnableLoadmore(false);
        } else if (position == 2) {
            mList.setAdapter(mMyAucing);
            mMyAucing.setOnItemClickListener(this);
            mRefreshLayout.setEnableLoadmore(false);
        }
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                initRequest();
            }
        });
    }

    private void initRequest() {
        if (position == 0) {
            MyRequset more = new MyRequset();
            MyRequset.Parameter parameter = new MyRequset.Parameter();
            parameter.setPageNum(String.valueOf(pageIndex));
            parameter.setPageSize("10");
            more.setUrlMapping("goods-recommen");
            more.setParameter(parameter);
            mWebSocketClient.send(more.toString());
        } else if (position == 1) {
            MyRequset more = new MyRequset();
            MyRequset.Parameter parameter = new MyRequset.Parameter();
            parameter.setToken(SpManager.getToken());
            parameter.setAucSt("1");
            more.setUrlMapping("goods-myAucIng");
            more.setParameter(parameter);
            mWebSocketClient.send(more.myOrder());
        } else if (position == 2) {
            MyRequset more = new MyRequset();
            MyRequset.Parameter parameter = new MyRequset.Parameter();
            parameter.setToken(SpManager.getToken());
            more.setUrlMapping("collect-myCollect");
            more.setParameter(parameter);
            mWebSocketClient.send(more.myCollect());
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
                    if (position == 0) {
                        if(mMyFavoriteAdapter != null) {
                            mMyFavoriteAdapter.clearDatas();
                        }
                        if(mMyAucing != null) {
                            mMyAucing.clearDatas();
                        }
                        mAdapter.addData(mHomeBean.getResponse().getGoods());
                        mRefreshLayout.setData(mHomeBean.getResponse().getGoods());
                    } else if (position == 1) {
                        if(mAdapter != null) {
                            mAdapter.clearDatas();
                        }
                        if(mMyAucing != null) {
                            mMyAucing.clearDatas();
                        }
                        mMyFavoriteAdapter.setDataList(mMyOrder.getResponse());
                        mRefreshLayout.setData(mMyOrder.getResponse());
                    } else if (position == 2) {
                        if(mAdapter != null) {
                            mAdapter.clearDatas();
                        }
                        if(mMyFavoriteAdapter != null) {
                            mMyFavoriteAdapter.clearDatas();
                        }
                        mMyAucing.addData(mHomeBean.getResponse().getGoods());
                        mRefreshLayout.setData(mHomeBean.getResponse().getGoods());
                    }
//                    mPhoneListAdapter.addData(mHomeBean.getResponse().getPhoneGoods());
                    break;
            }
        }
    };

    @Override
    protected void loadData(Bundle savedInstanceState) {
        super.loadData(savedInstanceState);
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
                        if (message.contains("goods-recommen")) {
                            Gson gson = new Gson();
                            mHomeBean = gson.fromJson(message, RecommendMoreResponse.class);
                        } else if (message.contains("goods-myAucIng")) {
                            Gson gson = new Gson();
                            mMyOrder = gson.fromJson(message, MyOrder.class);
                        } else if (message.contains("collect-myCollect")) {
                            Gson gson = new Gson();
                            mHomeBean = gson.fromJson(message, RecommendMoreResponse.class);
                        }
                        Message msg = new Message();
                        msg.what = 1;
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

    @Override
    public void itemClick(int position) {
        Bundle bundle = new Bundle();
        if (this.position == 1) {
            bundle.putString("goodsid", mMyOrder.getResponse().get(position).getGoodsId() + "");
            bundle.putString("time", mMyOrder.getResponse().get(position).getTime() + "");
        } else {
            bundle.putString("goodsid", mHomeBean.getResponse().getGoods().get(position).getId());
            bundle.putString("time", mHomeBean.getResponse().getGoods().get(position).getTime());
        }
        startActivity(GoodsDetailActivity.class, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshGoodsEvent event) {
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);
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
}
