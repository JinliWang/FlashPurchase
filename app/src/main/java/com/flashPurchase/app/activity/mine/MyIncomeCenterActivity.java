package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.PopupUtil;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.fragment.mine.ExpenseDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 拍币中心
 * Created by 10951 on 2018/7/2.
 */

public class MyIncomeCenterActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.more_layout)
    FrameLayout mMessageLayout;
    @BindView(R.id.pai_bi)
    TextView mPaiBi;
    @BindView(R.id.zeng_bi)
    TextView mZengBi;
    @BindView(R.id.buy_bi)
    TextView mBuyBi;
    @BindView(R.id.tv_question)
    TextView mTvQuestion;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private PopupWindow popupWindow;
    private View popupView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_income;
    }

    @Override
    protected void initView() {
        mVpContent.setAdapter(new ProcessCheckAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(4);
        mTabTitle.setupWithViewPager(mVpContent);
        mTvTitle.setText("我的财产");
        mIvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mMessageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initPop();
            }
        });
    }

    private void initPop() {
        if (popupWindow == null) {
            popupView = UIUtil.inflate(R.layout.pop_sale_work);
            FrameLayout frameLayout = (FrameLayout)popupView.findViewById(R.id.message_layout);
            TextView textView = (TextView) popupView.findViewById(R.id.tv_deal_history);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(CenterMessageActivity.class);
                }
            });
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(DealHistoryActivity.class);
                }
            });
            popupWindow = PopupUtil.showPopup(popupView, MyIncomeCenterActivity.this);
        } else {
            popupWindow.showAtLocation(popupView, Gravity.TOP, 0, 0);
        }
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
                    return "支出明细";
                case 1:
                    return "收益明细";
            }
            return "";
        }
    }

}
