package com.flashPurchase.app.activity.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.AucSuccessEvent;
import com.flashPurchase.app.model.bean.Order;
import com.flashPurchase.app.model.bean.PayResult;
import com.flashPurchase.app.model.bean.RechargeOrder;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/14.
 */

public class FastRechargeActivity extends BaseActivity {
    @BindView(R.id.tv_pai)
    TextView mTvPai;
    @BindView(R.id.tv_pai_all)
    TextView mTvPaiAll;
    @BindView(R.id.iv_zfb)
    ImageView mIvZfb;
    @BindView(R.id.tv_zfb)
    TextView mTvZfb;
    @BindView(R.id.cb_zfb)
    CheckBox mCbZfb;
    @BindView(R.id.zfb_pay_layout)
    RelativeLayout mZfbPayLayout;
    @BindView(R.id.iv_wx)
    ImageView mIvWx;
    @BindView(R.id.tv_wx)
    TextView mTvWx;
    @BindView(R.id.cb_wx)
    CheckBox mCbWx;
    @BindView(R.id.wx_pay_layout)
    RelativeLayout mWxPayLayout;
    @BindView(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.btn_sure)
    Button mBtnSure;

    private boolean isZFB = true;//选择支付方式
    private String mMoney;
    private WebSocketClient mWebSocketClient;
    private RechargeOrder mRechargeOrder;
    private Order mOrder;
    private IWXAPI api;
    private String mType;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fast_recharge;
    }

    @Override
    protected void initView() {
        initTitle("支付订单");
        mType = extraDatas.getString("type");
        mMoney = extraDatas.getString("money");
        mTvPai.setText("￥" + mMoney);
        mTvPaiAll.setText("￥" + mMoney);
        mZfbPayLayout.setOnClickListener(this);
        mWxPayLayout.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
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
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("order-recharge")) {//返回收藏状态，判断是否收藏
                        Gson gson = new Gson();
                        mRechargeOrder = gson.fromJson(message, RechargeOrder.class);
                        Message msg = new Message();
                        msg.what = 2;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("pay-money")) {
                        Gson gson = new Gson();
                        mOrder = gson.fromJson(message, Order.class);
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

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.zfb_pay_layout:
                isZFB = true;
                mCbZfb.setChecked(true);
                mCbWx.setChecked(false);
                break;
            case R.id.wx_pay_layout:
                isZFB = false;
                mCbZfb.setChecked(false);
                mCbWx.setChecked(true);
                break;
            case R.id.btn_sure:
                if (!mCbAgreement.isChecked()) {
                    ToastUtil.show("请确认您已同意用户协议！");
                    return;
                }
                MyRequset requset = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setToken(SpManager.getToken());
                parameter.setType("1");
                parameter.setPrice(mMoney);
                requset.setParameter(parameter);
                requset.setUrlMapping("order-recharge");
                mWebSocketClient.send(requset.recharge());
                break;
        }
    }

    private void Alipay(final Order body) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(FastRechargeActivity.this);
                Map<String, String> result = alipay.payV2(body.getResponse().getObject().getOrderStr(), true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = 0;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void WxPay() {
        PayReq request = new PayReq();

        request.appId = "wxc1bcb659fb67c7df";

        request.partnerId = "1900000109";

        request.prepayId= "1101000000140415649af9fc314aa427";

        request.packageValue = "Sign=WXPay";

        request.nonceStr= "1101000000140429eb40476f8896f4c9";

        request.timeStamp= "1398746574";

        request.sign= "7FFECB600D7157C5AA49810D2D8F28BC2811827B";

        api.sendReq(request);
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(FastRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        setResult(1);
                        finish();

                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(FastRechargeActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(FastRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 1:
                    break;
                case 2:
                    MyRequset requset = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setOrderId(mRechargeOrder.getResponse().getId() + "");
                    parameter.setType("2");
                    requset.setParameter(parameter);
                    requset.setUrlMapping("pay-money");
                    mWebSocketClient.send(requset.makeOrder());
                    break;
                case 3:
                    if (isZFB) {
                        Alipay(mOrder);
                    }
                    break;
                case 4:
                    String m = (String) msg.obj;
                    if(m.contains("恭喜你获得拍品")) {
                        EventBus.getDefault().post(new AucSuccessEvent());
                    }
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
    }
}
