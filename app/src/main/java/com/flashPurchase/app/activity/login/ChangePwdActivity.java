package com.flashPurchase.app.activity.login;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;

/**
 * Created by 10951 on 2018/6/10.
 */

public class ChangePwdActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_get_pwd;
    }

    @Override
    protected void initView() {
        initTitle("找回密码");
    }
}
