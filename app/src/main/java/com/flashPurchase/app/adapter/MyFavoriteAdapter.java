package com.flashPurchase.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.library.base.BaseRecycleHolder;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.ImageLoadManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyOrder;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/8.
 */

public class MyFavoriteAdapter extends BaseRecyclerAdapter<MyOrder.ResponseBean> {
    @Override
    public BaseRecycleHolder setViewHolder(ViewGroup parent) {
        return new MyFavoriteAdapter.Holder(parent.getContext(), parent);
    }

    class Holder extends BaseRecycleHolder<MyOrder.ResponseBean> {

        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_gooods_title)
        TextView mTvGooodsTitle;
        @BindView(R.id.tv_goods_price)
        TextView mTvGoodsPrice;
        @BindView(R.id.tv_people_num)
        TextView mTvPeopleNum;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.lin_pai)
        LinearLayout mLinPai;

        public Holder(Context context, ViewGroup root) {
            super(context, root, R.layout.item_goods_cls);
        }

        @Override
        public void bindData(MyOrder.ResponseBean preferGoodsBean) {
            mLinPai.setVisibility(View.GONE);
            ImageLoadManager.getInstance().setImage(getContext(), preferGoodsBean.getPics(), mIvGoods);
            mTvGooodsTitle.setText(preferGoodsBean.getGoodsName());
            mTvGoodsPrice.setText(preferGoodsBean.getCurrentPrice() + "");
            mTvTime.setText(preferGoodsBean.getAucTime()+ "");
        }
    }
}
