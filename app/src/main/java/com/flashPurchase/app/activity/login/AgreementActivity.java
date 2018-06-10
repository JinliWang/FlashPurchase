package com.flashPurchase.app.activity.login;

import android.webkit.WebView;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/6/10.
 */

public class AgreementActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView mWeb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initView() {
        initTitle("服务协议");
    }

}
