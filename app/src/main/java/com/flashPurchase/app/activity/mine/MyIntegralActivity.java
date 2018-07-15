package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyGwb;
import com.flashPurchase.app.model.bean.MyIntegral;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/3.
 */

public class MyIntegralActivity extends BaseActivity {
    @BindView(R.id.tv_gwb)
    TextView mTvGwb;
    @BindView(R.id.tv_integral_detail)
    TextView mTvIntegralDetail;
    @BindView(R.id.tv_get_integral)
    TextView mTvGetIntegral;
    @BindView(R.id.tv_num)
    TextView mTvNum;

    private WebSocketClient mWebSocketClient;
    private MyIntegral mMyGwb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_integral;
    }

    @Override
    protected void initView() {
        initTitle("我的积分", R.drawable.icon_message);
    }

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
                        mMyGwb = gson.fromJson(message, MyIntegral.class);
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
                    mTvGwb.setText(mMyGwb.getResponse().getPoint() + "积分");
//                    mPaiBi.setText(mMyIncome.getResponse().getPayCoin() + "");
//                    mZengBi.setText(mMyIncome.getResponse().getFreeCoin() + "");
//                    mBuyBi.setText(mMyIncome.getResponse().getShopCoin() + "");
                    break;
            }
        }
    };
}
