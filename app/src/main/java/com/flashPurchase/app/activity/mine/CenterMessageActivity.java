package com.flashPurchase.app.activity.mine;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.library.base.BaseActivity;
import com.app.library.view.MyViewPager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.fragment.mine.AuctionNoticeFragment;
import com.flashPurchase.app.fragment.mine.SystemMessageFragment;
import com.flashPurchase.app.fragment.home.HomeFragment;

import butterknife.BindView;

/**
 * Created by zms on 2018/4/8.
 */

public class CenterMessageActivity extends BaseActivity {
    @BindView(R.id.vp_center_msg)
    MyViewPager viewPager;
    @BindView(R.id.rg_center_msg)
    RadioGroup radioGroup;
    @BindView(R.id.rb_cender_msg)
    RadioButton rbMsg;
    @BindView(R.id.rb_cender_notice)
    RadioButton rbNotice;
    @BindView(R.id.rb_cender_sysmsg)
    RadioButton rbSysmsg;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_centermsg;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setScrollable(false);
        viewPager.setOffscreenPageLimit(3);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.rg_center_msg:
                        selectItem(0);
                        break;
                    case R.id.rb_cender_notice:
                        selectItem(1);
                        break;
                    case R.id.rb_cender_sysmsg:
                        selectItem(2);
                        break;
                }
            }
        });

    }
    private void selectItem(int position) {
        viewPager.setCurrentItem(position);
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
                    return new AuctionNoticeFragment();
                case 2:
                    //个人中心
                    return new SystemMessageFragment();
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
                    return "竞拍消息";
                case 1:
                    return "拍卖公告";
                case 2:
                    return "系统消息";
            }
            return "";
        }
    };
}
