package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.library.base.BaseActivity;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/10.
 */

public class AdviceActivity extends BaseActivity {
    @BindView(R.id.btn_submit)
    Button mBtnSubmit;
    @BindView(R.id.et_advice)
    EditText mEtAdvice;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_advice;
    }

    @Override
    protected void initView() {
        initTitle("建议或意见");
        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(mEtAdvice.getText().toString())) {
                    ToastUtil.show("请填写您的建议或意见！");
                    return;
                }
                ToastUtil.show("提交成功！");
                finish();
            }
        });
    }
}
