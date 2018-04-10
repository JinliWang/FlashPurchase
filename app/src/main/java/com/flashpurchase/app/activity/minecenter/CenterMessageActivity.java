package com.flashpurchase.app.activity.minecenter;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.app.library.base.BaseActivity;
import com.app.library.view.MyViewPager;
import com.flashpurchase.app.R;
import com.flashpurchase.app.fragment.HomeFragment;
import com.flashpurchase.app.fragment.MineCenterFragment;

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
        return 0;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(mAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setScrollable(false);
        viewPager.setOffscreenPageLimit(3);


        Drawable drawableMsg = getResources().getDrawable(R.drawable.choose_center_msg);
        drawableMsg.setBounds(0,0,39,39);
        rbMsg.setCompoundDrawables(null,drawableMsg,null,null);
        Drawable drawableNotice = getResources().getDrawable(R.drawable.choose_center_notice);
        drawableMsg.setBounds(0,0,39,39);
        rbNotice.setCompoundDrawables(null,drawableNotice,null,null);
        Drawable drawableSysmsg = getResources().getDrawable(R.drawable.choose_center_sysmsg);
        drawableMsg.setBounds(0,0,39,39);
        rbSysmsg.setCompoundDrawables(null,drawableSysmsg,null,null);

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
                    return new HomeFragment();
                case 2:
                    //商品分类
                    return new HomeFragment();
                case 3:
                    //个人中心
                    return new MineCenterFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
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
