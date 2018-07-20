package com.flashPurchase.app.activity.mine;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.ActivityManager;
import com.flashPurchase.app.BuildConfig;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zms on 2018/4/7.
 */

public class SetUpActivity extends BaseActivity {
    @BindView(R.id.ll_setup_back)
    LinearLayout llBack;
    @BindView(R.id.tv_version)
    TextView mTvVersion;
    @BindView(R.id.btn_logout)
    Button mButton;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setup;
    }

    @Override
    protected void initView() {
        onClick();
        mTvVersion.setText(BuildConfig.VERSION_NAME);
    }

    private void onClick() {
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.getActivityManager().popAllActivity();
                startActivity(LoginActivity.class);
                finish();
            }
        });
    }

}
