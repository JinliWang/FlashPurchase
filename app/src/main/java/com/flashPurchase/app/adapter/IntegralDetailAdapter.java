package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyIncome;
import com.flashPurchase.app.model.bean.MyIntegral;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/16.
 */

public class IntegralDetailAdapter extends BaseAdapter<MyIntegral.ResponseBean.IncomeBean> {
    public IntegralDetailAdapter(List<MyIntegral.ResponseBean.IncomeBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MyIntegral.ResponseBean.IncomeBean> {

        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_detail)
        TextView mTvDetail;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_deal_detail);
        }

        @Override
        protected void refreshView() {
            switch (getData().getPcType()) {
                case 1:
                    mTvName.setText("注册赠送");
                    break;
                case 2:
                    mTvName.setText("充值获得");
                    break;
                case 3:
                    mTvName.setText("竞拍消耗/返还");
                    break;
                case 4:
                    mTvName.setText("签到赠送");
                    break;
                case 5:
                    mTvName.setText("积分兑换");
                    break;
                case 6:
                    mTvName.setText("差价购");
                    break;
            }
            switch (getData().getCoinType()) {
                case 1:
                    if (getData().getType() == 1) {
                        mTvDetail.setText("+" + getData().getAmount() + "拍币");
                    } else {
                        mTvDetail.setText("-" + getData().getAmount() + "拍币");
                    }
                    break;
                case 2:
                    if (getData().getType() == 1) {
                        mTvDetail.setText("+" + getData().getAmount() + "赠币");
                    } else {
                        mTvDetail.setText("-" + getData().getAmount() + "赠币");
                    }
                    break;
                case 3:
                    if (getData().getType() == 1) {
                        mTvDetail.setText("+" + getData().getAmount() + "购物币");
                    } else {
                        mTvDetail.setText("-" + getData().getAmount() + "购物币");
                    }
                    break;
                case 4:
                    if (getData().getType() == 1) {
                        mTvDetail.setText("+" + getData().getAmount() + "积分");
                    } else {
                        mTvDetail.setText("-" + getData().getAmount() + "积分");
                    }
                    break;
            }
            mTvTime.setText(getData().getCreateTime());
        }
    }
}
