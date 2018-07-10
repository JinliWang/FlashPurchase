package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/3.
 */

public class MyIntegralActivity extends BaseActivity {
    @BindView(R.id.tv_gwb)
    TextView mTvGwb;
    @BindView(R.id.tv_integral_detail)
    TextView mTvIntegralDetail;
    @BindView(R.id.tv_get_integral)
    TextView mTvGetIntegral;
    @BindView(R.id.tv_num)
    TextView mTvNum;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_integral;
    }

    @Override
    protected void initView() {
        initTitle("我的积分", R.drawable.icon_message);
    }

}
