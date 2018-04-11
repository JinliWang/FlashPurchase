package com.flashpurchase.app.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.library.Constant;
import com.app.library.util.UIUtil;
import com.flashpurchase.app.R;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

import java.util.List;


/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public class RefreshLayout extends TwinklingRefreshLayout {
    private View view;
    private TextView tvNoData;


    public RefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setFloatRefresh(true);
        setEnableLoadmore(false);
        setEnableOverScroll(false);
        setHeaderHeight(80);
        setMaxHeadHeight(180);
        setHeaderView(new ProgressLayout(context));
        setBottomView(new LoadingView(context));

    }

    public void showEmptyView(String str) {
        if (view == null) {
            view = UIUtil.inflate(R.layout.view_no_data);
            tvNoData = (TextView) view.findViewById(R.id.tv_no_data);
            if (!TextUtils.isEmpty(str)) {
                tvNoData.setText(str);
            }
            view.setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            addView(view);
        } else {
            view.setVisibility(VISIBLE);
        }
    }

    public void hideEmptyView() {
        if (view != null) {
            view.setVisibility(GONE);
        }
    }

    public void setData(List data, BaseAdapter adapter) {
        setData(data, "", adapter);
    }

    public void setData(List data) {
        setData(data, "", null);
    }

    public void setData(List data, boolean isShowEmpty) {
        setData(data, "", null, isShowEmpty);
    }

    public void setData(List data, String strNoData, BaseAdapter adapter) {
        setData(data, strNoData, adapter, true);
    }

    public void setData(List data, String strNoData, BaseAdapter adapter, boolean isShowEmpty) {
        hideEmptyView();
        if (data == null || data.size() < Constant.PAGESIZE) {
            if ((adapter == null || adapter.getCount() == 0)) {
                if ((data == null || data.size() == 0) && isShowEmpty)
                    showEmptyView(strNoData);
            }
            setEnableLoadmore(false);
        } else {
            if (data.size() == Constant.PAGESIZE)
                setEnableLoadmore(true);
        }
        finishLoadmore();
        finishRefreshing();
    }
}
