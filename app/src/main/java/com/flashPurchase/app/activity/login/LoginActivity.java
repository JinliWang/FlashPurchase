package com.flashPurchase.app.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.MainActivity;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.event.AucSuccessEvent;
import com.flashPurchase.app.event.LoginSuccessEvent;
import com.flashPurchase.app.model.bean.Login;
import com.flashPurchase.app.model.bean.Order;
import com.flashPurchase.app.model.bean.PayResult;
import com.flashPurchase.app.model.bean.RechargeOrder;
import com.flashPurchase.app.model.request.LoginReq;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.net.manager.ApiManager;
import com.flashPurchase.app.wxapi.WXEntryActivity;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

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
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 10951 on 2018/6/10.
 */

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.tv_forget_pwd)
    TextView mTvForgetPwd;
    @BindView(R.id.login_wx)
    TextView mLoginWx;
    @BindView(R.id.login_qq)
    TextView mLoginQq;
    @BindView(R.id.progress)
    ProgressBar mProgressBar;

    private WebSocketClient mWebSocketClient;
    private Login mLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_login;
    }

    @Override
    protected void initView() {
        initTitle("登录", "注册");
        mTvForgetPwd.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mLoginWx.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    private void login() {
        ApiManager.getApi().onLogin()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        SpManager.setClientId(s);
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
                                    } else if (message.contains("user-login")) {//返回收藏状态，判断是否收藏
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
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    LoginReq loginReq = new LoginReq();
                    LoginReq.Parameter parameter = new LoginReq.Parameter();
                    parameter.setPhone(mEtUsername.getText().toString());
                    parameter.setPassword(mEtPwd.getText().toString());
                    parameter.setClientId(SpManager.getClientId());
                    loginReq.setUrlMapping("user-login");
                    loginReq.setParameter(parameter);
                    mWebSocketClient.send(loginReq.register());
                    break;
                case 1:
                    if (mLogin.getResponse().getCode().equals("200")) {
                        SpManager.setMark(mLogin.getResponse().getMark() + "");
                        SpManager.setToken(mLogin.getResponse().getToken());
                        SpManager.setRegisterTime(mLogin.getResponse().getRegisterTime());
                        SpManager.setUserInfo(mLogin);
                        SpManager.setUserName(mEtUsername.getText().toString());
                        SpManager.setName(mEtUsername.getText().toString());
                        SpManager.setUserPwd(mEtPwd.getText().toString());
                        startActivity(MainActivity.class);
                    } else {
                        ToastUtil.show("手机号或者密码错误！");
                    }
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_right://注册
                startActivity(RegisterActivity.class);
                break;
            case R.id.tv_forget_pwd://忘记密码
                startActivity(FindBackPwdActivity.class);
                break;
            case R.id.tv_agreement://用户协议
                startActivity(AgreementActivity.class);
                break;
            case R.id.btn_login:
                String strPhoneNumber = mEtUsername.getText().toString();
                if (TextUtils.isEmpty(strPhoneNumber)) {
                    Toast.makeText(this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mEtPwd.getText().toString())) {
                    ToastUtil.show("请输入密码！");
                    return;
                }
                if (!mCbAgreement.isChecked()) {
                    ToastUtil.show("请同意用户协议！");
                    return;
                }
                login();
//                startActivity(MainActivity.class);
                break;
            case R.id.login_wx:
                mProgressBar.setVisibility(View.VISIBLE);
                IWXAPI mApi = WXAPIFactory.createWXAPI(this, WXEntryActivity.WEIXIN_APP_ID, true);
                mApi.registerApp(WXEntryActivity.WEIXIN_APP_ID);
                if (!mApi.isWXAppInstalled()) {
                    ToastUtil.show("请先安装微信！");
                } else {
                    SendAuth.Req req = new SendAuth.Req();
                    req.scope = "snsapi_userinfo";
                    req.state = "wechat_sdk_demo_test_neng";
                    mApi.sendReq(req);
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
