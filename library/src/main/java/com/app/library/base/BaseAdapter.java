package com.app.library.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Description:基类ListView Adapter
 *
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {

    protected List<T> mDatas;
    protected BaseHolder holder;
    protected int mPageIndex;
    protected Context mContext;

    public BaseAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public BaseAdapter(List<T> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = getHolder();
            holder.setFirstData(mDatas);
            if (mContext != null) {
                holder.setContext(mContext);
            }
            convertView = holder.getRootView();
        } else {
            holder = (BaseHolder) convertView.getTag();
        }
        holder.setAllData(mDatas);
        holder.setData(mDatas.get(position), mDatas.size(), position);
        return convertView;
    }

    protected abstract BaseHolder getHolder();

    public void refreshData(List<T> mData) {
        mDatas.clear();
        if (mData != null) {
            mDatas.addAll(mData);
        }
        notifyDataSetChanged();
    }

    public void addData(List<T> mData) {
        if (mData != null) {
            mDatas.addAll(mData);
        }
        notifyDataSetChanged();
    }

    public void addData(T mData) {
        if (mData != null) {
            mDatas.add(mData);
        }
        notifyDataSetChanged();
    }

    public void clearData() {
        if (mDatas != null) {
            mDatas.clear();
        }
        notifyDataSetChanged();
    }

    public void setIndex(int index) {
        mPageIndex = index;
    }

    public int getPageIndex() {
        return mPageIndex;
    }

    public List<T> getAllDatas() {
        return mDatas;
    }

    public OnItemClickListener itemClickListener;

    public interface OnItemClickListener {
        void itemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.itemClickListener = listener;
    }

}
