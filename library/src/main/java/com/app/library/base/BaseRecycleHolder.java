package com.app.library.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.library.util.UIUtil;

import butterknife.ButterKnife;

/**
 * Description:基类RecycleView Holder
 * Create By: MLS Co,Ltd
 */
public abstract class BaseRecycleHolder<T> extends RecyclerView.ViewHolder {

    public BaseRecycleHolder(Context context, ViewGroup root, int layoutRes) {
        super(LayoutInflater.from(context).inflate(layoutRes, root, false));
        init();
    }

    /**
     * 此适配器是为了能让详情页共用列表页的ViewHolder，一般情况无需重写该构造器
     */
    public BaseRecycleHolder(View itemView) {
        super(itemView);
        init();
    }

    public BaseRecycleHolder(@LayoutRes int resId) {
        super(UIUtil.inflate(resId));
        init();
    }

    private void init() {
        ButterKnife.bind(this, itemView);
    }

    public Context getContext() {
        return itemView.getContext();
    }

    /**
     * 用给定的 data 对 holder 的 view 进行赋值
     */
    public abstract void bindData(T t);

    public void bindHeadData() {
    }

    /**
     * 通知适配器更新布局
     */
    public interface OnNotifyChangeListener {
        void onNotify();
    }


    OnNotifyChangeListener listener;

    public void setOnNotifyChangeListener(OnNotifyChangeListener listener) {
        this.listener = listener;
    }

    public void notifyChange() {
        listener.onNotify();
    }
}