package com.flashpurchase.app.fragment.classification;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;
import com.flashpurchase.app.fragment.home.HomeListFragment;

import butterknife.BindView;

/**
 * 商品分类
 * Created by 10951 on 2018/4/11.
 */

public class GoodsClassificationFragment extends BaseFragment {
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_good_classification;
    }

    @Override
    protected void initView(View view) {
        initTitle("商品分类");
        mIvRight.setVisibility(View.VISIBLE);
        mIvLeft.setVisibility(View.GONE);
        mIvRight.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_msg));
        mVpContent.setAdapter(new GoodsClassificationAdapter(getChildFragmentManager()));
        mVpContent.setOffscreenPageLimit(12);
        mTabTitle.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabTitle.setupWithViewPager(mVpContent);
    }

    private static class GoodsClassificationAdapter extends FragmentPagerAdapter {

        GoodsClassificationAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ClassificationListFragment();
                case 1:
                    return new HomeListFragment();
                case 2:
                    return new HomeListFragment();
                case 3:
                    return new HomeListFragment();
                case 4:
                    return new HomeListFragment();
                case 5:
                    return new HomeListFragment();
                case 6:
                    return new HomeListFragment();
                case 7:
                    return new HomeListFragment();
                case 8:
                    return new HomeListFragment();
                case 9:
                    return new HomeListFragment();
                case 10:
                    return new HomeListFragment();
                case 11:
                    return new HomeListFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 12;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "全部";
                case 1:
                    return "十元专区";
                case 2:
                    return "手机专区";
                case 3:
                    return "珠宝配饰";
                case 4:
                    return "电脑平板";
                case 5:
                    return "生活家电";
                case 6:
                    return "数码影音";
                case 7:
                    return "其他专区";
                case 8:
                    return "美食天地";
                case 9:
                    return "运动户外";
                case 10:
                    return "美妆个护";
                case 11:
                    return "家居生活";
            }
            return "";
        }
    }
}
