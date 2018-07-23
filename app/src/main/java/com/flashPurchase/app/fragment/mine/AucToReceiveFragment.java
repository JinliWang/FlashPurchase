package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.activity.goods.WuLiuActivity;
import com.flashPurchase.app.activity.mine.ComfirmOrderActivity;
import com.flashPurchase.app.adapter.AucSucAdapter;
import com.flashPurchase.app.adapter.AucToReceiveAdapter;
import com.flashPurchase.app.event.PaySuccess;
import com.flashPurchase.app.model.bean.MyAucList;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;

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
 * Created by 10951 on 2018/7/10.
 */

public class AucToReceiveFragment extends BaseFragment {
    @BindView(R.id.my_auction_list)
    ListView mMyAuctionList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private MyAucList mMyAucList;
    private AucToReceiveAdapter mMyAucAdapter;
    private List<MyAucList.ResponseBean> mList;
    private String mMessage;

    private WebSocketClient mWebSocketClient;

    public static AucToReceiveFragment getInstance(String type) {
        AucToReceiveFragment pane = new AucToReceiveFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        pane.setArguments(args);
        return pane;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_auction;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        mList = new ArrayList<>();
        mMyAucAdapter = new AucToReceiveAdapter(mList);
        mMyAuctionList.setAdapter(mMyAucAdapter);
        mMyAuctionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mList.get(i).getGoodsId() + "");
                bundle.putString("time", mList.get(i).getTime() + "");
                bundle.putString("isnext", "1");
                startActivity(ComfirmOrderActivity.class, bundle);
            }
        });
        mRefreshLayout.setEnableRefresh(false);

        mMyAucAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void itemClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_pai:
                        MyRequset more = new MyRequset();
                        MyRequset.Parameter parameter = new MyRequset.Parameter();
                        parameter.setOrderId(mMyAucList.getResponse().get(position).getId());
                        more.setUrlMapping("order-confirmReceive");
                        more.setParameter(parameter);
                        mWebSocketClient.send(more.wuliu());
                        break;
                    case R.id.tv_wuliu:
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("id", mMyAucList.getResponse().get(position).getId());
                        startActivity(WuLiuActivity.class, bundle2);
                        break;
                }
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
                    mMessage = message;
                    LogUtil.d(message);
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else if (message.contains("order-confirmReceive")) {
                        Message msg = new Message();
                        msg.what = 2;
                        handler.sendMessage(msg);
                    } else {
                        Gson gson = new Gson();
                        mMyAucList = gson.fromJson(message, MyAucList.class);
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
            MyRequset more = new MyRequset();
            MyRequset.Parameter parameter = new MyRequset.Parameter();
            switch (msg.what) {
                case 0:
                    parameter.setToken(SpManager.getToken());
                    parameter.setAucSt("5");
                    more.setUrlMapping("goods-myAucIng");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myOrder());
                    break;
                case 1:
                    mMyAucAdapter.refreshData(mMyAucList.getResponse());
                    mRefreshLayout.setData(mMyAucList.getResponse());
                    break;
                case 2:
                    if (mMessage.contains("\"success\":1")) {
                        ToastUtil.show("签收成功！");
                        parameter.setToken(SpManager.getToken());
                        parameter.setAucSt("5");
                        more.setUrlMapping("goods-myAucIng");
                        more.setParameter(parameter);
                        mWebSocketClient.send(more.myOrder());
                    } else if (mMessage.contains("\"success\":0")) {
                        ToastUtil.show("签收失败，请重试！");
                    }
                    break;

            }
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(PaySuccess paySuccess) {
        MyRequset more = new MyRequset();
        MyRequset.Parameter parameter = new MyRequset.Parameter();
        parameter.setToken(SpManager.getToken());
        parameter.setAucSt("5");
        more.setUrlMapping("goods-myAucIng");
        more.setParameter(parameter);
        mWebSocketClient.send(more.myOrder());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
