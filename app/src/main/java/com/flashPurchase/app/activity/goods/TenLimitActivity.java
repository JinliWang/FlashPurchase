package com.flashPurchase.app.activity.goods;

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
import com.flashPurchase.app.adapter.TenLimittAdapter;
import com.flashPurchase.app.model.bean.TenLimit;
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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/19.
 */

public class TenLimitActivity extends BaseActivity {
    @BindView(R.id.list_ten)
    RecyclerView mListTen;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private int pageIndex = 1;
    private TenLimit mTenLimit;
    private TenLimittAdapter mLimittAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ten_limit;
    }

    @Override
    protected void initView() {
        initTitle("10元专区");

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, 2);
        mLimittAdapter = new TenLimittAdapter();
        mListTen.setLayoutManager(gridLayoutManager);
        mListTen.setAdapter(mLimittAdapter);
        mLimittAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mTenLimit.getResponse().getGoods().get(position).getId() + "");
                bundle.putString("time", mTenLimit.getResponse().getGoods().get(position).getTime() + "");
                if (mTenLimit.getResponse().getGoods().get(position).getStatus() == 1) {
                    bundle.putString("isnext", "0");
                }else {
                    bundle.putString("isnext", "1");
                }
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mLimittAdapter.clearDatas();
                pageIndex = 1;
                getTen();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                getTen();
            }
        });
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
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        LogUtil.d(message);
                        try {
                            Gson gson = new Gson();
                            mTenLimit = gson.fromJson(message, TenLimit.class);
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    getTen();
                    break;
                case 1:
                    mLimittAdapter.addData(mTenLimit.getResponse().getGoods());
                    mRefreshLayout.setData2(mTenLimit.getResponse().getGoods(), mLimittAdapter);
                    break;
            }
        }
    };

    private void getTen() {
        MyRequset more = new MyRequset();
        MyRequset.Parameter parameter = new MyRequset.Parameter();
        parameter.setPageNum(String.valueOf(pageIndex));
        parameter.setToken(SpManager.getToken());
        parameter.setPageSize("10");
        more.setUrlMapping("goods-limitTime");
        more.setParameter(parameter);
        mWebSocketClient.send(more.getTen());
    }
}
