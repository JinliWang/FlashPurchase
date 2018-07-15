package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyIncome;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/12.
 */

public class MyIncomeAdapter extends BaseAdapter<MyIncome.ResponseBean.IncomeBean> {
    public MyIncomeAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MyIncome.ResponseBean.IncomeBean> {

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
            mTvName.setText(getData().getPcType());
        }
    }
}
