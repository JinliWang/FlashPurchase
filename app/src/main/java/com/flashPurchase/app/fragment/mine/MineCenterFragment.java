package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.LogUtil;
import com.app.library.view.CircleImageView;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.MyAucBaskActivity;
import com.flashPurchase.app.activity.mine.AdviceActivity;
import com.flashPurchase.app.activity.mine.CenterMessageActivity;
import com.flashPurchase.app.activity.mine.MyAuctionActivity;
import com.flashPurchase.app.activity.mine.MyCollectActivity;
import com.flashPurchase.app.activity.mine.MyGwbActivity;
import com.flashPurchase.app.activity.mine.MyIncomeCenterActivity;
import com.flashPurchase.app.activity.mine.MyInfoActivity;
import com.flashPurchase.app.activity.mine.MyIntegralActivity;
import com.flashPurchase.app.activity.mine.MyOrdersActivity;
import com.flashPurchase.app.activity.mine.SetUpActivity;
import com.flashPurchase.app.activity.mine.SignActivity;
import com.flashPurchase.app.activity.mine.VoucherCenterActivity;
import com.flashPurchase.app.event.HomeEvent;
import com.flashPurchase.app.event.RechargeEvent;
import com.flashPurchase.app.event.SignSuccessEvent;
import com.flashPurchase.app.event.UpdateSuccess;
import com.flashPurchase.app.model.bean.MyInfo;
import com.flashPurchase.app.model.request.MyRequset;
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
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by zms on 2018/4/7.
 */

public class MineCenterFragment extends BaseFragment {
    @BindView(R.id.iv_maine_setup)
    ImageView ivSetup;              //设置
    @BindView(R.id.iv_maine_msg)
    ImageView ivMsg;                //消息
    @BindView(R.id.tv_voucher)
    TextView mTvVoucher;
    @BindView(R.id.lin_paibi)
    LinearLayout mLinPaibi;
    @BindView(R.id.lin_zengbi)
    LinearLayout mLinZengbi;
    @BindView(R.id.lin_gouwubi)
    LinearLayout mLinGouwubi;
    @BindView(R.id.lin_jifen)
    LinearLayout mLinJifen;
    @BindView(R.id.lin_advice)
    RelativeLayout mLinAdvice;
    @BindView(R.id.lin_my_auction)
    RelativeLayout mRelMyAuction;
    @BindView(R.id.home_title)
    RelativeLayout mHomeTitle;
    @BindView(R.id.ll_mine_data)
    LinearLayout mLlMineData;
    @BindView(R.id.iv_iv1)
    ImageView mIvIv1;
    @BindView(R.id.iv_iv2)
    ImageView mIvIv2;
    @BindView(R.id.lin_aucting)
    LinearLayout mLinAucting;
    @BindView(R.id.lin_get)
    LinearLayout mLinGet;
    @BindView(R.id.lin_cjg)
    LinearLayout mLinCjg;
    @BindView(R.id.lin_wait_pay)
    LinearLayout mLinWaitPay;
    @BindView(R.id.lin_wait_sign)
    LinearLayout mLinWaitSign;
    @BindView(R.id.iv_iv3)
    ImageView mIvIv3;
    @BindView(R.id.iv_iv4)
    ImageView mIvIv4;
    @BindView(R.id.iv_iv5)
    ImageView mIvIv5;
    @BindView(R.id.iv_iv7)
    ImageView mIvIv7;
    @BindView(R.id.iv_iv6)
    ImageView mIvIv6;
    @BindView(R.id.ll_mine)
    LinearLayout mLlMine;
    @BindView(R.id.sv_mine)
    ScrollView mSvMine;
    @BindView(R.id.lin_my_collect)
    RelativeLayout mLinMyCollect;
    @BindView(R.id.rel_my_order)
    RelativeLayout mRelMyOrder;
    @BindView(R.id.rel_sign_in)
    RelativeLayout mRelSignIn;
    @BindView(R.id.rel_share)
    RelativeLayout mRelShare;
    @BindView(R.id.iv_photo)
    CircleImageView mIvPhoto;
    @BindView(R.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R.id.tv_id)
    TextView mTvId;
    @BindView(R.id.tv_pai)
    TextView mTvPai;
    @BindView(R.id.tv_free)
    TextView mTvFree;
    @BindView(R.id.tv_shop)
    TextView mTvShop;
    @BindView(R.id.tv_point)
    TextView mTvPoint;


    private WebSocketClient mWebSocketClient;
    private MyInfo mMyInfo;
    private String mPic;
    private String mNickName;
    private String mPhone;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        onClick();
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
                    } else if (message.contains("account-myCenter")) {
                        try {
                            Gson gson = new Gson();
                            mMyInfo = gson.fromJson(message, MyInfo.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            Message msg = new Message();
                            msg.what = 2;
                            handler.sendMessage(msg);
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
                    parameter.setToken(SpManager.getToken());
                    more.setUrlMapping("account-myCenter");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    SpManager.setMyInfo(mMyInfo);
                    mPic = mMyInfo.getResponse().getPic();
                    mTvPai.setText(mMyInfo.getResponse().getPayCoin() + "");
                    mTvFree.setText(mMyInfo.getResponse().getFreeCoin() + "");
                    mTvShop.setText(mMyInfo.getResponse().getShopCoin() + "");
                    mTvPoint.setText(mMyInfo.getResponse().getPoint() + "");
                    mTvNickName.setText(mMyInfo.getResponse().getNickname());
                    mNickName = mMyInfo.getResponse().getNickname();
                    mTvId.setText(mMyInfo.getResponse().getUserId() + "");
                    ImageLoadManager.getInstance().setImage(getContext(), mMyInfo.getResponse().getPic(), mIvPhoto);
                    break;
            }
        }
    };

    private void onClick() {
        final Bundle bundle = new Bundle();
        //设置
        ivSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SetUpActivity.class);
            }
        });

        //消息
        ivMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(CenterMessageActivity.class);
            }
        });

        //充值
        mTvVoucher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(VoucherCenterActivity.class);
            }
        });

        //拍币
        mLinPaibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyIncomeCenterActivity.class);
            }
        });

        //赠币
        mLinZengbi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyIncomeCenterActivity.class);
            }
        });

        //购物币
        mLinGouwubi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyGwbActivity.class);
            }
        });

        //积分
        mLinJifen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyIntegralActivity.class);
            }
        });

        //投诉建议
        mLinAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(AdviceActivity.class);
            }
        });

        //我的竞拍
        mRelMyAuction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("aucSt", "0");
                startActivity(MyAuctionActivity.class, bundle);
            }
        });

        //正在拍
        mLinAucting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("aucSt", "1");
                startActivity(MyAuctionActivity.class, bundle);
            }
        });

        //我拍中
        mLinGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("aucSt", "2");
                startActivity(MyAuctionActivity.class, bundle);
            }
        });

        //差价购
        mLinCjg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("aucSt", "3");
                startActivity(MyAuctionActivity.class, bundle);
            }
        });

        //待付款
        mLinWaitPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("aucSt", "4");
                startActivity(MyAuctionActivity.class, bundle);
            }
        });

        //待晒单
        mLinWaitSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("aucSt", "6");
                startActivity(MyAuctionActivity.class, bundle);
            }
        });

        //我的收藏
        mLinMyCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("type", "collect");
                startActivity(MyCollectActivity.class, bundle);
            }
        });

        //我的晒单
        mRelMyOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(MyAucBaskActivity.class, bundle);
            }
        });

        //签到
        mRelSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(SignActivity.class);
            }
        });

        //我的信息
        mLlMineData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bundle.putString("pic", mPic);
                bundle.putString("name", mNickName);
                startActivity(MyInfoActivity.class, bundle);
            }
        });

        //分享
        mRelShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showShare();
            }
        });
    }

    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，微信、QQ和QQ空间等平台使用
        oks.setTitle("一起分享");
        // titleUrl QQ和QQ空间跳转链接
        oks.setTitleUrl("http://www.tutuwork.com");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("雅德拍卖");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath(Environment.getExternalStorageDirectory() + "ic_launcher.jpg");//确保SDcard下面存在此张图片
        // url在微信、微博，Facebook等平台中使用
        oks.setUrl("http://www.tutuwork.com");
        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
        // 启动分享GUI
        oks.show(getContext());

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RechargeEvent event) {
        MyRequset more = new MyRequset();
        MyRequset.Parameter parameter = new MyRequset.Parameter();
        parameter.setToken(SpManager.getToken());
        more.setUrlMapping("account-myCenter");
        more.setParameter(parameter);
        mWebSocketClient.send(more.myCollect());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SignSuccessEvent event) {
        MyRequset more = new MyRequset();
        MyRequset.Parameter parameter = new MyRequset.Parameter();
        parameter.setToken(SpManager.getToken());
        more.setUrlMapping("account-myCenter");
        more.setParameter(parameter);
        mWebSocketClient.send(more.myCollect());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateSuccess updateSuccess) {
        mTvNickName.setText(SpManager.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
