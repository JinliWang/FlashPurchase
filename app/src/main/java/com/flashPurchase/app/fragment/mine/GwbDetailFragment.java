package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.DealDetailCostAdapter;
import com.flashPurchase.app.adapter.GwbDetailAdapter;
import com.flashPurchase.app.adapter.GwbUseDetailAdapter;
import com.flashPurchase.app.model.bean.MyGwb;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/2.
 */

public class GwbDetailFragment extends BaseFragment {

    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.rel_title)
    RelativeLayout mRelTitle;

    private WebSocketClient mWebSocketClient;
    private MyGwb mMyGwb;
    private List<MyGwb.ResponseBean.JaUseBean> mJaUseBeans;
    private List<MyGwb.ResponseBean.JaExpireBean> mJaExpireBeans;
    private GwbDetailAdapter mAdapter;
    private GwbUseDetailAdapter mUserAdapter;
    private int p;

    public static GwbDetailFragment getInstance(int position) {
        GwbDetailFragment saleCenterFragment = new GwbDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        saleCenterFragment.setArguments(bundle);

        return saleCenterFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expense_detail;
    }

    @Override
    protected void initView(View view) {
        Bundle bundle = getArguments();
        p = bundle.getInt("position");
        mRelTitle.setVisibility(View.GONE);
        mJaExpireBeans = new ArrayList<>();
        mAdapter = new GwbDetailAdapter(mJaExpireBeans);
        mList.setAdapter(mAdapter);

        mJaUseBeans = new ArrayList<>();
        mUserAdapter = new GwbUseDetailAdapter(mJaUseBeans);
        mList.setAdapter(mUserAdapter);

        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableLoadmore(false);
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
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        LogUtil.d(message);
                        Gson gson = new Gson();
                        mMyGwb = gson.fromJson(message, MyGwb.class);
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
                    parameter.setToken(SpManager.getToken());
                    parameter.setPageSize("10");
                    more.setUrlMapping("account-myShopCoin");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    if (p == 0) {
                        mUserAdapter.addData(mMyGwb.getResponse().getJaUse());
                        mRefreshLayout.setData(mMyGwb.getResponse().getJaUse(), mUserAdapter);
                    } else {
                        mAdapter.addData(mMyGwb.getResponse().getJaExpire());
                        mRefreshLayout.setData(mMyGwb.getResponse().getJaExpire(), mAdapter);
                    }
                    break;
            }
        }
    };
}
