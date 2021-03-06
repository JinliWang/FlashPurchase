package com.flashPurchase.app.fragment.dynamic;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.adapter.DynamicAdapter;
import com.flashPurchase.app.event.RefreshGoodsEvent;
import com.flashPurchase.app.model.bean.RecentDeal;
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
 * Created by 10951 on 2018/4/9.
 */

public class NewNitificaDynamicFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.nitificate_list)
    ListView mNitificateList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private DynamicAdapter mAdapter;
    private List<RecentDeal.ResponseBean.DealRecordsBean> mList;

    private WebSocketClient mWebSocketClient;
    private int pageIndex = 1;
    private RecentDeal mRecentDeal;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_dynamic;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        initTitle("最新动态");
        mIvLeft.setVisibility(View.GONE);

        mList = new ArrayList<>();
        mAdapter = new DynamicAdapter(mList);
        mNitificateList.setAdapter(mAdapter);
        mNitificateList.setOnItemClickListener(this);
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                pageIndex = 1;
                mAdapter.clearData();
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                pageIndex++;
                Message msg = new Message();
                msg.what = 0;
                handler.sendMessage(msg);
            }
        });
    }

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
                        mRecentDeal = gson.fromJson(message, RecentDeal.class);
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
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setPageNum(String.valueOf(pageIndex));
                    parameter.setPageSize("10");
                    parameter.setGoodsId("0");
                    more.setUrlMapping("goods-recentDeal");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.getRecent());
                    break;
                case 1:
                    mAdapter.addData(mRecentDeal.getResponse().getDealRecords());
                    mRefreshLayout.setData(mRecentDeal.getResponse().getDealRecords());
                    break;
            }
        }
    };

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Bundle bundle = new Bundle();
        bundle.putString("goodsid", mRecentDeal.getResponse().getDealRecords().get(i).getGoodsId());
        bundle.putString("time", mRecentDeal.getResponse().getDealRecords().get(i).getTime());
        bundle.putString("isnext", "1");
        startActivity(GoodsDetailActivity.class, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshGoodsEvent event) {
        if (mWebSocketClient != null) {
            Message msg = new Message();
            msg.what = 0;
            handler.sendMessage(msg);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
