package com.flashpurchase.app.activity;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.ActivityManager;
import com.app.library.view.MyViewPager;
import com.flashpurchase.app.R;
import com.flashpurchase.app.fragment.HomeFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_tab_content)
    MyViewPager mVpTabContent;
    @BindView(R.id.rg_container)
    RadioGroup mRgContainer;

    // 退出时间
    private long exitTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mVpTabContent.setAdapter(mAdapter);
        mVpTabContent.setCurrentItem(0);
        mVpTabContent.setScrollable(false);
        mVpTabContent.setOffscreenPageLimit(4);

        mRgContainer.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        selectItem(0);
                        break;
                    case R.id.rb_new_state:
                        selectItem(1);
                        break;
                    case R.id.rb_goods_type:
                        selectItem(2);
                        break;
                    case R.id.rb_me:
                        selectItem(3);
                        break;

                }
            }
        });
    }

    private void selectItem(int position) {
        mVpTabContent.setCurrentItem(position);
    }

    @Override
    public void onBackPressed() {
        exit();
    }

    /**
     * 退出程序
     */
    private void exit() {
        // 两次点击间隔<2秒钟退出
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(MainActivity.this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            // 退出应用
            ActivityManager.getActivityManager().popAllActivity();
        }
    }

    FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    //首页
                    return new HomeFragment();
                case 1:
                    //最新动态
                    return new HomeFragment();
                case 2:
                    //商品分类
                    return new HomeFragment();
                case 3:
                    //个人中心
                    return new HomeFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "首页";
                case 1:
                    return "最新动态";
                case 2:
                    return "商品分类";
                case 3:
                    return "个人中心";
            }
            return "";
        }
    };
}
