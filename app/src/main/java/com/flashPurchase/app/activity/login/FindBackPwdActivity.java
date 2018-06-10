package com.flashPurchase.app.activity.login;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/6/10.
 */

public class FindBackPwdActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.tv_yzm)
    TextView mTvYzm;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.btn_next)
    Button mBtnNext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initView() {
        initTitle("找回密码");
        mBtnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ChangePwdActivity.class);
            }
        });
    }

}
