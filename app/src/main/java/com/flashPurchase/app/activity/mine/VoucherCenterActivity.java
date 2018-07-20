package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.adapter.VoucherAdapter;
import com.flashPurchase.app.event.RechargeEvent;
import com.flashPurchase.app.model.VoucherMoney;
import com.flashPurchase.app.model.bean.Order;
import com.flashPurchase.app.model.bean.PayResult;
import com.flashPurchase.app.model.bean.RechargeOrder;
import com.flashPurchase.app.model.request.MyRequset;
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
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/5/27.
 */

public class VoucherCenterActivity extends BaseActivity {


    @BindView(R.id.voucher_list)
    RecyclerView mVoucherList;
    @BindView(R.id.tv_money1)
    TextView mTvMoney1;
    @BindView(R.id.tv_money2)
    TextView mTvMoney2;
    @BindView(R.id.tv_zfb)
    TextView mTvZfb;
    @BindView(R.id.cb_zfb)
    CheckBox mCbZfb;
    @BindView(R.id.cb_wx)
    CheckBox mCbWx;
    @BindView(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    @BindView(R.id.zfb_pay_layout)
    RelativeLayout mZfbPayLayout;
    @BindView(R.id.wx_pay_layout)
    RelativeLayout mWxPayLayout;


    private VoucherAdapter mVoucherAdapter;
    private boolean isZFB = true;//选择支付方式
    private String mMoney;
    private WebSocketClient mWebSocketClient;
    private RechargeOrder mRechargeOrder;
    private Order mOrder;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voucher_center;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initTitle("充值中心");
        mVoucherAdapter = new VoucherAdapter(this);
        mVoucherList.setHasFixedSize(true);
        //recycleview设置布局方式，GridView (一行三列)
        mVoucherList.setLayoutManager(new GridLayoutManager(this, 3));
        mVoucherList.setAdapter(mVoucherAdapter);
        mVoucherAdapter.replaceAll(getData(), this);
        initClick();
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
                    } else if (message.contains("order-recharge")) {
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

    private void initClick() {
        mZfbPayLayout.setOnClickListener(this);
        mWxPayLayout.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    public ArrayList<VoucherMoney> getData() {
        String data = "50,100,200,300,500";
        String dataArr[] = data.split(",");
        ArrayList<VoucherMoney> list = new ArrayList<>();
        for (int i = 0; i < dataArr.length; i++) {
            String count = dataArr[i];
            list.add(new VoucherMoney(VoucherMoney.TWO, count));
        }
        list.add(new VoucherMoney(VoucherMoney.THREE, null));

        return list;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAdapterClickInfo(VoucherMoney model) {
        String money = model.data.toString();
        mMoney = money;
        mTvMoney1.setText(money + "拍币");
        Double d = new Double(money);
        mTvMoney2.setText("￥" + d.toString());

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
                if (TextUtils.isEmpty(mMoney)) {
                    ToastUtil.show("请选择一个数额进行充值！");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void Alipay(final Order body) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(VoucherCenterActivity.this);
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
                        Toast.makeText(VoucherCenterActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        setResult(1);
                        EventBus.getDefault().post(new RechargeEvent());
                        finish();

                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(VoucherCenterActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(VoucherCenterActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
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
            }
        }
    };
}
