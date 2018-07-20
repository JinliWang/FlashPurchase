package com.flashPurchase.app.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.app.library.util.ActivityManager;
import com.app.library.util.LogUtil;
import com.app.library.util.okhttp.CallBackUtil;
import com.app.library.util.okhttp.OkhttpUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.activity.MainActivity;
import com.flashPurchase.app.activity.login.LoginActivity;
import com.flashPurchase.app.model.bean.Login;
import com.flashPurchase.app.model.bean.UserInfo;
import com.flashPurchase.app.model.request.LoginReq;
import com.flashPurchase.app.net.manager.ApiManager;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;

/**
 * Created by 10951 on 2018/6/10.
 */

public class WXEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String APP_SECRET = "0fe8e5bcf010a156a877f43a7c9d2b01";
    private IWXAPI mWeixinAPI;
    public static final String WEIXIN_APP_ID = "wxc1bcb659fb67c7df";
    private static String uuid;
    private WebSocketClient mWebSocketClient;
    private String mClientId;
    private String mIcon;
    private String mNickName;
    private String mOpenId;
    private Login mUserInfo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, true);
        mWeixinAPI.handleIntent(this.getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        mWeixinAPI.handleIntent(intent, this);//必须调用此句话
    }

    //微信发送的请求将回调到onReq方法
    @Override
    public void onReq(BaseReq req) {
        LogUtil.d("onReq");
    }

    //发送到微信请求的响应结果
    @Override
    public void onResp(BaseResp resp) {
        LogUtil.d("onResp");
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                LogUtil.d("ERR_OK");
                //发送成功
                SendAuth.Resp sendResp = (SendAuth.Resp) resp;
                if (sendResp != null) {
                    String code = sendResp.code;
                    getAccess_token(code);
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                LogUtil.d("ERR_USER_CANCEL");
                //发送取消
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                LogUtil.d("ERR_AUTH_DENIED");
                //发送被拒绝
                break;
            default:
                //发送返回
                break;
        }

    }

    /**
     * 获取openid accessToken值用于后期操作
     *
     * @param code 请求码
     */
    private void getAccess_token(final String code) {
        String path = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + WEIXIN_APP_ID
                + "&secret="
                + APP_SECRET
                + "&code="
                + code
                + "&grant_type=authorization_code";
        LogUtil.d("getAccess_token：" + path);
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("appid", WEIXIN_APP_ID);
        paramsMap.put("secret", APP_SECRET);
        paramsMap.put("code", code);
        paramsMap.put("grant_type", "authorization_code");
        OkhttpUtil.okHttpGet("https://api.weixin.qq.com/sns/oauth2/access_token", paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    String openid = jsonObject.getString("openid").toString().trim();
                    String access_token = jsonObject.getString("access_token").toString().trim();
                    getUserMesg(access_token, openid);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * 获取微信的个人信息
     *
     * @param access_token
     * @param openid
     */
    private void getUserMesg(final String access_token, final String openid) {
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token="
                + access_token
                + "&openid="
                + openid;
        LogUtil.d("getUserMesg：" + path);
        //网络请求，根据自己的请求方式
        HashMap<String, String> paramsMap = new HashMap<>();
        paramsMap.put("access_token", access_token);
        paramsMap.put("openid", openid);
        OkhttpUtil.okHttpGet("https://api.weixin.qq.com/sns/userinfo", paramsMap, new CallBackUtil.CallBackString() {
            @Override
            public void onFailure(Call call, Exception e) {

            }

            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    mIcon = jsonObject.getString("headimgurl").toString().trim();
                    mOpenId = jsonObject.getString("openid").toString().trim();
                    mNickName = jsonObject.getString("nickname").toString().trim();
                    ApiManager.getApi().onLogin()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<String>() {

                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(String s) {
                                    mClientId = s;
                                    SpManager.setClientId(mClientId);
                                    LoginReq loginReq = new LoginReq();
                                    LoginReq.Parameter parameter = new LoginReq.Parameter();
                                    parameter.setClientId(mClientId);
                                    parameter.setIcon(mIcon);
                                    parameter.setOpenId(mOpenId);
                                    parameter.setNickName(mNickName);
                                    loginReq.setUrlMapping("user-login");
                                    loginReq.setParameter(parameter);
                                    try {
                                        mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + mClientId), new Draft_17()) {
                                            @Override
                                            public void onOpen(ServerHandshake handshakedata) {

                                            }

                                            @Override
                                            public void onMessage(String message) {
                                                LogUtil.d(message);
                                                if (!message.contains("response")) {
                                                    Message msg = new Message();
                                                    msg.what = 0;
                                                    handler.sendMessage(msg);
                                                } else if (message.contains("user-login")) {
                                                    Gson gson = new Gson();
                                                    mUserInfo = gson.fromJson(message, Login.class);
                                                    Message msg = new Message();
                                                    msg.what = 1;
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

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });

//                    ToastUtil.show(jsonObject.getString("nickname").toString().trim());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    LoginReq loginReq = new LoginReq();
                    LoginReq.Parameter parameter = new LoginReq.Parameter();
                    parameter.setClientId(mClientId);
                    parameter.setIcon(mIcon);
                    parameter.setOpenId(mOpenId);
                    parameter.setNickName(mNickName);
                    loginReq.setUrlMapping("user-login");
                    loginReq.setParameter(parameter);
                    mWebSocketClient.send(loginReq.toString());
                    break;
                case 1:
                    SpManager.setUserInfo(mUserInfo);
                    SpManager.setToken(mUserInfo.getResponse().getToken());
                    SpManager.setRegisterTime(mUserInfo.getResponse().getRegisterTime());
                    Intent intent = new Intent(WXEntryActivity.this, MainActivity.class);
                    startActivity(intent);
                    ActivityManager.getActivityManager().popActivityByClass(LoginActivity.class);
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