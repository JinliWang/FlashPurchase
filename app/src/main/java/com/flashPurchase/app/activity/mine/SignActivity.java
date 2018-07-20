package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.model.bean.MySignInfo;
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
    private MySignInfo mSignInfo;
    private boolean isSign = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sign;
    }

    @Override
    protected void initView() {
        initTitle("连续签到有好礼");
        mBtnSign.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId() == R.id.btn_sign) {
            if (isSign) {
                ToastUtil.show("已签到");
            } else {
                MyRequset requset = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setToken(SpManager.getToken());
                requset.setParameter(parameter);
                requset.setUrlMapping("sign-do");
                mWebSocketClient.send(requset.myCollect());
            }
        }
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
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("sign-get")) {//返回收藏状态，判断是否收藏
                        Gson gson = new Gson();
                        mSignInfo = gson.fromJson(message, MySignInfo.class);
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("sign-do")) {
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
                    mWebSocketClient.send(requset.myCollect());
                    break;
                case 1:
                    if (mSignInfo.getResponse().getTodaySign() == 1) {
                        isSign = true;
                    } else {
                        isSign = false;
                    }
                    mTvDay.setText(mSignInfo.getResponse().getSignCount() + "");
                    break;
                case 2:
                    ToastUtil.show("签到成功！");
                    mTvDay.setText(mSignInfo.getResponse().getSignCount() + 1 + "");
                    break;
            }
        }
    };
}
