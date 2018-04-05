package com.mls.library.view;

import android.content.Context;
import android.util.AttributeSet;

import com.baoyz.swipemenulistview.SwipeMenuListView;

/**
 * Description:适用于ScrollView下的ListView
 * Create By: MLS Co,Ltd
 * e-mail:wxdingcn@gmail.com
 */

public class ScrollSwipeListView extends SwipeMenuListView {
    public ScrollSwipeListView(Context context) {
        super(context);
    }

    public ScrollSwipeListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollSwipeListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
