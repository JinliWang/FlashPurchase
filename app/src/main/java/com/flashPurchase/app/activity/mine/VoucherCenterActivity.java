package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.VoucherAdapter;
import com.flashPurchase.app.model.VoucherMoney;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/5/27.
 */

public class VoucherCenterActivity extends BaseActivity {


    @BindView(R.id.voucher_list)
    RecyclerView mVoucherList;
    @BindView(R.id.tv_money1)
    TextView mTvMoney1;
    @BindView(R.id.tv_money2)
    TextView mTvMoney2;
    @BindView(R.id.tv_zfb)
    TextView mTvZfb;
    @BindView(R.id.cb_zfb)
    CheckBox mCbZfb;
    @BindView(R.id.cb_wx)
    CheckBox mCbWx;
    @BindView(R.id.cb_agreement)
    CheckBox mCbAgreement;
    @BindView(R.id.tv_agreement)
    TextView mTvAgreement;
    @BindView(R.id.btn_sure)
    Button mBtnSure;
    @BindView(R.id.zfb_pay_layout)
    RelativeLayout mZfbPayLayout;
    @BindView(R.id.wx_pay_layout)
    RelativeLayout mWxPayLayout;


    private VoucherAdapter mVoucherAdapter;
    private boolean isZFB = true;//选择支付方式
    private String mMoney;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_voucher_center;
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        initTitle("充值中心");
        mVoucherAdapter = new VoucherAdapter(this);
        mVoucherList.setHasFixedSize(true);
        //recycleview设置布局方式，GridView (一行三列)
        mVoucherList.setLayoutManager(new GridLayoutManager(this, 3));
        mVoucherList.setAdapter(mVoucherAdapter);
        mVoucherAdapter.replaceAll(getData(), this);
        initClick();
    }

    private void initClick() {
        mZfbPayLayout.setOnClickListener(this);
        mWxPayLayout.setOnClickListener(this);
        mBtnSure.setOnClickListener(this);
    }

    public ArrayList<VoucherMoney> getData() {
        String data = "50,100,200,300,500";
        String dataArr[] = data.split(",");
        ArrayList<VoucherMoney> list = new ArrayList<>();
        for (int i = 0; i < dataArr.length; i++) {
            String count = dataArr[i];
            list.add(new VoucherMoney(VoucherMoney.TWO, count));
        }
        list.add(new VoucherMoney(VoucherMoney.THREE, null));

        return list;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getAdapterClickInfo(VoucherMoney model) {
        String money = model.data.toString();
        mMoney = money;
        mTvMoney1.setText(money + "拍币");
        Double d = new Double(money);
        mTvMoney2.setText("￥" + d.toString());

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.zfb_pay_layout:
                isZFB = true;
                mCbZfb.setChecked(true);
                mCbWx.setChecked(false);
                break;
            case R.id.wx_pay_layout:
                isZFB = false;
                mCbZfb.setChecked(false);
                mCbWx.setChecked(true);
                break;
            case R.id.btn_sure:
                if (!mCbAgreement.isChecked()) {
                    ToastUtil.show("请确认您已同意用户协议！");
                    return;
                }
                if (TextUtils.isEmpty(mMoney)) {
                    ToastUtil.show("请选择一个数额进行充值！");
                    return;
                }
                if(isZFB) {

                }else {

                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
