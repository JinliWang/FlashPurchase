package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.fragment.mine.ExpenseDetailFragment;
import com.flashPurchase.app.model.bean.MyGwb;
import com.flashPurchase.app.model.bean.MyIncome;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/3.
 */

public class MyGwbActivity extends BaseActivity {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.more_layout)
    FrameLayout mMoreLayout;
    @BindView(R.id.tv_gwb)
    TextView mTvGwb;
    @BindView(R.id.tv_user_line)
    TextView mTvUserLine;
    @BindView(R.id.tab_title)
    TabLayout mTabTitle;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private WebSocketClient mWebSocketClient;
    private MyGwb mMyGwb;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_gouwubi;
    }

    @Override
    protected void initView() {
        mVpContent.setAdapter(new ProcessCheckAdapter(getSupportFragmentManager()));
        mVpContent.setOffscreenPageLimit(4);
        mTabTitle.setupWithViewPager(mVpContent);
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
                    return "可用购物币";
                case 1:
                    return "过期或已使用";
            }
            return "";
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                }

                @Override
                public void onMessage(String message) {
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        LogUtil.d(message);
                        Gson gson = new Gson();
                        mMyGwb = gson.fromJson(message, MyGwb.class);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setToken(SpManager.getToken());
                    parameter.setPageSize("10");
                    more.setUrlMapping("account-myShopCoin");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    mTvGwb.setText(mMyGwb.getResponse().getTotalCoin() + "购物币");
//                    mPaiBi.setText(mMyIncome.getResponse().getPayCoin() + "");
//                    mZengBi.setText(mMyIncome.getResponse().getFreeCoin() + "");
//                    mBuyBi.setText(mMyIncome.getResponse().getShopCoin() + "");
                    break;
            }
        }
    };
}
