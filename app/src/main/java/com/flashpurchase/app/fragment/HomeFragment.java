package com.flashpurchase.app.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;
import com.github.wanglu1209.bannerlibrary.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 10951 on 2018/4/5.
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.home_menu_1)
    TextView mHomeMenu1;
    @BindView(R.id.home_menu_2)
    TextView mHomeMenu2;
    @BindView(R.id.home_menu_3)
    TextView mHomeMenu3;
    @BindView(R.id.home_menu_4)
    TextView mHomeMenu4;
    @BindView(R.id.home_menu_5)
    TextView mHomeMenu5;
    @BindView(R.id.rel_recomend)
    RelativeLayout mRelRecomend;
    @BindView(R.id.recomend_list)
    RecyclerView mRecomendList;
    @BindView(R.id.rel_sale)
    RelativeLayout mRelSale;
    @BindView(R.id.rel_phone)
    RelativeLayout mRelPhone;
    @BindView(R.id.rel_computer)
    RelativeLayout mRelComputer;
    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.view_page)
    ViewPager mViewPage;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        mViewPage.setAdapter(new HomeListAdapter(getChildFragmentManager()));
        mViewPage.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPage);

    }

    private static class HomeListAdapter extends FragmentPagerAdapter {
        private String title;

        public HomeListAdapter(FragmentManager fm) {
            super(fm);
            this.title = title;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeListFragment();
                case 1:
                    return new HomeListFragment();
                case 2:
                    return new HomeListFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "猜你喜欢";
                case 1:
                    return "我在拍";
                case 2:
                    return "我收藏";
            }
            return "";
        }
    }

}
