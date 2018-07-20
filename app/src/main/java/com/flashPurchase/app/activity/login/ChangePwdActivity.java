package com.flashPurchase.app.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.library.base.BaseActivity;
import com.app.library.util.ActivityManager;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.Register;
import com.flashPurchase.app.model.request.LoginReq;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/6/10.
 */

public class ChangePwdActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.btn_sure)
    Button mBtnSure;

    private Register mRegister;
    private String phone;
    private WebSocketClient mWebSocketClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_pwd;
    }

    @Override
    protected void initView() {
        initTitle("找回密码");
        phone = extraDatas.getString("phone");
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEtUsername.getText().toString())) {
                    ToastUtil.show("请输入密码！");
                    return;
                }
                LoginReq loginReq = new LoginReq();
                LoginReq.Parameter parameter = new LoginReq.Parameter();
                parameter.setPassword(mEtUsername.getText().toString());
                parameter.setPhone(phone);
                parameter.setClientId(SpManager.getClientId());
                loginReq.setUrlMapping("user-register");
                loginReq.setParameter(parameter);
                mWebSocketClient.send(loginReq.register());
            }
        });
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
                    } else if (message.contains("user-register")) {//返回收藏状态，判断是否收藏
                        Gson gson = new Gson();
                        mRegister = gson.fromJson(message, Register.class);
                        Message msg = new Message();
                        msg.what = 1;
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
                    if (mRegister.getResponse().getSuccess() == 1) {
                        ToastUtil.show("密码修改成功！");
                        ActivityManager.getActivityManager().popActivityByClass(FindBackPwdActivity.class);
                        finish();
                    }
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
    }
}
