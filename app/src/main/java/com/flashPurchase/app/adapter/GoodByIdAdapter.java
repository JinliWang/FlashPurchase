package com.flashPurchase.app.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.base.BaseRecycleHolder;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.GoodClassification;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/7.
 */

public class GoodByIdAdapter extends BaseAdapter<GoodClassification.ResponseBean.GoodsBean> {


    public GoodByIdAdapter(List<GoodClassification.ResponseBean.GoodsBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<GoodClassification.ResponseBean.GoodsBean> {

        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_aucNum)
        TextView mTvAucNum;
        @BindView(R.id.tv_goods_price)
        TextView mTvGoodsPrice;
        @BindView(R.id.tv_goods_name)
        TextView mTvGoodsName;
        @BindView(R.id.btn_pai)
        Button mBtnPai;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_goods_by_id);
        }

        @Override
        protected void refreshView() {
            ImageLoadManager.getInstance().setImage(getContext(), getData().getPics(), mIvGoods);
            mTvAucNum.setText(getData().getAucNum());
            mTvGoodsPrice.setText(getData().getCurrentPrice());
            mTvGoodsName.setText(getData().getName());
        }
    }
}
