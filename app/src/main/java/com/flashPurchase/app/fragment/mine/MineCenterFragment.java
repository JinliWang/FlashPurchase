package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.mine.AdviceActivity;
import com.flashPurchase.app.activity.mine.CenterMessageActivity;
import com.flashPurchase.app.activity.mine.MyAuctionActivity;
import com.flashPurchase.app.activity.mine.MyCollectActivity;
import com.flashPurchase.app.activity.mine.MyGwbActivity;
import com.flashPurchase.app.activity.mine.MyIncomeCenterActivity;
import com.flashPurchase.app.activity.mine.MyIntegralActivity;
import com.flashPurchase.app.activity.mine.SetUpActivity;
import com.flashPurchase.app.activity.mine.VoucherCenterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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
    @BindView(R.id.lin_my_sign)
    RelativeLayout mLinMySign;
    @BindView(R.id.rel_sign_in)
    RelativeLayout mRelSignIn;
    @BindView(R.id.rel_share)
    RelativeLayout mRelShare;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    protected void initView(View view) {
        onClick();
    }

    private void onClick() {
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
                startActivity(MyAuctionActivity.class);
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
                startActivity(MyCollectActivity.class);
            }
        });



    }
}
