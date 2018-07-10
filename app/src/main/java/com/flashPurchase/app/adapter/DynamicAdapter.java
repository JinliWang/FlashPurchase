package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.Dynamics;
import com.flashPurchase.app.model.bean.RecentDeal;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/5/3.
 */

public class DynamicAdapter extends BaseAdapter<RecentDeal.ResponseBean.DealRecordsBean> {
    public DynamicAdapter(List<RecentDeal.ResponseBean.DealRecordsBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<RecentDeal.ResponseBean.DealRecordsBean> {

        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_goods_name)
        TextView mTvGoodsName;
        @BindView(R.id.tv_comsumer)
        TextView mTvComsumer;
        @BindView(R.id.tv_market_price)
        TextView mTvMarketPrice;
        @BindView(R.id.tv_current_price)
        TextView mTvCurrentPrice;
        @BindView(R.id.tv_rate)
        TextView mTvRate;
        @BindView(R.id.tv_pai)
        TextView mTvPai;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_dynamic);
        }

        @Override
        protected void refreshView() {
            ImageLoadManager.getInstance().setImage(getContext(),getData().getPics(),mIvGoods);
            mTvComsumer.setText("成交人：" + getData().getNickname());
            mTvCurrentPrice.setText("￥" + getData().getFinalPrice());
            mTvGoodsName.setText(getData().getGoodsName());
            mTvMarketPrice.setText("￥" + getData().getMarketPrice());
            mTvTime.setText(getData().getTime());
            mTvRate.setText(getData().getSaveRatio());
        }
    }
}
