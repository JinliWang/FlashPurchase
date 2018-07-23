package com.app.library.base;

import android.content.Context;
import android.view.View;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Description:BaseHolder 处理页面数据
 *
 */
public abstract class BaseHolder<T> {

    private T mDatas;
    private View view;
    private int totalNumber,position;
    private List<T> allData;
    private Context mContext;

    public BaseHolder() {
        view = initView();
        ButterKnife.bind(this, view);
        view.setTag(this);
    }

    /**
     * 初始化布局文件
     * @return
     */
    protected abstract View initView();

    /**
     * 设置数据
     * @param mDatas
     */
    public void setData(T mDatas) {
        this.mDatas = mDatas;
        refreshView();
    }

    /**
     * 设置数据
     * @param mDatas
     */
    public void setFirstData(T mDatas) {
        this.mDatas = mDatas;
    }

    /**
     * 设置数据 能够得到当前总共有多少个数据和当前是第几个
     * @param mDatas 数据集合
     * @param totalNumber 总共多少数据
     * @param position  当前是第几个数据
     */
    public void setData(T mDatas,int totalNumber,int position) {
        this.mDatas = mDatas;
        this.totalNumber = totalNumber;
        this.position = position;
        refreshView();
    }

    /**
     * 设置所有的数据
     * @param allData
     */
    public void setAllData(List<T> allData) {
        this.allData = allData;
    }

    /**
     * 得到所有的数据
     * @return
     */
    public List<T> getAllData() {
        return allData;
    }

    /**
     * 刷新界面
     */
    protected abstract void refreshView();

    /**
     * 得到当前的数据集合
     * @return
     */
    public T getData() {
        return mDatas;
    }

    /**
     * 得到总共多少数据
     * @return
     */
    public int getTotalNumber() {
        return totalNumber;
    }

    /**
     * 得到当前数据的position
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * 得到当前的View
     * @return
     */
    public View getRootView() {
        return view;
    }

    public Context getContext() {
        return null==mContext? BaseConstant.getInstance():mContext;
    }

    public void setContext(Context mContext) {
        this.mContext=mContext;
    }
}
