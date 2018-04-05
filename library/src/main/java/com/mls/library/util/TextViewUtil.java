package com.mls.library.util;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

import static com.mls.library.util.UIUtil.getResources;


/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public class TextViewUtil {

    public static void setDrawableRightImg(TextView tv, Integer resId) {
        Drawable drawable = null;
        if (resId != null) {
            drawable = getResources().getDrawable(resId);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        tv.setCompoundDrawables(null, null, drawable, null);
    }
    public static void setDrawableLeftImg(TextView tv, Integer resId) {
        Drawable drawable = null;
        if (resId != null) {
            drawable = getResources().getDrawable(resId);
            // 这一步必须要做,否则不会显示.
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        tv.setCompoundDrawables(drawable,null, null, null);
    }
}
