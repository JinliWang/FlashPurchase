package com.flashPurchase.app.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.BindSuccess;
import com.flashPurchase.app.model.request.LoginReq;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import butterknife.BindView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by 10951 on 2018/7/20.
 */

public class BindPhoneActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;

    EventHandler eventHandler;
    private WebSocketClient mWebSocketClient;
    private String mMessage;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bind_phone;
    }

    @Override
    protected void initView() {
        initTitle("绑定手机");
        eventHandler = new EventHandler() {

            /**
             * 在操作之后被触发
             *
             * @param event  参数1
             * @param result 参数2 SMSSDK.RESULT_COMPLETE表示操作成功，为SMSSDK.RESULT_ERROR表示操作失败
             * @param data   事件操作的结果
             */

            @Override
            public void afterEvent(int event, int result, Object data) {
                Message message = myHandler.obtainMessage(0x00);
                message.arg1 = event;
                message.arg2 = result;
                message.obj = data;
                myHandler.sendMessage(message);
            }
        };
//注册监听器
        SMSSDK.registerEventHandler(eventHandler);
        mTvGetCode.setOnClickListener(this);
        mBtnRegist.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_get_code:
                String strPhoneNumber = mEtUsername.getText().toString();
                if (null == strPhoneNumber || "".equals(strPhoneNumber) || strPhoneNumber.length() != 11) {
                    Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode("86", strPhoneNumber);

                //开启线程去更新button的text
                new Thread() {
                    @Override
                    public void run() {
                        int totalTime = 60;
                        for (int i = 0; i < totalTime; i++) {
                            Message message = myHandler.obtainMessage(0x01);
                            message.arg1 = totalTime - i;
                            myHandler.sendMessage(message);
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        myHandler.sendEmptyMessage(0x02);
                    }
                }.start();
                break;
            case R.id.btn_regist:
                String phoneNumber = mEtUsername.getText().toString();
                if (null == phoneNumber || "".equals(phoneNumber) || phoneNumber.length() != 11) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mEtCode.getText().toString())) {
                    ToastUtil.show("请获取并填写验证码！");
                    return;
                }
                SMSSDK.submitVerificationCode("86", phoneNumber, mEtCode.getText().toString());
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
                        msg.what = 3;
                        myHandler.sendMessage(msg);
                    } else if (message.contains("user-changePhone")) {
                        mMessage = message;
                        Message msg = new Message();
                        msg.what = 4;
                        myHandler.sendMessage(msg);
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

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(BindPhoneActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d(TAG, "submit code successful");
                            HashMap<String, Object> mData = (HashMap<String, Object>) data;
                            //返回国家的编号
                            String country = (String) mData.get("country");
                            String phone = (String) mData.get("phone");
                            Log.e("TAG", country + "====" + phone);
                            if (phone.equals(mEtUsername.getText().toString())) {
                                LoginReq loginReq = new LoginReq();
                                LoginReq.Parameter parameter = new LoginReq.Parameter();
                                parameter.setPhone(mEtUsername.getText().toString());
                                parameter.setToken(SpManager.getToken());
                                loginReq.setUrlMapping("user-changePhone");
                                loginReq.setParameter(parameter);
                                mWebSocketClient.send(loginReq.changePhone());
                            }
                        } else {
                            Log.d(TAG, data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            Log.e(TAG, "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(BindPhoneActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    mTvGetCode.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    mTvGetCode.setText("获取验证码");
                    mTvGetCode.setClickable(true);
                    break;
                case 4:
                    if (mMessage.contains("\"success\":1")) {
                        ToastUtil.show("绑定成功！");
                        SpManager.setPhone(mEtUsername.getText().toString());
                        EventBus.getDefault().post(new BindSuccess());
                        finish();
                    } else if (mMessage.contains("\"success\":0")) {
                        ToastUtil.show("绑定失败，请重试！");
                    }
                    break;
            }
        }
    };
}
