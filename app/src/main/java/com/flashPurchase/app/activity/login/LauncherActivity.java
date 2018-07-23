package com.flashPurchase.app.activity.login;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.NetworkUtil;
import com.app.library.util.StatusBarUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.MainActivity;
import com.flashPurchase.app.model.bean.Login;
import com.flashPurchase.app.model.request.LoginReq;
import com.flashPurchase.app.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class LauncherActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    private WebSocketClient mWebSocketClient;
    private Login mLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setFull(this);
        requestPermission(this, MY_PERMISSIONS_REQUEST_CAMERA);
        if (!NetworkUtil.hasNetwork(this)) {
            ToastUtil.show("没有网络");
        }
    }

    public void requestPermission(final @NonNull Activity activity, final @IntRange(from = 0) int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.d("RecommendMoreResponse");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            LogUtil.d("Allowed");
            enter();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            LogUtil.d("Allow 1");
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                LogUtil.d("Allow 2");
                enter();
            } else {
                Toast.makeText(this, "您拒绝了权限", Toast.LENGTH_SHORT).show();
                requestPermission(this, MY_PERMISSIONS_REQUEST_CAMERA);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void enter() {
        if (TextUtils.isEmpty(SpManager.getClientId())) {
            startActivity(LoginActivity.class);
            finish();
        } else {
            if (!TextUtils.isEmpty(SpManager.getUserPwd())) {
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
                            } else if (message.contains("user-login")) {//
                                Gson gson = new Gson();
                                mLogin = gson.fromJson(message, Login.class);
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
            }else {
                IWXAPI mApi = WXAPIFactory.createWXAPI(this, WXEntryActivity.WEIXIN_APP_ID, true);
                mApi.registerApp(WXEntryActivity.WEIXIN_APP_ID);
                if (!mApi.isWXAppInstalled()) {
                    ToastUtil.show("请先安装微信！");
                    startActivity(LoginActivity.class);
                    finish();
                } else {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test_neng";
                    mApi.sendReq(req);
                }
            }
        }
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    LoginReq loginReq = new LoginReq();
                    LoginReq.Parameter parameter = new LoginReq.Parameter();
                    parameter.setPhone(SpManager.getUserName());
                    parameter.setPassword(SpManager.getUserPwd());
                    parameter.setClientId(SpManager.getClientId());
                    loginReq.setUrlMapping("user-login");
                    loginReq.setParameter(parameter);
                    mWebSocketClient.send(loginReq.register());
                    break;
                case 1:
                    if (mLogin.getResponse().getCode().equals("200")) {
                        startActivity(MainActivity.class);
                    } else {
                        ToastUtil.show("手机号或者密码错误！");
                    }
                    break;
            }
        }
    };
}
