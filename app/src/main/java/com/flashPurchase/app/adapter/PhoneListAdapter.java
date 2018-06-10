package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.HomeList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/9.
 */

public class PhoneListAdapter extends BaseAdapter<HomeList> {
    public PhoneListAdapter(List<HomeList> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<HomeList> {

        @BindView(R.id.img_goods)
        ImageView mImgGoods;
        @BindView(R.id.img_saled)
        ImageView mImgSaled;
        @BindView(R.id.tv_goods_price)
        TextView mTvGoodsPrice;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_home_son_list);
        }

        @Override
        protected void refreshView() {
            mTvGoodsPrice.setText(getData().getGoodsPrice());
        }
    }
}
