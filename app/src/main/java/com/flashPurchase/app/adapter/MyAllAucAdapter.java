package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.bean.MyAllAucList;
import com.flashPurchase.app.model.bean.MyAucList;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/13.
 */

public class MyAllAucAdapter extends BaseAdapter<MyAllAucList.ResponseBean.AucIngBean> {
    public MyAllAucAdapter(List<MyAllAucList.ResponseBean.AucIngBean> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MyAllAucList.ResponseBean.AucIngBean> {

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

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_my_auc);
        }

        @Override
        protected void refreshView() {
            mTvMarketPrice.setText("市场价：￥" + getData().getMarketPrice());
            mTvPaimaiPrice.setText("￥" + getData().getCurrentPrice());
            mTvAucTime.setText("我出价：" + getData().getAucTime() + "次");
            ImageLoadManager.getInstance().setImage(getContext(), getData().getPics(), mIvPhoto);
            switch (getData().getStatus()) {
                case "aucing":

                    break;
                case "aucout":
                    break;
                case "aucsuc":
                    break;
                case "auctoback":
                    break;
                case "auctopay":
                    break;
                case "auctoreceive":
                    break;

            }
        }
    }
}
