package com.app.library.util;

import android.app.Activity;

import com.mls.library.R;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.r0adkll.slidr.model.SlidrListener;
import com.r0adkll.slidr.model.SlidrPosition;


/**
 * Description:滑动返回帮助类
 *
 */

public class SwipeBackManager {

    private volatile static SwipeBackManager instance;
    private static SlidrConfig config;

    private SwipeBackManager() {

        config = new SlidrConfig.Builder()
                .primaryColor(UIUtil.getResources().getColor(R.color.title_bg))
                .secondaryColor(UIUtil.getResources().getColor(R.color.transparent))
                .position(SlidrPosition.LEFT)
                .sensitivity(1f)
                .scrimColor(UIUtil.getResources().getColor(R.color.title_bg))
                .scrimStartAlpha(0.8f)
                .scrimEndAlpha(0f)
                .velocityThreshold(2400)
                .distanceThreshold(0.25f)
                .edge(true)
                .edgeSize(0.18f) // The % of the screen that counts as the edge, default 18%
                .listener(new SlidrListener() {
                    @Override
                    public void onSlideStateChanged(int state) {

                    }

                    @Override
                    public void onSlideChange(float percent) {

                    }

                    @Override
                    public void onSlideOpened() {

                    }

                    @Override
                    public void onSlideClosed() {

                    }
                })
                .build();

    }

    public static SwipeBackManager getInstance() {
        if (instance == null) {
            synchronized (SwipeBackManager.class) {
                if (instance == null) {
                    instance = new SwipeBackManager();
                }
            }
        }
        return instance;
    }

    /**
     * activity开启滑动返回
     * 需设置activity的theme为BackActivityTheme
     * activity页面根部局需设置background颜色
     *
     * @param activity
     */
    public void setSwipeBackOpen(Activity activity) {
        Slidr.attach(activity, config);

    }
}
