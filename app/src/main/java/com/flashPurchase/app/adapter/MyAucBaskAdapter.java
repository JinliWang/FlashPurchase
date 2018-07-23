package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyBask;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/20.
 */

public class MyAucBaskAdapter extends BaseAdapter<MyBask.ResponseBean.CommentsBean> {
    public MyAucBaskAdapter(List<MyBask.ResponseBean.CommentsBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MyBask.ResponseBean.CommentsBean> {

        @BindView(R.id.iv_goods)
        ImageView mIvGoods;
        @BindView(R.id.tv_goods_name)
        TextView mTvGoodsName;
        @BindView(R.id.tv_date)
        TextView mTvDate;
        @BindView(R.id.tv_goods_comments)
        TextView mTvGoodsComments;
        @BindView(R.id.tv_status)
        TextView mTvStatus;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_my_bask);
        }

        @Override
        protected void refreshView() {
            ImageLoadManager.getInstance().setImage(getContext(), getData().getPics(), mIvGoods);
            mTvGoodsComments.setText(getData().getComments());
            mTvGoodsName.setText(getData().getName());
            mTvDate.setText(getData().getCreateTime());
            switch (getData().getStatus()) {
                case 1:
                    mTvStatus.setText("审核中");
                    break;
                case 2:
                    mTvStatus.setText("审核通过");
                    break;
                case 3:
                    mTvStatus.setText("审核失败");
                    break;
            }
        }
    }
}
