package com.flashPurchase.app.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.LogUtil;
import com.app.library.util.UIUtil;
import com.app.library.view.MyScrollView;
import com.app.library.view.ScrollGridView;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.FastRechargeActivity;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.activity.goods.TenLimitActivity;
import com.flashPurchase.app.activity.home.RecommendMoreActivity;
import com.flashPurchase.app.activity.mine.SignActivity;
import com.flashPurchase.app.activity.mine.VoucherCenterActivity;
import com.flashPurchase.app.adapter.ComputerListAdapter;
import com.flashPurchase.app.adapter.HomeBannerAdapter;
import com.flashPurchase.app.adapter.PhoneListAdapter;
import com.flashPurchase.app.adapter.RecomendListAdapter;
import com.flashPurchase.app.event.ChangeTabEvent;
import com.flashPurchase.app.event.HomeEvent;
import com.flashPurchase.app.event.HomeInfo;
import com.flashPurchase.app.event.RefreshGoodsEvent;
import com.flashPurchase.app.model.HomeBanner;
import com.flashPurchase.app.model.bean.HomeBean;
import com.flashPurchase.app.util.IOnFocusListenable;
import com.github.wanglu1209.bannerlibrary.Banner;
import com.github.wanglu1209.bannerlibrary.BannerPagerAdapter;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.lang.ref.WeakReference;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 10951 on 2018/4/5.
 */

public class HomeFragment2 extends BaseFragment implements IOnFocusListenable, MyScrollView.OnScrollListener, View.OnClickListener {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
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
    @BindView(R.id.siness_fragment)
    FrameLayout mSinessFragment;
    @BindView(R.id.tab_layout2)
    TabLayout mTabLayout2;
    @BindView(R.id.lin_tab)
    LinearLayout mLinTab;
    @BindView(R.id.scrollView)
    MyScrollView mMyScrollView;

    private List<HomeBean.ResponseBean.PhoneGoodsBean> mList2;
    private List<HomeBean.ResponseBean.ComputerGoodsBean> mComputerGoodsBeans;
    private RecomendListAdapter mAdapter;
    private HomeBannerAdapter mBannerAdapter;
    private PhoneListAdapter mPhoneListAdapter;
    private ComputerListAdapter mComputerListAdapter;

    public WebSocketClient mWebSocketClient;
    private HomeBean mHomeBean;

    private List<String> mTitles;
    private FragmentManager mFragmentManager = null;
    private SparseArray<WeakReference<RecommendFragment3>> childFragments;
    private static int headHeight, headAndTabHeight, tabHeight;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }

    @Override
    protected void initView(View view) {
        mIvLeft.setVisibility(View.GONE);
        EventBus.getDefault().register(this);
//        mViewPage.setAdapter(new HomeListAdapter(getChildFragmentManager()));
//        mViewPage.setOffscreenPageLimit(3);
//        mTabLayout.setupWithViewPager(mViewPage);
        mMyScrollView.setOnScrollListener(this);

        //初始化为你推荐
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecomendList.setLayoutManager(linearLayoutManager);
        mAdapter = new RecomendListAdapter();
        mRecomendList.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void itemClick(int position) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mHomeBean.getResponse().getPreferGoods().get(position).getId());
                bundle.putString("time", mHomeBean.getResponse().getPreferGoods().get(position).getTime());
                if (mHomeBean.getResponse().getPreferGoods().get(position).getStatus().equals("2")) {
                    bundle.putString("isnext", "1");
                }else {
                    bundle.putString("isnext", "0");
                }
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });

        //初始化Banner
        List<HomeBanner.ListBean> beanList = new ArrayList<>();
        final HomeBanner homeBanner = new HomeBanner();
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
        mPhoneListAdapter = new PhoneListAdapter(mList2);
        mPhoneGrid.setAdapter(mPhoneListAdapter);
        mPhoneGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mHomeBean.getResponse().getPhoneGoods().get(i).getId());
                bundle.putString("time", mHomeBean.getResponse().getPhoneGoods().get(i).getTime());
                if (mHomeBean.getResponse().getPhoneGoods().get(i).getStatus().equals("2")) {
                    bundle.putString("isnext", "1");
                }else {
                    bundle.putString("isnext", "0");
                }
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });

        //初始化电脑列表
        mComputerGoodsBeans = new ArrayList<>();
        mComputerListAdapter = new ComputerListAdapter(mComputerGoodsBeans);
        mCompterGrid.setAdapter(mComputerListAdapter);
        mCompterGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mHomeBean.getResponse().getComputerGoods().get(i).getId());
                bundle.putString("time", mHomeBean.getResponse().getComputerGoods().get(i).getTime());
                if (mHomeBean.getResponse().getComputerGoods().get(i).getStatus().equals("2")) {
                    bundle.putString("isnext", "1");
                }else {
                    bundle.putString("isnext", "0");
                }
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });

        mRelRecomend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(RecommendMoreActivity.class);
            }
        });

        mRelPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeEvent event = new HomeEvent();
                event.setType("phone");
                EventBus.getDefault().post(event);
            }
        });

        mRelComputer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeEvent event = new HomeEvent();
                event.setType("computer");
                EventBus.getDefault().post(event);
            }
        });

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int p = tab.getPosition();
                RecommendFragment3 fragment = RecommendFragment3.getIntance(p);
                addFragment(fragment, R.id.siness_fragment);
                ChangeTabEvent event = new ChangeTabEvent();
                event.setP(p);
                EventBus.getDefault().post(event);
                if (mTabLayout2.getTabAt(p) != null) {
                    mTabLayout2.getTabAt(p).select();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        mTabLayout2.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int p = tab.getPosition();
                RecommendFragment3 fragment = RecommendFragment3.getIntance(p);
                addFragment(fragment, R.id.siness_fragment);
                ChangeTabEvent event = new ChangeTabEvent();
                event.setP(p);
                EventBus.getDefault().post(event);
                if (mTabLayout.getTabAt(p) != null) {
                    mTabLayout.getTabAt(p).select();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setTitle();
        mHomeMenu1.setOnClickListener(this);
        mHomeMenu2.setOnClickListener(this);
        mHomeMenu3.setOnClickListener(this);
        mHomeMenu4.setOnClickListener(this);
        mHomeMenu5.setOnClickListener(this);
    }

    private void addFragment(Fragment fragment, @IdRes int containerViewId) {
        if (mFragmentManager == null) {
            mFragmentManager = getFragmentManager();
        }
        mFragmentManager.beginTransaction()
                .add(containerViewId, fragment).commit();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    mWebSocketClient.send("{\"urlMapping\":\"goods-index\"}");
                    break;
                case 1:
                    mAdapter.addData(mHomeBean.getResponse().getPreferGoods());
                    mPhoneListAdapter.refreshData(mHomeBean.getResponse().getPhoneGoods());
                    mComputerListAdapter.refreshData(mHomeBean.getResponse().getComputerGoods());
                    break;
            }
        }
    };

    private void setTitle() {
        mTitles = new ArrayList<>();
        mTitles.add("猜你喜欢");
        mTitles.add("我在拍");
        mTitles.add("我收藏");

        int num = mTitles.size();
        if (num > 0) {
            int index = 0;
            childFragments = new SparseArray<>(num);
            for (int i = 0; i < mTitles.size(); i++) {
                mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)), false);
                childFragments.put(index, providerSellerListFragment(i));
                index++;
            }
            if (mTabLayout.getTabAt(0) != null) {
                mTabLayout.getTabAt(0).select();
            }
        }
        if (num > 0) {
            int index = 0;
            childFragments = new SparseArray<>(num);
            for (int i = 0; i < mTitles.size(); i++) {
                mTabLayout2.addTab(mTabLayout2.newTab().setText(mTitles.get(i)), false);
                childFragments.put(index, providerSellerListFragment(i));
                index++;
            }
            if (mTabLayout2.getTabAt(0) != null) {
                mTabLayout2.getTabAt(0).select();
            }
        }
    }

    @Override
    protected void loadData(Bundle savedInstanceState) {
        super.loadData(savedInstanceState);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://39.104.102.255:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                }

                @Override
                public void onMessage(String message) {
                    LogUtil.d(message);
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        Gson gson = new Gson();
                        mHomeBean = gson.fromJson(message, HomeBean.class);
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

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            headHeight = mBanner.getMeasuredHeight() + mRelRecomend.getMeasuredHeight()
                    + mRelPhone.getMeasuredHeight() + mRelComputer.getMeasuredHeight() + mHomeMenu1.getMeasuredHeight() + UIUtil.dip2px(350);
            headAndTabHeight = headHeight + mTabLayout.getMeasuredHeight();
        }
    }

    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        if (t > headHeight) {
            if (tabHeight == 0)
                tabHeight = mLinTab.getMeasuredHeight() - 1;
            if (t > headAndTabHeight - tabHeight) {
                mLinTab.setVisibility(View.VISIBLE);
            } else {
                mLinTab.setVisibility(View.GONE);
            }
        } else {
            mLinTab.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(HomeInfo event) {
        Gson gson = new Gson();
        mHomeBean = gson.fromJson(event.getInfo(), HomeBean.class);
        mAdapter.setDataList(mHomeBean.getResponse().getPreferGoods());
        mPhoneListAdapter.addData(mHomeBean.getResponse().getPhoneGoods());
        mComputerListAdapter.addData(mHomeBean.getResponse().getComputerGoods());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshGoodsEvent event) {
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        mWebSocketClient.close();
    }

    private WeakReference<RecommendFragment3> providerSellerListFragment(int p) {
        RecommendFragment3 busnissReportFragment = RecommendFragment3.getIntance(p);
        return new WeakReference<>(busnissReportFragment);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.home_menu_1:
                String registTime = SpManager.getUserInfo().getResponse().getRegisterTime();
                long day = 0;
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    Date regist = dateFormat.parse(registTime);
                    Date today = new Date();
                    day = (today.getTime() - regist.getTime()) / (24 * 60 * 60 * 1000);
                    if (day < 7 && SpManager.getMark().equals("0")) {
                        bundle.putString("type", "2");
                    } else {
                        bundle.putString("type", "1");
                    }
                    bundle.putString("money", "100");
                    startActivity(FastRechargeActivity.class, bundle);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.home_menu_2:
                startActivity(SignActivity.class);
                break;
            case R.id.home_menu_3:
                bundle.putString("type", "1");
                startActivity(VoucherCenterActivity.class, bundle);
                break;
            case R.id.home_menu_4:
                startActivity(TenLimitActivity.class);
                break;
            case R.id.home_menu_5:
                break;
        }
    }
}
