package com.flashPurchase.app.activity.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.MyAucBaskAdapter;
import com.flashPurchase.app.model.bean.AucBask;
import com.flashPurchase.app.model.bean.MyBask;
import com.flashPurchase.app.model.request.MyRequset;
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
 * Created by 10951 on 2018/7/20.
 */

public class MyAucBaskActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.bask_list)
    ListView mBaskList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private int pageIndex = 1;
    private MyAucBaskAdapter mAdapter;
    private List<MyBask.ResponseBean.CommentsBean> mList;
    private MyBask mAucBask;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_bask;
    }

    @Override
    protected void initView() {
        initTitle("我的晒单");
        mList = new ArrayList<>();
        mAdapter = new MyAucBaskAdapter(mList);
        mBaskList.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mAdapter.clearData();
                pageIndex = 1;
                getList();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                getList();
            }
        });

        mBaskList.setOnItemClickListener(this);
    }

    private void getList() {
        MyRequset more = new MyRequset();
        MyRequset.Parameter parameter = new MyRequset.Parameter();
        parameter.setToken(SpManager.getToken());
        parameter.setPageSize("10");
        parameter.setPageNum(String.valueOf(pageIndex));
        more.setUrlMapping("comment-getMyComments");
        more.setParameter(parameter);
        mWebSocketClient.send(more.getTen());
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
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
                    } else if (message.contains("comment-getMyComments")) {
                        Gson gson = new Gson();
                        mAucBask = gson.fromJson(message, MyBask.class);
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    getList();
                    break;
                case 1:
                    mAdapter.addData(mAucBask.getResponse().getComments());
                    mRefreshLayout.setData(mAucBask.getResponse().getComments(), mAdapter);
                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putString("goodsid", mList.get(i).getGoodsId() + "");
        bundle.putString("time", mList.get(i).getTime() + "");
        bundle.putString("isnext", "0");
        startActivity(GoodsDetailActivity.class, bundle);
    }
}
