package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.model.bean.Order;
import com.flashPurchase.app.model.bean.PayResult;
import com.flashPurchase.app.model.bean.RechargeOrder;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/11.
 */

public class SignActivity extends BaseActivity {
    @BindView(R.id.btn_sign)
    Button mBtnSign;
    @BindView(R.id.tv_day)
    TextView mTvDay;

    private WebSocketClient mWebSocketClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initView() {
        initTitle("连续签到有好礼");
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
                    LogUtil.d(message);
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("order-recharge")) {//返回收藏状态，判断是否收藏
                        Gson gson = new Gson();
//                        mRechargeOrder = gson.fromJson(message, RechargeOrder.class);
                        Message msg = new Message();
                        msg.what = 2;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("pay-money")) {
                        Gson gson = new Gson();
//                        mOrder = gson.fromJson(message, Order.class);
                        Message msg = new Message();
                        msg.what = 3;
                        mHandler.sendMessage(msg);
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
                    MyRequset requset = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
            switch (msg.what) {
                case 0:
                    parameter.setToken(SpManager.getToken());
                    requset.setParameter(parameter);
                    requset.setUrlMapping("sign-get");
                    mWebSocketClient.send(requset.makeOrder());
                    break;
                case 1:
                    break;
                case 2:
                    parameter.setType("2");
                    requset.setParameter(parameter);
                    requset.setUrlMapping("pay-money");
                    mWebSocketClient.send(requset.makeOrder());
                    break;
            }
        }
    };
}
