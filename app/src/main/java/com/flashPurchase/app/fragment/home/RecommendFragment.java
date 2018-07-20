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
import com.flashPurchase.app.adapter.RecommendAdapter;
import com.flashPurchase.app.event.RefreshGoodsEvent;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
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

public class RecommendFragment extends BaseFragment implements BaseRecyclerAdapter.OnItemClickListener {
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private RecommendAdapter mAdapter;
    private int pageIndex = 1;
    private RecommendMoreResponse mHomeBean;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mList.setLayoutManager(gridLayoutManager);
        mAdapter = new RecommendAdapter();
        mList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mAdapter.clearDatas();
                pageIndex++;
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
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
                    more.setUrlMapping("goods-recommen");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.toString());
                    break;
                case 1:
                    mAdapter.setDataList(mHomeBean.getResponse().getGoods());
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
                        Gson gson = new Gson();
                        mHomeBean = gson.fromJson(message, RecommendMoreResponse.class);
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
        bundle.putString("goodsid", mHomeBean.getResponse().getGoods().get(position).getId());
        bundle.putString("time", mHomeBean.getResponse().getGoods().get(position).getTime());
        startActivity(GoodsDetailActivity.class, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshGoodsEvent event) {
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
