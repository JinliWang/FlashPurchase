package com.flashPurchase.app.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseRecycleHolder;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.ImageLoadManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/7.
 */

public class MyCollectAdapter extends BaseRecyclerAdapter<RecommendMoreResponse.ResponseBean.GoodsBean> {
    @Override
    public BaseRecycleHolder setViewHolder(ViewGroup parent) {
        return new Holder(parent.getContext(), parent);
    }

    class Holder extends BaseRecycleHolder<RecommendMoreResponse.ResponseBean.GoodsBean> {


        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_price)
        TextView mTvPrice;
        @BindView(R.id.tv_customer)
        TextView mTvCustomer;
        @BindView(R.id.btn_pai)
        Button mBtnPai;

        public Holder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_my_collect);
        }

        @Override
        public void bindData(RecommendMoreResponse.ResponseBean.GoodsBean preferGoodsBean) {
            ImageLoadManager.getInstance().setImage(getContext(), preferGoodsBean.getPics(), mIvGoods);
            mTvCustomer.setText(preferGoodsBean.getName());
            mTvPrice.setText(preferGoodsBean.getCurrentPrice());

        }
    }
}
