package com.flashPurchase.app.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.util.ImageLoadManager;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.AucBaskActivity;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.activity.goods.WuLiuActivity;
import com.flashPurchase.app.activity.mine.ComfirmOrderActivity;
import com.flashPurchase.app.model.bean.MyAllAucList;

import java.util.List;

/**
 * Created by 10951 on 2018/7/17.
 */

public class AllAucAdapter extends BaseAdapter implements View.OnClickListener {
    private List<MyAllAucList.ResponseBean.AucIngBean> mList;
    private static final int TYPE_AUCING = 0;
    private static final int TYPE_AUCOUT = 1;
    private static final int TYPE_AUCSUC = 2;
    private static final int TYPE_TO_BASK = 3;
    private static final int TYPE_TO_PAY = 4;
    private static final int TYPE_TO_RECEIVE = 5;
    private static final int TYPE_ITEM_COUNT = 6;
    private Context mContext;
    private String message;

    public AllAucAdapter(List<MyAllAucList.ResponseBean.AucIngBean> list, Context context) {
        this.mList = list;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        if (mList != null) {
            return mList.size();
        }
        return 0;
    }

    public void addData(List<MyAllAucList.ResponseBean.AucIngBean> mData) {
        if (mData != null) {
            mList.addAll(mData);
        }
        notifyDataSetChanged();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        switch (mList.get(position).getStatus()) {
            case "aucing"://正在拍
                return TYPE_AUCING;
            case "aucout"://差价购
                return TYPE_AUCOUT;
            case "aucsuc"://我拍中
                return TYPE_AUCSUC;
            case "auctobask"://待晒单
                return TYPE_TO_BASK;
            case "auctopay"://待付款
                return TYPE_TO_PAY;
            case "auctoreceive"://待签收
                return TYPE_TO_RECEIVE;
        }
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_ITEM_COUNT;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        int type = getItemViewType(i);
        AucingHolder aucingHolder = null;
        WaitPayHolder waitPayHolder = null;
        final MyAllAucList.ResponseBean.AucIngBean bean = mList.get(i);

        switch (type) {
            case TYPE_AUCING://我在拍
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_my_auc);
                    aucingHolder = new AucingHolder(view);
                    view.setTag(aucingHolder);
                } else {
                    aucingHolder = (AucingHolder) view.getTag();
                }
                aucingHolder.mTvTime.setText(bean.getDate());
                aucingHolder.mTvStatus.setText("我在拍");
                aucingHolder.mTvMarketPrice.setText("市场价：￥" + bean.getMarketPrice());
                aucingHolder.mTvPaimaiPrice.setText("￥" + bean.getCurrentPrice());
                aucingHolder.mTvAucTime.setText("我出价：" + bean.getAucTime() + "");
                ImageLoadManager.getInstance().setImage(mContext, bean.getPics(), aucingHolder.mIvPhoto);
                aucingHolder.mTvPai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, GoodsDetailActivity.class);
                        intent.putExtra("goodsid", bean.getGoodsId() + "");
                        intent.putExtra("time", bean.getTime() + "");
                        intent.putExtra("innext", "0");

                        mContext.startActivity(intent);

                    }
                });
                break;
            case TYPE_AUCOUT://差价购
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_wait_pay);
                    waitPayHolder = new WaitPayHolder(view);
                    view.setTag(waitPayHolder);
                } else {
                    waitPayHolder = (WaitPayHolder) view.getTag();
                }
                waitPayHolder.mTvTime.setText(bean.getDate());
                waitPayHolder.mTvMarketPrice.setText("市场价：￥" + bean.getMarketPrice());
                waitPayHolder.mTvPaimaiPrice.setText("￥" + bean.getCurrentPrice());
                waitPayHolder.mTvAucTime.setText("成交价：￥" + bean.getActualPayment());
                ImageLoadManager.getInstance().setImage(mContext, bean.getPics(), waitPayHolder.mIvPhoto);
                waitPayHolder.mTvPai.setText("差价购买");
                waitPayHolder.mTvPai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, ComfirmOrderActivity.class);
                        intent.putExtra("goodsid", bean.getGoodsId() + "");
                        intent.putExtra("time", bean.getTime() + "");
                        intent.putExtra("marketprice", bean.getMarketPrice());
                        intent.putExtra("shopcoin", bean.getShopCoin());
                        intent.putExtra("actprice", bean.getActualPayment());
                        intent.putExtra("type", "3");
                        intent.putExtra("pics", bean.getPics());
                        mContext.startActivity(intent);

                    }
                });
                break;
            case TYPE_AUCSUC://我拍中
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_my_auc);
                    waitPayHolder = new WaitPayHolder(view);
                    view.setTag(waitPayHolder);
                } else {
                    waitPayHolder = (WaitPayHolder) view.getTag();
                }
                waitPayHolder.mTvTime.setText(bean.getDate());
                waitPayHolder.mTvMarketPrice.setText("商品名称：￥" + bean.getGoodsName());
                waitPayHolder.mTvPaimaiPrice.setText("￥" + bean.getFinalPrice());
                waitPayHolder.mTvAucTime.setText("成交人：￥" + bean.getDealUser());
                ImageLoadManager.getInstance().setImage(mContext, bean.getPics(), waitPayHolder.mIvPhoto);
                waitPayHolder.mTvPai.setText("立即付款");
                waitPayHolder.mTvPai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, ComfirmOrderActivity.class);
                        intent.putExtra("goodsid", bean.getGoodsId() + "");
                        intent.putExtra("time", bean.getTime() + "");
                        intent.putExtra("marketprice", bean.getMarketPrice());
                        intent.putExtra("shopcoin", bean.getShopCoin());
                        intent.putExtra("actprice", bean.getFinalPrice());
                        intent.putExtra("pics", bean.getPics());
                        intent.putExtra("type", "2");
                        mContext.startActivity(intent);

                    }
                });
                break;
            case TYPE_TO_BASK://待晒单
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_my_auc);
                    waitPayHolder = new WaitPayHolder(view);
                    view.setTag(waitPayHolder);
                } else {
                    waitPayHolder = (WaitPayHolder) view.getTag();
                }
                waitPayHolder.mTvTime.setText(bean.getDate());
                waitPayHolder.mTvMarketPrice.setText("市场价：￥" + bean.getMarketPrice());
                waitPayHolder.mTvPaimaiPrice.setText("￥" + bean.getActualPayment());
                waitPayHolder.mTvAucTime.setText("成交人：￥" + bean.getDealUser());
                ImageLoadManager.getInstance().setImage(mContext, bean.getPics(), waitPayHolder.mIvPhoto);
                waitPayHolder.mTvPai.setText("立即晒单");
                waitPayHolder.mTvPai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, AucBaskActivity.class);
                        intent.putExtra("id", bean.getId() + "");
                        mContext.startActivity(intent);

                    }
                });
                break;
            case TYPE_TO_PAY://待付款
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_my_auc);
                    waitPayHolder = new WaitPayHolder(view);
                    view.setTag(waitPayHolder);
                } else {
                    waitPayHolder = (WaitPayHolder) view.getTag();
                }
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_wait_pay);
                    waitPayHolder = new WaitPayHolder(view);
                    view.setTag(waitPayHolder);
                } else {
                    waitPayHolder = (WaitPayHolder) view.getTag();
                }
                waitPayHolder.mTvTime.setText(bean.getDate());
                waitPayHolder.mTvMarketPrice.setText("市场价：￥" + bean.getMarketPrice());
                waitPayHolder.mTvPaimaiPrice.setText("￥" + bean.getCurrentPrice());
                waitPayHolder.mTvAucTime.setText("成交价：￥" + bean.getActualPayment());
                ImageLoadManager.getInstance().setImage(mContext, bean.getPics(), waitPayHolder.mIvPhoto);
                waitPayHolder.mTvWuLiu.setVisibility(View.VISIBLE);
                waitPayHolder.mTvCancel.setVisibility(View.VISIBLE);
                waitPayHolder.mTvPai.setText("支付差价");
                waitPayHolder.mTvCancel.setOnClickListener(this);
                waitPayHolder.mTvWuLiu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, WuLiuActivity.class);
                        intent.putExtra("id", bean.getId() + "");
                        mContext.startActivity(intent);
                    }
                });
                waitPayHolder.mTvPai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(mContext, ComfirmOrderActivity.class);
                        intent.putExtra("id", bean.getId() + "");
                        mContext.startActivity(intent);

                    }
                });
                break;
            case TYPE_TO_RECEIVE:
                if (view == null) {
                    view = UIUtil.inflate(R.layout.item_my_auc);
                    waitPayHolder = new WaitPayHolder(view);
                    view.setTag(waitPayHolder);
                } else {
                    waitPayHolder = (WaitPayHolder) view.getTag();
                }
                waitPayHolder.mTvTime.setText(bean.getDate());
                waitPayHolder.mTvMarketPrice.setText("市场价：￥" + bean.getMarketPrice());
                waitPayHolder.mTvPaimaiPrice.setText("￥" + bean.getActualPayment());
                waitPayHolder.mTvAucTime.setText("成交人：￥" + bean.getDealUser());
                ImageLoadManager.getInstance().setImage(mContext, bean.getPics(), waitPayHolder.mIvPhoto);
                waitPayHolder.mTvPai.setText("立即签收");
                waitPayHolder.mTvPai.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        Intent intent = new Intent(mContext, AucBaskActivity.class);
//                        intent.putExtra("id", bean.getId() + "");
//                        mContext.startActivity(intent);

                    }
                });
                break;
        }
        return view;
    }

    @Override
    public void onClick(View view) {

    }

    class AucingHolder {
        public TextView mTvTime;
        public TextView mTvStatus;
        public ImageView mIvPhoto;
        public TextView mTvMarketPrice;
        public TextView mTvPaimaiPrice;
        public TextView mTvAucTime;
        public TextView mTvPai;


        public AucingHolder(View view) {
            this.mTvTime = (TextView) view.findViewById(R.id.tv_title);
            this.mTvStatus = (TextView) view.findViewById(R.id.tv_status);
            this.mIvPhoto = (ImageView) view.findViewById(R.id.iv_photo);
            this.mTvMarketPrice = (TextView) view.findViewById(R.id.tv_market_price);
            this.mTvPaimaiPrice = (TextView) view.findViewById(R.id.tv_paimai_price);
            this.mTvAucTime = (TextView) view.findViewById(R.id.tv_auc_time);
            this.mTvPai = (TextView) view.findViewById(R.id.tv_pai);
        }
    }

    class WaitPayHolder {
        public TextView mTvTime;
        public ImageView mIvPhoto;
        public TextView mTvMarketPrice;
        public TextView mTvPaimaiPrice;
        public TextView mTvAucTime;
        public TextView mTvPai;
        public TextView mTvCancel;
        public TextView mTvWuLiu;

        public WaitPayHolder(View view) {
            this.mTvTime = (TextView) view.findViewById(R.id.tv_title);
            this.mIvPhoto = (ImageView) view.findViewById(R.id.iv_photo);
            this.mTvMarketPrice = (TextView) view.findViewById(R.id.tv_market_price);
            this.mTvPaimaiPrice = (TextView) view.findViewById(R.id.tv_paimai_price);
            this.mTvAucTime = (TextView) view.findViewById(R.id.tv_auc_time);
            this.mTvPai = (TextView) view.findViewById(R.id.tv_pai);
            this.mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
            this.mTvWuLiu = (TextView) view.findViewById(R.id.tv_wuliu);

        }
    }
}
