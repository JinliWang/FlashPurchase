package com.flashPurchase.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseRecycleHolder;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.ImageLoadManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.TenLimit;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/7.
 */

public class TenLimittAdapter extends BaseRecyclerAdapter<TenLimit.ResponseBean.GoodsBean> {
    @Override
    public BaseRecycleHolder setViewHolder(ViewGroup parent) {
        return new Holder(parent.getContext(), parent);
    }

    class Holder extends BaseRecycleHolder<TenLimit.ResponseBean.GoodsBean> implements View.OnClickListener {


        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_price)
        TextView mTvPrice;
        @BindView(R.id.tv_customer)
        TextView mTvCustomer;
        @BindView(R.id.btn_pai)
        Button mBtnPai;
        @BindView(R.id.tv_collect)
        TextView mTvCollect;

        public Holder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_ten_limit);
        }

        @Override
        public void bindData(TenLimit.ResponseBean.GoodsBean preferGoodsBean) {
            ImageLoadManager.getInstance().setImage(getContext(), preferGoodsBean.getPics(), mIvGoods);
            mTvPrice.setText(preferGoodsBean.getCurrentPrice() + "");
            if (preferGoodsBean.getCollect() == 0) {
                mTvCollect.setVisibility(View.GONE);
            } else if (preferGoodsBean.getCollect() == 1) {
                mTvCollect.setVisibility(View.VISIBLE);
            }
            mBtnPai.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.itemClick(getPosition());
        }
    }
}
