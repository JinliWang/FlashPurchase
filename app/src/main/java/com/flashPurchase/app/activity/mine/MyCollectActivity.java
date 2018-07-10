package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.HomeListAdapter;
import com.flashPurchase.app.adapter.MyCollectAdapter;
import com.flashPurchase.app.adapter.RecommendMoreAdapter;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/10.
 */

public class MyCollectActivity extends BaseActivity {
    @BindView(R.id.recomend_list)
    RecyclerView mRecomendList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;


    private WebSocketClient mWebSocketClient;
    private MyCollectAdapter mAdapter;
    private int pageIndex = 0;
    private RecommendMoreResponse mHomeBean;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend_more;
    }

    @Override
    protected void initView() {
        initTitle("我收藏的商品");
        mAdapter = new MyCollectAdapter();
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 2);
        mRecomendList.setAdapter(mAdapter);
        mRecomendList.setLayoutManager(gridLayoutManager);
        mRefreshLayout.setEnableRefresh(false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setUserId("123456");
                    more.setUrlMapping("collect-myCollect");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    mRefreshLayout.setVisibility(View.VISIBLE);
//                    mLinNoData.setVisibility(View.GONE);
                    mAdapter.setDataList(mHomeBean.getResponse().getGoods());
                    mRefreshLayout.setData(mHomeBean.getResponse().getGoods());
                    break;
                case 2:
                    mRefreshLayout.setVisibility(View.GONE);
//                    mLinNoData.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=1"), new Draft_17()) {
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
                        try {
                            Gson gson = new Gson();
                            mHomeBean = gson.fromJson(message, RecommendMoreResponse.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            Message msg = new Message();
                            msg.what = 2;
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
}
