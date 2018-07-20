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
import com.flashPurchase.app.fragment.home.HomeFragment;
import com.flashPurchase.app.fragment.home.MyFavoriteFragment;
import com.flashPurchase.app.fragment.home.MyOrderFragment;
import com.flashPurchase.app.fragment.home.RecommendFragment;
import com.flashPurchase.app.fragment.mine.AucOutFragment;
import com.flashPurchase.app.fragment.mine.AucSucFragment;
import com.flashPurchase.app.fragment.mine.AucToBaskFragment;
import com.flashPurchase.app.fragment.mine.AucToReceiveFragment;
import com.flashPurchase.app.fragment.mine.AuctionNoticeFragment;
import com.flashPurchase.app.fragment.mine.MyAllAuctionFragment;
import com.flashPurchase.app.fragment.mine.MyAuctionFragment;
import com.flashPurchase.app.fragment.mine.SystemMessageFragment;
import com.flashPurchase.app.fragment.mine.WaitToPayFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/4.
 */

public class MyAuctionActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private String mAucSt;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_auction;
    }

    @Override
    protected void initView() {
        initTitle("我的竞拍");
        mAucSt = extraDatas.getString("aucSt");
        mVpContent.setAdapter(new MyAuctionAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(7);
        mTabTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabTitle.setupWithViewPager(mVpContent);
        switch (mAucSt) {
            case "0":
                mVpContent.setCurrentItem(0);
                break;
            case "1":
                mVpContent.setCurrentItem(1);
                break;
            case "2":
                mVpContent.setCurrentItem(2);
                break;
            case "3":
                mVpContent.setCurrentItem(3);
                break;
            case "4":
                mVpContent.setCurrentItem(4);
                break;
            case "6":
                mVpContent.setCurrentItem(6);
                break;
        }
    }

    private static class MyAuctionAdapter extends FragmentPagerAdapter {
        private String title;

        public MyAuctionAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 7;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new MyAllAuctionFragment();
                case 1:
                    return new MyAuctionFragment();
                case 2:
                    return new AucSucFragment();
                case 3:
                    return new AucOutFragment();
                case 4:
                    return new WaitToPayFragment();
                case 5:
                    return new AucToReceiveFragment();
                case 6:
                    return new AucToBaskFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部";
                case 1:
                    return "我在拍";
                case 2:
                    return "我拍中";
                case 3:
                    return "差价购";
                case 4:
                    return "待付款";
                case 5:
                    return "待签收";
                case 6:
                    return "待晒单";
            }
            return "";
        }
    }
}
