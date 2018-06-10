package com.flashPurchase.app.activity.mine;

import android.view.View;
import android.widget.LinearLayout;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;

import butterknife.BindView;

/**
 * Created by zms on 2018/4/7.
 */

public class SetUpActivity extends BaseActivity {
    @BindView(R.id.ll_setup_back)
    LinearLayout llBack;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setup;
    }

    @Override
    protected void initView() {
        onClick();
    }

    private void onClick(){
        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
