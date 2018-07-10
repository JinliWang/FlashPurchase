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
import com.flashPurchase.app.model.bean.HomeBean;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/6.
 */

public class RecomendListAdapter extends BaseRecyclerAdapter<HomeBean.ResponseBean.PreferGoodsBean> {
    @Override
    public BaseRecycleHolder setViewHolder(ViewGroup parent) {
        return new Holder(parent.getContext(), parent);
    }

    class Holder extends BaseRecycleHolder<HomeBean.ResponseBean.PreferGoodsBean> {


        @BindView(R.id.img_goods)
        ImageView mImgGoods;
        @BindView(R.id.img_saled)
        ImageView mImgSaled;
        @BindView(R.id.tv_goods_price)
        TextView mTvGoodsPrice;

        public Holder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_recomend_list);
        }

        @Override
        public void bindData(HomeBean.ResponseBean.PreferGoodsBean homeList) {
            ImageLoadManager.getInstance().setImage(getContext(), homeList.getPics(), mImgGoods);
            mTvGoodsPrice.setText(homeList.getCurrentPrice());
        }
    }
}
