package com.flashPurchase.app.activity.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.WuliuDetail;
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
 * Created by 10951 on 2018/7/18.
 */

public class WuLiuActivity extends BaseActivity {

    @BindView(R.id.tv_status)
    TextView mTvStatus;
    @BindView(R.id.iv_goods)
    ImageView mIvGoods;
    @BindView(R.id.tv_market_price)
    TextView mTvMarketPrice;
    @BindView(R.id.tv_gwb)
    TextView mTvGwb;
    @BindView(R.id.tv_act_price)
    TextView mTvActPrice;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    private WebSocketClient mWebSocketClient;
    private String id;
    private WuliuDetail mWuliuDetail;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wu_liu;
    }

    @Override
    protected void initView() {
        initTitle("物流详情");
        id = extraDatas.getString("id");
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
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        LogUtil.d(message);
                        try {
                            Gson gson = new Gson();
                            mWuliuDetail = gson.fromJson(message, WuliuDetail.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } catch (Exception e) {

                        }
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
                    parameter.setOrderId(id);
                    more.setUrlMapping("order-orderLogisticsDetail");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.wuliu());
                    break;
                case 1:
                    switch (mWuliuDetail.getResponse().getOrderSt()) {
                        case 1:
                            mTvStatus.setText("未付款");
                            break;
                        case 2:
                            mTvStatus.setText("审核中");
                            break;
                        case 3:
                            mTvStatus.setText("待发货");
                            break;
                        case 4:
                            mTvStatus.setText("已发货");
                            break;
                        case 5:
                            mTvStatus.setText("待签收");
                            break;
                        case 6:
                            mTvStatus.setText("待晒单");
                            break;
                        case 7:
                            mTvStatus.setText("已晒单");
                            break;
                    }
                    ImageLoadManager.getInstance().setImage(WuLiuActivity.this, mWuliuDetail.getResponse().getPics(), mIvGoods);
                    mTvMarketPrice.setText("￥" + mWuliuDetail.getResponse().getMarketPrice());
                    mTvGwb.setText("￥" + mWuliuDetail.getResponse().getShopCoin());
                    mTvActPrice.setText("￥" + mWuliuDetail.getResponse().getActualPayment());
                    mTvName.setText("收件人：" + mWuliuDetail.getResponse().getNickname());
                    mTvPhone.setText(mWuliuDetail.getResponse().getPhone());
                    mTvAddress.setText("收货地址：" + mWuliuDetail.getResponse().getRegion() + mWuliuDetail.getResponse().getSpecificAddress());
                    break;
            }
        }
    };
}
