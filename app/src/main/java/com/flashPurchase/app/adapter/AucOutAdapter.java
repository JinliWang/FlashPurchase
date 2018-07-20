package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyAucList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/13.
 */

public class AucOutAdapter extends BaseAdapter<MyAucList.ResponseBean> {
    public AucOutAdapter(List<MyAucList.ResponseBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MyAucList.ResponseBean> implements View.OnClickListener {

        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_status)
        TextView mTvStatus;
        @BindView(R.id.iv_photo)
        ImageView mIvPhoto;
        @BindView(R.id.tv_market_price)
        TextView mTvMarketPrice;
        @BindView(R.id.tv_paimai_price)
        TextView mTvPaimaiPrice;
        @BindView(R.id.tv_auc_time)
        TextView mTvAucTime;
        @BindView(R.id.tv_pai)
        TextView mTvPai;
        @BindView(R.id.tv_cancel)
        TextView mTvCancel;
        @BindView(R.id.tv_wuliu)
        TextView mTvWuliu;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_my_auc);
        }

        @Override
        protected void refreshView() {
            mTvTitle.setText(getData().getDate());
            mTvStatus.setVisibility(View.GONE);
            mTvMarketPrice.setText("市场价：￥" + getData().getMarketPrice());
            mTvPaimaiPrice.setText("￥" + getData().getFinalPrice());
            mTvAucTime.setText("成交人：" + getData().getDealUser());
            ImageLoadManager.getInstance().setImage(getContext(), getData().getPics(), mIvPhoto);
            mTvPai.setText("差价购买");
            mTvPai.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.itemClick(view, getPosition());
        }
    }
}
