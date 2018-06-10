package com.flashPurchase.app.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.wxapi.WXEntryActivity;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import butterknife.BindView;

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

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_login;
    }

    @Override
    protected void initView() {
        initTitle("登录","注册");
        mTvForgetPwd.setOnClickListener(this);
        mTvAgreement.setOnClickListener(this);
        mLoginWx.setOnClickListener(this);
    }

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
            case R.id.login_wx:
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
}
