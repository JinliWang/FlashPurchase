package com.flashpurchase.app.net.callback;


import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;
import com.app.library.util.ToastUtil;
import com.app.library.view.dialog.LoadingDialog;
import com.flashpurchase.app.model.CommonResponse;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Description:
 * Create By: MLS Co,Ltd
 */

public abstract class HttpObserver<T> implements SingleObserver<CommonResponse<T>>, LoadingDialog.onLoadingCancelListener {

    LoadingDialog loadingDialog;
    Disposable d;

    public static final int errorCode1 = 1; //服务器内部出错
    public static final int errorCode2 = 2; //连接超时
    public static final int errorCode3 = 3; //没有网络
    public static final int errorCode4 = 4; //
    public static final int errorTokenInvalid = 5; //token失效
    public static final String errorCode = "-1";

    @Override
    public final void onSubscribe(Disposable d) {
        this.d = d;
        start();
    }

    @Override
    public void onSuccess(CommonResponse<T> response) {
        try {
            if ("1".equals(response.getCode())) {
                success(response.getObj(), response.getMsg());
            } else {
                error(response.getMsg(),response.getCode());
//                if ("noData".equals(response.getState())) {
//                    success(null, response.getMessage());
//                } else if ("invalidPrincipal".equals(response.getState())) {
//                    error(response.getMessage(), errorTokenInvalid);
//                } else if ("error".equals(response.getState())) {
//                    error(response.getMessage(), errorCode4);
//                } else {
//                    error(response.getMessage(), errorCode2);
//                }

            }

        } catch (Exception e) {
            error(e.getMessage(), errorCode);
            e.printStackTrace();
        }
        completed();
    }

    @Override
    public final void onError(Throwable t) {
        if (t instanceof SocketTimeoutException) {
            error(t.getMessage(), errorCode);
        } else if (t instanceof ConnectException) {
            error(t.getMessage(), errorCode);
        } else if (t instanceof HttpException) {
            HttpException e = (HttpException) t;
                error(e.getMessage(), errorCode);
//            } else {
//                error(e.getMessage(), errorCode1);
//            }
        } else {
            error(t.getMessage(), errorCode);
        }
        t.printStackTrace();
        completed();
    }

    public final void showLoadingDialog(Context context, String info) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context, this, isCanCancel());
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
        dissmissLoadingDialog();
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

    protected void error(String msg, String errorCode) {
        if(errorCode.equals("1")) {
            ToastUtil.show(msg);
        }
//        switch (errorCode) {
//            case errorCode1:
////                ToastUtil.show("服务器内部出错");
//                break;
//            case errorCode2:
//                ToastUtil.show("连接超时");
//                break;
//            case errorCode3:
//                ToastUtil.show("网络连接出错");
//                break;
//            case errorCode4:
//                ToastUtil.show(msg);
//                break;
//            case errorTokenInvalid:
////                ToastUtil.show("登陆已失效，请重新登录");
////                EventBus.getDefault().post(new TokenInvalidEvent());
//                break;
//
//        }
    }

    public boolean isCanCancel() {
        return true;
    }
}
