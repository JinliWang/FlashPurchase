package com.app.library.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mls.library.R;


/**
 * Description: 图片加载帮助类
 * Create By: MLS Co,Ltd
 */

public class ImageLoadManager {
    private volatile static ImageLoadManager instance;

    private ImageLoadManager() {
    }

    public static ImageLoadManager getInstance() {
        if (instance == null) {
            synchronized (ImageLoadManager.class) {
                if (instance == null) {
                    instance = new ImageLoadManager();
                }
            }
        }
        return instance;
    }

    public void setPlaceImage(Context context, String url, ImageView iv) {
        Glide.with(context) // 绑定Context
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //all:缓存源资源和转换后的资源
                .thumbnail(0.1f)   //先加载缩略图 在加载全图
                .placeholder(R.drawable.placeholder_image)
                .into(iv);
    }

    public void setBannerImage(Context context, String url, ImageView iv) {
        Glide.with(context) // 绑定Context
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL) //all:缓存源资源和转换后的资源
                .thumbnail(0.1f)   //先加载缩略图 在加载全图
                .placeholder(R.drawable.banner)
                .into(iv);
    }

    public void setImage(Context context, String url, ImageView iv) {
        Glide.with(context) // 绑定Context
                .load(url)
                .into(iv);
    }


    public void setImage(Context context, Uri uri, ImageView iv) {
        Glide.with(context) // 绑定Context
                .load(uri)
                .into(iv);
    }

    /**
     * 清理磁盘缓存 需要在子线程中执行
     *
     * @param context
     */
    public void clearDiskCache(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearDiskCache();
            }
        }).run();
    }

    /**
     * 清理内存缓存  可以在UI主线程中进行
     *
     * @param context
     */
    public void clearMemory(final Context context) {
        HandlerUtil.postMain(new Runnable() {
            @Override
            public void run() {
                Glide.get(context).clearMemory();
            }
        });
    }
}
