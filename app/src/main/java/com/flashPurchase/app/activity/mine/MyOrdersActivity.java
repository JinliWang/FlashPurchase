package com.flashPurchase.app.activity.mine;

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
import com.flashPurchase.app.fragment.mine.MyAuctionFragment;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/4.
 */

public class MyOrdersActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.more_layout)
    FrameLayout mMoreLayout;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_auction;
    }

    @Override
    protected void initView() {
        initTitle("我的订单");
        mVpContent.setAdapter(new MyAuctionAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(4);
        mTabTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabTitle.setupWithViewPager(mVpContent);
    }

    private static class MyAuctionAdapter extends FragmentPagerAdapter {
        private String title;

        public MyAuctionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
//                    return MyAuctionFragment.getInstance("0");
                case 1:
//                    return MyAuctionFragment.getInstance("1");
                case 2:
//                    return MyAuctionFragment.getInstance("2");
                case 3:
//                    return MyAuctionFragment.getInstance("3");
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部订单";
                case 1:
                    return "待付款";
                case 2:
                    return "已支付";
                case 3:
                    return "待评价";
            }
            return "";
        }
    }
}
