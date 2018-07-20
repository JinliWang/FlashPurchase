package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.app.library.view.dialog.MessageNoTitleDialog;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.SignSuccessEvent;
import com.flashPurchase.app.model.bean.MyIntegral;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
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
    @BindView(R.id.tv_one)
    TextView mTvOne;
    @BindView(R.id.tv_two)
    TextView mTvTwo;
    @BindView(R.id.tv_three)
    TextView mTvThree;
    @BindView(R.id.tv_five)
    TextView mTvFive;

    private WebSocketClient mWebSocketClient;
    private MyIntegral mMyGwb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_integral;
    }

    @Override
    protected void initView() {
        initTitle("我的积分");
        mTvIntegralDetail.setOnClickListener(this);
        mTvOne.setOnClickListener(this);
        mTvTwo.setOnClickListener(this);
        mTvThree.setOnClickListener(this);
        mTvFive.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_integral_detail:
                startActivity(IntegralDetailActivity.class);
                break;
            case R.id.tv_one:
                new MessageNoTitleDialog(this)
                        .getDialog("您是否确认兑换1赠币\n本次兑换消耗100积分", "取消", "确认兑换")
                        .seteditDialogListener(new MessageNoTitleDialog.MessageDialogListener() {
                            @Override
                            public void sure() {
                                MyRequset more = new MyRequset();
                                MyRequset.Parameter parameter = new MyRequset.Parameter();
                                parameter.setToken(SpManager.getToken());
                                parameter.setPoint("100");
                                more.setUrlMapping("account-pointForCoin");
                                more.setParameter(parameter);
                                mWebSocketClient.send(more.pointForCoin());
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                break;
            case R.id.tv_two:
                new MessageNoTitleDialog(this)
                        .getDialog("您是否确认兑换2赠币\n本次兑换消耗200积分", "取消", "确认兑换")
                        .seteditDialogListener(new MessageNoTitleDialog.MessageDialogListener() {
                            @Override
                            public void sure() {
                                MyRequset more = new MyRequset();
                                MyRequset.Parameter parameter = new MyRequset.Parameter();
                                parameter.setToken(SpManager.getToken());
                                parameter.setPoint("200");
                                more.setUrlMapping("account-pointForCoin");
                                more.setParameter(parameter);
                                mWebSocketClient.send(more.pointForCoin());
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                break;
            case R.id.tv_three:
                new MessageNoTitleDialog(this)
                        .getDialog("您是否确认兑换3赠币\n本次兑换消耗300积分", "取消", "确认兑换")
                        .seteditDialogListener(new MessageNoTitleDialog.MessageDialogListener() {
                            @Override
                            public void sure() {
                                MyRequset more = new MyRequset();
                                MyRequset.Parameter parameter = new MyRequset.Parameter();
                                parameter.setToken(SpManager.getToken());
                                parameter.setPoint("300");
                                more.setUrlMapping("account-pointForCoin");
                                more.setParameter(parameter);
                                mWebSocketClient.send(more.pointForCoin());
                            }

                            @Override
                            public void cancel() {

                            }
                        });
                break;
            case R.id.tv_five:
                new MessageNoTitleDialog(this)
                        .getDialog("您是否确认兑换5赠币\n本次兑换消耗500积分", "取消", "确认兑换")
                        .seteditDialogListener(new MessageNoTitleDialog.MessageDialogListener() {
                            @Override
                            public void sure() {
                                MyRequset more = new MyRequset();
                                MyRequset.Parameter parameter = new MyRequset.Parameter();
                                parameter.setToken(SpManager.getToken());
                                parameter.setPoint("500");
                                more.setUrlMapping("account-pointForCoin");
                                more.setParameter(parameter);
                                mWebSocketClient.send(more.pointForCoin());
                            }

                            @Override
                            public void cancel() {

                            }
                        });
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
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else if (message.contains("account-myPoint")) {
                        LogUtil.d(message);
                        Gson gson = new Gson();
                        mMyGwb = gson.fromJson(message, MyIntegral.class);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    } else if (message.contains("account-pointForCoin")) {
                        Message msg = new Message();
                        msg.what = 2;
                        msg.obj = message;
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
                    break;
                case 2:
                    String message = (String) msg.obj;
                    if (message.contains("积分不够了")) {
                        ToastUtil.show("积分不够了！");
                    } else {
                        ToastUtil.show("恭喜您，兑换成功！");
                        EventBus.getDefault().post(new SignSuccessEvent());
                        mTvNum.setText("0");
                    }
                    break;
            }
        }
    };
}
