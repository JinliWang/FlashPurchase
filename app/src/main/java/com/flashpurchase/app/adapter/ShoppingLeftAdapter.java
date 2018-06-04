package com.flashpurchase.app.adapter;

import android.view.View;
import android.widget.TextView;


import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.UIUtil;
import com.flashpurchase.app.R;
import com.flashpurchase.app.model.Dynamics;

import java.util.List;

import butterknife.BindView;

/**
 * Created by admin on 8/21 0021.
 */

public class ShoppingLeftAdapter extends BaseAdapter<Dynamics> {

    public ShoppingLeftAdapter(List<Dynamics> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<Dynamics> {

        @BindView(R.id.tv_left_name)
        TextView tvLeftName;

        @Override
        protected View initView() {
            View view = UIUtil.inflate(R.layout.item_shopping_left);

            return view;
        }

        @Override
        protected void refreshView() {
            tvLeftName.setText(getData().getDynamicName());

        }
    }
}
