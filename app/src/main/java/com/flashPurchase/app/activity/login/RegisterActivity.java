package com.flashPurchase.app.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/6/10.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.tv_yzm)
    TextView mTvYzm;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.btn_regist)
    Button mBtnRegist;
    @BindView(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.login_wx)
    TextView mLoginWx;
    @BindView(R.id.login_qq)
    TextView mLoginQq;

    @Override
    protected int getLayoutId() {
        return R.layout.avtivity_regist;
    }

    @Override
    protected void initView() {
        initTitle("快速注册", "登录");
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_right:
                startActivity(LoginActivity.class);
                break;
        }
    }
}
