package com.flashpurchase.app.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;
import com.flashpurchase.app.view.RefreshLayout;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/9.
 */

public class NewNitificaDynamic extends BaseFragment {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.nitificate_list)
    ListView mNitificateList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_dynamic;
    }

    @Override
    protected void initView(View view) {
        initTitle("最新动态");
        mIvLeft.setVisibility(View.GONE);
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_msg));
    }
}
