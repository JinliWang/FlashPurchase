package com.nqi.app.activity;

import com.mls.library.base.BaseActivity;
import com.nqi.app.R;

public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initTitle("机房工程监理及验收监测辅助软件");
    }
}
