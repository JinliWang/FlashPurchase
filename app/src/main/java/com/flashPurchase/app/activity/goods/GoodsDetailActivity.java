package com.flashPurchase.app.activity.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.AucSuccessEvent;
import com.flashPurchase.app.event.RefreshGoodsEvent;
import com.flashPurchase.app.model.bean.Collect;
import com.flashPurchase.app.model.bean.GoodDetail;
import com.flashPurchase.app.model.bean.ToRecharge;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;
import com.octopus.amountview.AmountView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/5/2.
 */

public class GoodsDetailActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView mWebView;
    @BindView(R.id.iv_no_select)
    ImageView mIvNoSelect;
    @BindView(R.id.iv_select)
    ImageView mIvSelect;
    @BindView(R.id.tv_collect)
    TextView mTvCollect;
    @BindView(R.id.lin_chujia)
    LinearLayout mLinChujia;
    @BindView(R.id.amount_view)
    AmountView mAmountView;
    @BindView(R.id.lin_collect)
    LinearLayout mLinCollect;
    @BindView(R.id.lin_next)
    LinearLayout mLinNext;
    @BindView(R.id.tv_paibi)
    TextView mTvPaibi;
    @BindView(R.id.lin_now)
    LinearLayout mLinNow;
    @BindView(R.id.lin_pai)
    LinearLayout mLinPai;

    private String mGoodsId;
    private String mTime;
    private String mUrl;
    private int mAucTime = 1;
    private GoodDetail mGoodDetail;
    private Collect mCollect;
    private ToRecharge mToRecharge;

    private boolean isCollected = false;

    private WebSocketClient mWebSocketClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_detail;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initTitle("商品详情");
        mGoodsId = extraDatas.getString("goodsid");
        mTime = extraDatas.getString("time");
        mUrl = "http://39.104.102.255:8089/detail?token=" + SpManager.getToken() + "&goodsId=" + mGoodsId + "&time=" + mTime;
//        mUrl = "http://39.104.102.255:8089/detail?token=" + SpManager.getToken() + "&goodsId=" + "1" + "&time=" + "3";
        // 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true);
        //自适应屏幕`
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setAllowContentAccess(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;//返回值为true时在WebView中打开，为false时调用浏览器打开
            }
        });
        mWebView.loadUrl(mUrl);

        mLinCollect.setOnClickListener(this);
        mLinChujia.setOnClickListener(this);
        mLinNext.setOnClickListener(this);
        mAmountView.setOnChangeListener(new AmountView.OnChangeListener() {
            @Override
            public void onChanged(int value) {
                mAucTime = value;
            }
        });
    }

    //使用Webview的时候，返回键没有重写的时候会直接关闭程序，这时候其实我们要其执行的知识回退到上一步的操作
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack()) {
            mWebView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.lin_collect:
                MyRequset more = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setGoodsId(mGoodsId);
                parameter.setToken(SpManager.getToken());
                more.setParameter(parameter);
                if (isCollected) {
                    more.setUrlMapping("collect-cancel");
                } else {
                    more.setUrlMapping("collect-add");
                }
                mWebSocketClient.send(more.collect());
                break;
            case R.id.lin_chujia:
                MyRequset requset = new MyRequset();
                MyRequset.Parameter parameter1 = new MyRequset.Parameter();
                parameter1.setAucTime(mAucTime + "");
                parameter1.setGoodsId(mGoodsId);
                parameter1.setToken(SpManager.getToken());
                requset.setParameter(parameter1);
                requset.setUrlMapping("auc-bid");
                mWebSocketClient.send(requset.auc());
                break;
            case R.id.lin_next:
                MyRequset requset1 = new MyRequset();
                MyRequset.Parameter parameter2 = new MyRequset.Parameter();
                parameter2.setToken(SpManager.getToken());
                parameter2.setGoodsId(mGoodsId);
                parameter2.setTime(mTime);
                parameter2.setIsNext("1");
                requset1.setUrlMapping("goods-goodsDetail");
                requset1.setParameter(parameter2);
                mWebSocketClient.send(requset1.goodsDetail());
                break;
        }
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
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
                    } else if (message.contains("goods-goodsDetail")) {//返回收藏状态，判断是否收藏
                        Gson gson = new Gson();
                        mGoodDetail = gson.fromJson(message, GoodDetail.class);
                        if (mGoodDetail.getResponse() != null) {
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        }
                    } else if (message.contains("collect-add") || message.contains("collect-cancel")) {//收藏或者取消收藏结果
                        Gson gson = new Gson();
                        mCollect = gson.fromJson(message, Collect.class);
                        Message msg = new Message();
                        msg.what = 2;
                        handler.sendMessage(msg);
                    } else if (message.contains("auc-bid")) {//点击出价，判断是否需要充值
                        Gson gson = new Gson();
                        if (message.contains("turnRecharge")) {//跳转充值标志
                            mToRecharge = gson.fromJson(message, ToRecharge.class);
                            Message msg = new Message();
                            msg.what = 3;
                            handler.sendMessage(msg);
                        } else {//出价成功，通知刷新页面
                            Message msg = new Message();
                            msg.what = 4;
                            handler.sendMessage(msg);
                        }
                    } else if (message.contains("auc-success-user")) {
                        Message msg = new Message();
                        msg.what = 5;
                        msg.obj = message;
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
                case 0://获取收藏状态
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setToken(SpManager.getToken());
                    parameter.setGoodsId(mGoodsId);
                    parameter.setTime(mTime);
                    parameter.setIsNext("");
                    more.setUrlMapping("goods-goodsDetail");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.goodsDetail());
                    break;
                case 1:
                    if (mGoodDetail.getResponse().getCollect() == 0) {
                        isCollected = false;
                    } else if (mGoodDetail.getResponse().getCollect() == 1) {
                        isCollected = true;
                        collect();
                    }
                    mLinNow.setVisibility(View.VISIBLE);
                    mLinNext.setVisibility(View.GONE);
                    if(mGoodDetail.getResponse().getIsTen() == 0) {
                        mTvPaibi.setText("1拍币/次");
                    }else if(mGoodDetail.getResponse().getIsTen() == 1) {
                        mTvPaibi.setText("10拍币/次");
                    }
                    break;
                case 2:
                    if (mCollect.getResponse().equals("已收藏")) {
                        collect();
                        isCollected = true;
                    } else if (mCollect.getResponse().equals("已取消收藏")) {
                        noCollect();
                        isCollected = false;
                    }
                    break;
                case 3:
                    Bundle bundle = new Bundle();
                    bundle.putString("money", mToRecharge.getResponse().getNeedToPay() + "");
                    startActivity(FastRechargeActivity.class, bundle);
                    break;
                case 4:
                    EventBus.getDefault().post(new RefreshGoodsEvent());
                    break;
                case 5:
                    ToastUtil.show("恭喜你获得拍品，前往个人中心我的竞拍中查看");
                    mLinNow.setVisibility(View.GONE);
                    mLinNext.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    private void collect() {
        mIvSelect.setVisibility(View.VISIBLE);
        mIvNoSelect.setVisibility(View.GONE);
        mTvCollect.setText("已收藏");
    }

    private void noCollect() {
        mIvSelect.setVisibility(View.GONE);
        mIvNoSelect.setVisibility(View.VISIBLE);
        mTvCollect.setText("收藏");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(AucSuccessEvent event) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
        EventBus.getDefault().unregister(this);
    }
}
