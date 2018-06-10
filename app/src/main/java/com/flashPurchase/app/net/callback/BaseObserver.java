package com.flashPurchase.app.net.callback;


import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.app.library.util.ToastUtil;
import com.app.library.view.dialog.LoadingDialog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;


/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public abstract class BaseObserver<T> implements SingleObserver<T>, LoadingDialog.onLoadingCancelListener {

    LoadingDialog loadingDialog;
    Disposable d;

    public static final int errorCode1 = 1; //服务器内部出错
    public static final int errorCode2 = 2; //连接超时
    public static final int errorCode3 = 3; //没有网络
    public static final int errorCode4 = 4; //无返回数据
    public static final int errorCode5 = 5; //无返回数据

    @Override
    public final void onSubscribe(Disposable d) {
        this.d = d;
        start();
    }

    @Override
    public void onSuccess(T response) {
        if (response == null) error("", errorCode4);
        else success(response);
        completed();
    }

    @Override
    public void onError(Throwable t) {
        t.printStackTrace();
        if (t instanceof SocketTimeoutException) {
            error(t.getMessage(), errorCode2);
        } else if (t instanceof ConnectException) {
            error(t.getMessage(), errorCode3);
        } else if (t instanceof HttpException) {
            HttpException e = (HttpException) t;
            error(e.getMessage(), errorCode5);
        } else {
            error(t.getMessage(), errorCode1);
        }
        completed();
    }

    public final void showLoadingDialog(Context context, String info) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context, this);
        }
        loadingDialog.getDialog(info);
    }

    public final void showLoadingDialog(Context context) {
        showLoadingDialog(context, null);
    }

    public final void dissmissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dissMissDialog();
        }
    }

    public final void cancel() {
        if (d != null) {
            d.dispose();
        }
    }

    public abstract void success(T t);

    protected void success(T t, String msg) {
        success(t);
    }

    protected void completed() {
        try {
            dissmissLoadingDialog();

        } catch (Exception e) {

        }
    }

    protected void start() {
    }

    protected void error(String msg, int errorCode) {
        switch (errorCode) {
            case errorCode2:
                ToastUtil.show("连接超时");
                break;
            case errorCode3:
                ToastUtil.show("网络连接出错");
                break;
            case errorCode5:
                ToastUtil.show(msg);
                break;
        }

    }
}
