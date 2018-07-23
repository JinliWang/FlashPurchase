package com.flashPurchase.app.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.library.base.BaseActivity;
import com.app.library.util.ActivityManager;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.UpdateSuccess;
import com.flashPurchase.app.model.bean.Register;
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
import cn.smssdk.SMSSDK;

/**
 * Created by 10951 on 2018/7/19.
 */

public class ChangeNameActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.btn_sure)
    Button mBtnSure;

    private WebSocketClient mWebSocketClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_change_name;
    }

    @Override
    protected void initView() {
        initTitle("修改昵称");
        mBtnSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRequset requset = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setToken(SpManager.getToken());
                parameter.setNickname(mEtUsername.getText().toString());
                requset.setParameter(parameter);
                requset.setUrlMapping("user-update");
                mWebSocketClient.send(requset.update());
            }
        });
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
                    } else if (message.contains("user-update")) {
                        if (message.contains("\"success\":1")) {
                            Message msg = new Message();
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        }
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
                    ToastUtil.show("修改成功！");
                    SpManager.setName(mEtUsername.getText().toString());
                    EventBus.getDefault().post(new UpdateSuccess());
                    finish();
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
