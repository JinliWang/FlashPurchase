package com.mls.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

/**
 * Description:
 * Create By: MLS Co,Ltd
 * e-mail:wxdingcn@gmail.com
 */

public class MyHorizontalScrollView extends HorizontalScrollView {
    private ScrollListener scrollListener;

    public MyHorizontalScrollView(Context context) {
        super(context);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (scrollListener != null) {
            scrollListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }

    public interface ScrollListener {
        void onScrollChanged(View view, int l, int t, int oldl, int oldt);
    }

    public void setOnScrollListener(ScrollListener scrollListener) {
        this.scrollListener = scrollListener;
    }
}
