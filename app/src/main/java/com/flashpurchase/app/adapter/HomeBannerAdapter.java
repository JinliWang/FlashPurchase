package com.flashpurchase.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import com.app.library.util.ImageLoadManager;
import com.flashpurchase.app.R;
import com.flashpurchase.app.model.HomeBanner;
import com.github.wanglu1209.bannerlibrary.BannerPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2017/12/3 0003.
 */

public class HomeBannerAdapter extends BannerPagerAdapter<HomeBanner.ListBean> {
    private Context mContext;
    private List<HomeBanner.ListBean> data;

    public HomeBannerAdapter(Context context) {
        super();
        mContext = context;
    }

    @Override
    public void setData(List data) {
        super.setData(data);
        this.data = data;
    }

    /**
     * 只需要重写构造和这个方法即可
     * 在这里可以设置自己的View,使用自己的图片加载库
     */
    @Override
    public View setView(int position) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_pic, null);
        ImageView iv = (ImageView) v.findViewById(R.id.pic_iv);
        ImageLoadManager.getInstance().setBannerImage(mContext, data.get(position).getBannerpic(), iv);
        return v;
    }
}
