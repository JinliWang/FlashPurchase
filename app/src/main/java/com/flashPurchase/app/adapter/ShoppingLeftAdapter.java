package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.TextView;


import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.Dynamics;
import com.flashPurchase.app.model.bean.GoodClassification;

import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 8/21 0021.
 */

public class ShoppingLeftAdapter extends BaseAdapter<GoodClassification.ResponseBean.GoodsCategoriesBean> {

    public ShoppingLeftAdapter(List<GoodClassification.ResponseBean.GoodsCategoriesBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<GoodClassification.ResponseBean.GoodsCategoriesBean> {

        @BindView(R.id.tv_left_name)
        TextView tvLeftName;

        @Override
        protected View initView() {
            View view = UIUtil.inflate(R.layout.item_shopping_left);
            return view;
        }

        @Override
        protected void refreshView() {
            tvLeftName.setText(getData().getName());

        }
    }
}
