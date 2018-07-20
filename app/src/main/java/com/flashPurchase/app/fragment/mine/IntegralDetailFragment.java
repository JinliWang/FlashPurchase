package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.DealDetailAdapter;
import com.flashPurchase.app.adapter.DealDetailCostAdapter;
import com.flashPurchase.app.adapter.IntegralDetailAdapter;
import com.flashPurchase.app.adapter.IntegralDetailCostAdapter;
import com.flashPurchase.app.model.bean.MyIncome;
import com.flashPurchase.app.model.bean.MyIntegral;
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

public class IntegralDetailFragment extends BaseFragment {

    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private MyIntegral mMyIntegral;
    private List<MyIntegral.ResponseBean.IncomeBean> mIncomeBeans;
    private List<MyIntegral.ResponseBean.CostBean> mCostBeans;
    private IntegralDetailAdapter mAdapter;
    private IntegralDetailCostAdapter mCostAdapter;
    private int p;

    public static IntegralDetailFragment getInstance(int position) {
        IntegralDetailFragment saleCenterFragment = new IntegralDetailFragment();
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
        if (p == 0) {
            mTvType.setText("支出");
        } else {
            mTvType.setText("收益");
        }
        mIncomeBeans = new ArrayList<>();
        mAdapter = new IntegralDetailAdapter(mIncomeBeans);
        mList.setAdapter(mAdapter);

        mCostBeans = new ArrayList<>();
        mCostAdapter = new IntegralDetailCostAdapter(mCostBeans);
        mList.setAdapter(mAdapter);

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
                        mMyIntegral = gson.fromJson(message, MyIntegral.class);
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
                    more.setUrlMapping("account-myPoint");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    if (p == 0) {
                        mCostAdapter.addData(mMyIntegral.getResponse().getCost());
                        mRefreshLayout.setData(mMyIntegral.getResponse().getCost(), mCostAdapter);
                    } else {
                        mAdapter.addData(mMyIntegral.getResponse().getIncome());
                        mRefreshLayout.setData(mMyIntegral.getResponse().getIncome(), mAdapter);
                    }
                    break;
            }
        }
    };
}
