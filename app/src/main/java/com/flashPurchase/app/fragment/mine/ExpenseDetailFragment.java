package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.DealDetailAdapter;
import com.flashPurchase.app.adapter.DealDetailCostAdapter;
import com.flashPurchase.app.model.bean.MyIncome;
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
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 10951 on 2018/7/2.
 */

public class ExpenseDetailFragment extends BaseFragment {

    @BindView(R.id.tv_type)
    TextView mTvType;
    @BindView(R.id.list)
    ListView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private WebSocketClient mWebSocketClient;
    private MyIncome mMyIncome;
    private List<MyIncome.ResponseBean.IncomeBean> mIncomeBeans;
    private List<MyIncome.ResponseBean.CostBean> mCostBeans;
    private DealDetailAdapter mAdapter;
    private DealDetailCostAdapter mCostAdapter;
    private int p;

    public static ExpenseDetailFragment getInstance(int position) {
        ExpenseDetailFragment saleCenterFragment = new ExpenseDetailFragment();
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
        mAdapter = new DealDetailAdapter(mIncomeBeans);
        mList.setAdapter(mAdapter);

        mCostBeans = new ArrayList<>();
        mCostAdapter = new DealDetailCostAdapter(mCostBeans);
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
                        mMyIncome = gson.fromJson(message, MyIncome.class);
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
                    more.setUrlMapping("account-myAsset");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    if (p == 0) {
                        mCostAdapter.addData(mMyIncome.getResponse().getCost());
                        mRefreshLayout.setData(mMyIncome.getResponse().getCost(), mCostAdapter);
                    } else {
                        mAdapter.addData(mMyIncome.getResponse().getIncome());
                        mRefreshLayout.setData(mMyIncome.getResponse().getIncome(), mAdapter);
                    }
                    break;
            }
        }
    };
}
