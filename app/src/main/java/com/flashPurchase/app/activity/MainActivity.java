package com.flashPurchase.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.ActivityManager;
import com.app.library.util.LogUtil;
import com.app.library.view.MyViewPager;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.HomeEvent;
import com.flashPurchase.app.event.HomeInfo;
import com.flashPurchase.app.fragment.classification.GoodsClassificationFragment;
import com.flashPurchase.app.fragment.home.HomeFragment;
import com.flashPurchase.app.fragment.home.HomeFragment2;
import com.flashPurchase.app.fragment.mine.MineCenterFragment;
import com.flashPurchase.app.fragment.dynamic.NewNitificaDynamicFragment;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.util.IOnFocusListenable;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.vp_tab_content)
    MyViewPager mVpTabContent;
    @BindView(R.id.rg_container)
    RadioGroup mRgContainer;

    // 退出时间
    private long exitTime = 0;
    private WebSocketClient mWebSocketClient;
    private String mMessage;
    HomeFragment2 homeFragment2 = new HomeFragment2();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        mVpTabContent.setAdapter(mAdapter);
        mVpTabContent.setCurrentItem(0);
        mVpTabContent.setScrollable(false);
        mVpTabContent.setOffscreenPageLimit(5);

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
                    case R.id.rb_buy_again:
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
                    return homeFragment2;
                case 1:
                    //最新动态
                    return new NewNitificaDynamicFragment();
                case 2:
                    //商品分类
                    return new GoodsClassificationFragment();
                case 3:
                    //拍品回购
                    return new MineCenterFragment();
                case 4:
                    //个人中心
                    return new MineCenterFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 5;
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
                    return "拍品回购";
                case 4:
                    return "个人中心";
            }
            return "";
        }
    };

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HomeEvent event) {
        mVpTabContent.setCurrentItem(2);
        mRgContainer.check(R.id.rb_goods_type);
    }

//    @Override
//    protected void initData(Bundle bundle) {
//        super.initData(bundle);
//        try {
//            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
//                @Override
//                public void onOpen(ServerHandshake handshakedata) {
//                }
//
//                @Override
//                public void onMessage(String message) {
//                    LogUtil.d(message);
//                    if (!message.contains("response")) {
//                        Message msg = new Message();
//                        msg.what = 0;
//                        handler.sendMessage(msg);
//                    } else {
//                        HomeInfo info = new HomeInfo();
//                        info.setInfo(message);
//                        EventBus.getDefault().post(info);
//                    }
//                }
//
//                @Override
//                public void onClose(int code, String reason, boolean remote) {
//
//                }
//
//                @Override
//                public void onError(Exception ex) {
//
//                }
//            };
//            mWebSocketClient.connect();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what) {
//                case 0:
//                    MyRequset requset = new MyRequset();
//                    requset.setUrlMapping("goods-index");
//                    mWebSocketClient.send(requset.noPatameter());
//                    break;
//                case 1:
//
//                    break;
//            }
//        }
//    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(homeFragment2 instanceof IOnFocusListenable) {
            ((IOnFocusListenable) homeFragment2).onWindowFocusChanged(hasFocus);
        }
    }
}
