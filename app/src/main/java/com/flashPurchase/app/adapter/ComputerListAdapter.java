package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.HomeBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/9.
 */

public class ComputerListAdapter extends BaseAdapter<HomeBean.ResponseBean.ComputerGoodsBean> {
    public ComputerListAdapter(List<HomeBean.ResponseBean.ComputerGoodsBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<HomeBean.ResponseBean.ComputerGoodsBean> {

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
            ImageLoadManager.getInstance().setImage(getContext(), getData().getPics(), mImgGoods);
            mTvGoodsPrice.setText(getData().getCurrentPrice());
        }
    }
}
