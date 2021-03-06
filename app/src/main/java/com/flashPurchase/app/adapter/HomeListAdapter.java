package com.flashPurchase.app.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseRecycleHolder;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.ImageLoadManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.HomeList;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/6.
 */

public class HomeListAdapter extends BaseRecyclerAdapter<RecommendMoreResponse.ResponseBean.GoodsBean> {
    @Override
    public BaseRecycleHolder setViewHolder(ViewGroup parent) {
        return new Holder(parent.getContext(), parent);
    }

    class Holder extends BaseRecycleHolder<RecommendMoreResponse.ResponseBean.GoodsBean> {

        @BindView(R.id.img_goods)
        ImageView mImgGoods;
        @BindView(R.id.img_saled)
        ImageView mImgSaled;
        @BindView(R.id.tv_goods_name)
        TextView mTvGoodsName;
        @BindView(R.id.tv_goods_price)
        TextView mTvGoodsPrice;

        public Holder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_home_list);
        }

        @Override
        public void bindData(RecommendMoreResponse.ResponseBean.GoodsBean homeList) {
            ImageLoadManager.getInstance().setImage(getContext(), homeList.getPics(), mImgGoods);
            mTvGoodsName.setText(homeList.getName());
            mTvGoodsPrice.setText(homeList.getCurrentPrice());
        }
    }
}
