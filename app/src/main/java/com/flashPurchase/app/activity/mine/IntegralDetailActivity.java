package com.flashPurchase.app.activity.mine;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.flashPurchase.app.R;
import com.flashPurchase.app.fragment.mine.ExpenseDetailFragment;
import com.flashPurchase.app.fragment.mine.IntegralDetailFragment;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/2.
 */

public class IntegralDetailActivity extends BaseActivity {
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_deal_history;
    }

    @Override
    protected void initView() {
        initTitle("积分明细");
        mVpContent.setAdapter(new ProcessCheckAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(2);
        mTabTitle.setupWithViewPager(mVpContent);
        mTvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
    private static class ProcessCheckAdapter extends FragmentPagerAdapter {

        ProcessCheckAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return IntegralDetailFragment.getInstance(position);
                case 1:
                    return IntegralDetailFragment.getInstance(position);

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
                    return "支出明细";
                case 1:
                    return "收益明细";
            }
            return "";
        }
    }
}
