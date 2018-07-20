package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyGwb;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/17.
 */

public class GwbUseDetailAdapter extends BaseAdapter<MyGwb.ResponseBean.JaUseBean> {
    public GwbUseDetailAdapter(List<MyGwb.ResponseBean.JaUseBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MyGwb.ResponseBean.JaUseBean> {

        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_goods_name)
        TextView mTvGoodsName;
        @BindView(R.id.tv_gwb_back)
        TextView mTvGwbBack;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_gwb_detail);
        }

        @Override
        protected void refreshView() {
            mTvTime.setText(getData().getCreateTime());
            if (getData().getStatus() == 1) {
                mTvStatus.setText("可用");
            } else {
                mTvStatus.setText("已过期或已使用");
            }
            mTvGoodsName.setText(getData().getName());
            mTvGwbBack.setText("返还" + getData().getShopCoin() + "购物币");
            ImageLoadManager.getInstance().setImage(getContext(), getData().getPics(), mIvGoods);
        }
    }
}
