package com.mls.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Description:适用于ScrollView下的GridView
 * Create By: MLS Co,Ltd
 * e-mail:wxdingcn@gmail.com
 */

public class ScrollGridView extends GridView {

    public ScrollGridView(Context context) {
        super(context);
    }

    public ScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, height);
    }
}
