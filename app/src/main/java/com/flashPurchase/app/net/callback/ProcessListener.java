package com.flashPurchase.app.net.callback;


import com.app.library.util.HandlerUtil;

/**
 * Description:
 *
 */

public abstract class ProcessListener {
    private long currentSize=0;
    public abstract void onSuccess();

    public abstract void onProcess(long size,long content);

    public void success() {
        HandlerUtil.postMain(new Runnable() {
            @Override
            public void run() {
                onSuccess();
            }
        });
    }

    public void process(final long size, final long contetnSize) {
        if (size - currentSize > 100000) {
            currentSize = size;
            HandlerUtil.postMain(new Runnable() {
                @Override
                public void run() {
                    onProcess(size,contetnSize);
                }
            });
        }
    }

    public void onFail() {

    }
}

