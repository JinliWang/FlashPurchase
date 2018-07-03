package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;
import com.flashPurchase.app.fragment.mine.ExpenseDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/3.
 */

public class MyGwbActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.more_layout)
    FrameLayout mMoreLayout;
    @BindView(R.id.tv_gwb)
    TextView mTvGwb;
    @BindView(R.id.tv_user_line)
    TextView mTvUserLine;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_gouwubi;
    }

    @Override
    protected void initView() {
        mVpContent.setAdapter(new ProcessCheckAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(4);
        mTabTitle.setupWithViewPager(mVpContent);
    }

    private static class ProcessCheckAdapter extends FragmentPagerAdapter {

        ProcessCheckAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return ExpenseDetailFragment.getInstance(position);
                case 1:
                    return ExpenseDetailFragment.getInstance(position);

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "可用购物币";
                case 1:
                    return "过期或已使用";
            }
            return "";
        }
    }
}
