package com.flashpurchase.app.fragment.home;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.view.ScrollGridView;
import com.flashpurchase.app.R;
import com.flashpurchase.app.adapter.HomeBannerAdapter;
import com.flashpurchase.app.adapter.PhoneListAdapter;
import com.flashpurchase.app.adapter.RecomendListAdapter;
import com.flashpurchase.app.model.HomeBanner;
import com.flashpurchase.app.model.HomeList;
import com.github.wanglu1209.bannerlibrary.Banner;
import com.github.wanglu1209.bannerlibrary.BannerPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
    @BindView(R.id.phone_grid)
    ScrollGridView mPhoneGrid;
    @BindView(R.id.computer_grid)
    ScrollGridView mCompterGrid;
    @BindView(R.id.jewel_grid)
    ScrollGridView mJewelGrid;

    private List<HomeList> mLists;
    private List<HomeList> mList2;
    private RecomendListAdapter mAdapter;
    private HomeBannerAdapter mBannerAdapter;
    private PhoneListAdapter mPhoneListAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {

        mViewPage.setAdapter(new HomeListAdapter(getChildFragmentManager()));
        mViewPage.setOffscreenPageLimit(3);
        mTabLayout.setupWithViewPager(mViewPage);

        //初始化为你推荐列表
        HomeList homeList1 = new HomeList();
        homeList1.setGoodsName("iphone 8 256GB");
        homeList1.setGoodsPrice("￥8288.00");
        HomeList homeList2 = new HomeList();
        homeList2.setGoodsName("iphone 6 256GB");
        homeList2.setGoodsPrice("￥1188.00");
        HomeList homeList3 = new HomeList();
        homeList3.setGoodsName("iphone 7 256GB");
        homeList3.setGoodsPrice("￥8822.00");
        HomeList homeList4 = new HomeList();
        homeList4.setGoodsName("iphone X 256GB");
        homeList4.setGoodsPrice("￥8888.00");
        mLists = new ArrayList<>();
        mLists.add(homeList1);
        mLists.add(homeList2);
        mLists.add(homeList3);
        mLists.add(homeList4);
        mAdapter = new RecomendListAdapter();
        mAdapter.setDataList(mLists);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecomendList.setLayoutManager(linearLayoutManager);
        mRecomendList.setAdapter(mAdapter);

        //初始化Banner
        List<HomeBanner.ListBean> beanList = new ArrayList<>();
        HomeBanner homeBanner = new HomeBanner();
        HomeBanner.ListBean banner = new HomeBanner.ListBean();
        banner.setBannerpic("http://gfs5.gomein.net.cn/T1obZ_BmLT1RCvBVdK.jpg");
        HomeBanner.ListBean banner1 = new HomeBanner.ListBean();
        banner1.setBannerpic("http://gfs9.gomein.net.cn/T1C3J_B5LT1RCvBVdK.jpg");
        HomeBanner.ListBean banner2 = new HomeBanner.ListBean();
        banner2.setBannerpic("http://gfs5.gomein.net.cn/T1CwYjBCCT1RCvBVdK.jpg");
        HomeBanner.ListBean banner3 = new HomeBanner.ListBean();
        banner3.setBannerpic("http://gfs7.gomein.net.cn/T1u8V_B4ET1RCvBVdK.jpg");
        HomeBanner.ListBean banner4 = new HomeBanner.ListBean();
        banner4.setBannerpic("http://gfs7.gomein.net.cn/T1zODgB5CT1RCvBVdK.jpg");
        beanList.add(banner);
        beanList.add(banner1);
        beanList.add(banner2);
        beanList.add(banner3);
        beanList.add(banner4);
        homeBanner.setList(beanList);
        mBannerAdapter = new HomeBannerAdapter(getActivity());
        mBannerAdapter.setData(homeBanner.getList());
        mBanner.setDot(R.drawable.banner_white, R.drawable.banner_blue).
                setDotGravity(Banner.CENTER).
                setAdapter(mBannerAdapter).
                setOnItemClickListener(new BannerPagerAdapter.onItemClickListener() {
                    @Override
                    public void onClick(int position) {
//                                        ToastUtil.show("onClick");
//                                        homeBanner.getList().get(position).getRid();
                    }
                }).
                startAutoPlay();

        //初始化手机列表
        mList2 = new ArrayList<>();
        mList2.add(mLists.get(0));
        mList2.add(mLists.get(1));
        mList2.add(mLists.get(2));
        mPhoneListAdapter = new PhoneListAdapter(mList2);
        mPhoneGrid.setAdapter(mPhoneListAdapter);
        mCompterGrid.setAdapter(mPhoneListAdapter);
        mJewelGrid.setAdapter(mPhoneListAdapter);
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
