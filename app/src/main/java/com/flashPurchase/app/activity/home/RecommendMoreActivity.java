package com.flashPurchase.app.activity.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.app.library.base.BaseActivity;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.adapter.RecommendMoreAdapter;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/7.
 */

public class RecommendMoreActivity extends BaseActivity {
    @BindView(R.id.recomend_list)
    RecyclerView mRecomendList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private RecommendMoreAdapter mAdapter;
    private List<RecommendMoreResponse.ResponseBean.GoodsBean> mList = new ArrayList<>();
    private int pageIndex = 1;
    private boolean isLoadMore = false;
    private RecommendMoreResponse mHomeBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void initView() {
        initTitle("为你推荐", R.drawable.icon_message);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        mRecomendList.setLayoutManager(gridLayoutManager);
        mAdapter = new RecommendMoreAdapter();
        mRecomendList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mHomeBean.getResponse().getGoods().get(position).getId());
                bundle.putString("time", mHomeBean.getResponse().getGoods().get(position).getTime());
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                isLoadMore = false;
                mAdapter.clearDatas();
                pageIndex = 1;
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                isLoadMore = true;
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
                    if (isLoadMore) {
                        mList.addAll(mHomeBean.getResponse().getGoods());
                        mAdapter.addData(mList);
                        mRefreshLayout.setData(mList);
                    }else {
                        mAdapter.addData(mHomeBean.getResponse().getGoods());
                        mRefreshLayout.setData(mHomeBean.getResponse().getGoods());
                    }
//                    mPhoneListAdapter.addData(mHomeBean.getResponse().getPhoneGoods());
                    break;
            }
        }
    };

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                }

                @Override
                public void onMessage(String message) {
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        LogUtil.d(message);
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
}
