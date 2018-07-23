package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.app.library.base.BaseActivity;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.event.EditAddressEvent;
import com.flashPurchase.app.event.PayCancel;
import com.flashPurchase.app.event.PaySuccess;
import com.flashPurchase.app.model.bean.MyAucList;
import com.flashPurchase.app.model.bean.Order;
import com.flashPurchase.app.model.bean.OrderInfo;
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
import java.util.Map;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/16.
 */

public class ComfirmOrderActivity extends BaseActivity {
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.rel_address)
    RelativeLayout mRelAddress;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_market_price)
    TextView mTvMarketPrice;
    @BindView(R.id.tv_gwb)
    TextView mTvGwb;
    @BindView(R.id.cb_gwb)
    CheckBox mCbGwb;
    @BindView(R.id.tv_act_price)
    TextView mTvActPrice;
    @BindView(R.id.et_message)
    EditText mEtMessage;
    @BindView(R.id.cb_zfb)
    CheckBox mCbZfb;
    @BindView(R.id.zfb_pay_layout)
    RelativeLayout mZfbPayLayout;
    @BindView(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @BindView(R.id.tv_act_money)
    TextView mTvActMoney;
    @BindView(R.id.tv_pay)
    TextView mTvPay;
    @BindView(R.id.lin_address)
    LinearLayout mLinAddress;
    @BindView(R.id.lin_no_address)
    LinearLayout mLinNoAddress;

    private String mType;
    private WebSocketClient mWebSocketClient;
    private MyAucList mBean;
    private OrderInfo mOrderInfo;
    private Order mOrder;
    private int mShopCoinShow;
    private int mShopCoin;
    private String id = "";
    private String mGoodsId;
    private String mTime;
    private double mMarketP;
    private double mActP;
    private String mPics;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_comfirm_order;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initTitle("确认订单");
        mType = extraDatas.getString("type");
        id = extraDatas.getString("id");
        mGoodsId = extraDatas.getString("goodsid");
        mTime = extraDatas.getString("time");
        mMarketP = extraDatas.getDouble("marketprice");
        mActP = extraDatas.getDouble("actprice");
        mShopCoinShow = extraDatas.getInt("shopcoin");
        mPics = extraDatas.getString("pics");
        //初始化地址
        if (SpManager.getMyAddress().getResponse() != null) {
            mLinAddress.setVisibility(View.VISIBLE);
            mLinNoAddress.setVisibility(View.GONE);
            mTvName.setText("收件人：" + SpManager.getMyAddress().getResponse().getRecipient());
            mTvAddress.setText("收货地址：" + SpManager.getMyAddress().getResponse().getRegion() + SpManager.getMyAddress().getResponse().getSpecificAddress());
        } else {
            mLinNoAddress.setVisibility(View.VISIBLE);
            mLinAddress.setVisibility(View.GONE);
        }
        mLinAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyAddressActivity.class);
            }
        });

        mLinNoAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyAddressActivity.class);
            }
        });

        ImageLoadManager.getInstance().setImage(this, mPics, mIvGoods);
        //商品信息展示
        mTvGwb.setText("-￥" + mShopCoinShow);
        mTvMarketPrice.setText("￥" + mMarketP);
        if (mType.equals("2")) {
            mTvActMoney.setText("￥" + (mActP));
            mTvActPrice.setText("￥" + (mActP));
        } else if (mType.equals("3")) {
            mTvActMoney.setText("￥" + (mMarketP - mShopCoinShow));
            mTvActPrice.setText("￥" + (mMarketP - mShopCoinShow));
        }
        mShopCoin = mShopCoinShow;

        mTvPay.setOnClickListener(this);
        mCbGwb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (mType.equals("2")) {
                    mTvActPrice.setText("￥" + mActP);
                    mTvActMoney.setText("￥" + mActP);
                    mShopCoin = 0;
                } else if (mType.equals("3")) {
                    if (b) {
                        mTvActPrice.setText("￥" + (mMarketP - mShopCoinShow));
                        mTvActMoney.setText("￥" + (mMarketP - mShopCoinShow));
                        mShopCoin = mShopCoinShow;
                    } else {
                        mTvActPrice.setText("￥" + mMarketP);
                        mTvActMoney.setText("￥" + mMarketP);
                        mShopCoin = 0;
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_pay:
                if (!mCbAgreement.isChecked()) {
                    ToastUtil.show("请确认您已同意用户协议！");
                    return;
                }
                if (SpManager.getMyAddress().getResponse() == null) {
                    ToastUtil.show("请完善收货信息！！");
                    return;
                }
                if (!TextUtils.isEmpty(id)) {
                    MyRequset requset2 = new MyRequset();
                    MyRequset.Parameter parameter2 = new MyRequset.Parameter();
                    parameter2.setOrderId(id);
                    parameter2.setType("1");
                    requset2.setParameter(parameter2);
                    requset2.setUrlMapping("pay-money");
                    mWebSocketClient.send(requset2.makeOrder());
                } else {
                    MyRequset requset = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setToken(SpManager.getToken());
                    parameter.setGoodsId(mGoodsId);
                    parameter.setTime(mTime);
                    parameter.setAddressId(SpManager.getMyAddress().getResponse().getId());
                    parameter.setRemark(mEtMessage.getText().toString());
                    if (mType.equals("2")) {
                        parameter.setType("1");
                    } else if (mType.equals("3")) {
                        parameter.setType("2");
                    }
                    parameter.setShopCoin(mShopCoin + "");
                    requset.setUrlMapping("order-add");
                    requset.setParameter(parameter);
                    mWebSocketClient.send(requset.addOrder());
                }
                break;
        }
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
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("order-add")) {//返回收藏状态，判断是否收藏
                        Gson gson = new Gson();
                        mOrderInfo = gson.fromJson(message, OrderInfo.class);
                        Message msg = new Message();
                        msg.what = 1;
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    break;
                case 1:
                    MyRequset requset2 = new MyRequset();
                    MyRequset.Parameter parameter2 = new MyRequset.Parameter();
                    parameter2.setOrderId(mOrderInfo.getResponse().getId() + "");
                    parameter2.setType("1");
                    requset2.setParameter(parameter2);
                    requset2.setUrlMapping("pay-money");
                    mWebSocketClient.send(requset2.makeOrder());
                    break;
                case 3:
                    Alipay(mOrder);
                    break;
                case 4:
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
                        Toast.makeText(ComfirmOrderActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        EventBus.getDefault().post(new PaySuccess());
                        setResult(1);
                        finish();

                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        EventBus.getDefault().post(new PayCancel());
                        Toast.makeText(ComfirmOrderActivity.this, "支付取消", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ComfirmOrderActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    };

    private void Alipay(final Order body) {
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(ComfirmOrderActivity.this);
                Map<String, String> result = alipay.payV2(body.getResponse().getObject().getOrderStr(), true);
                Log.i("msp", result.toString());
                Message msg = new Message();
                msg.what = 4;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(EditAddressEvent event) {
        mLinNoAddress.setVisibility(View.GONE);
        mLinAddress.setVisibility(View.VISIBLE);
        mTvName.setText("收件人：" + SpManager.getMyAddress().getResponse().getRecipient());
        mTvAddress.setText("收货地址：" + SpManager.getMyAddress().getResponse().getRegion() + SpManager.getMyAddress().getResponse().getSpecificAddress());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
