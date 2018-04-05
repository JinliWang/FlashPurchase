package com.mls.library.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mls.library.R;
import com.mls.library.util.LogUtil;


/**
 * Description:loading弹窗
 * Create By: MLS Co,Ltd
 */
public class LoadingDialog extends Dialog {
    private LoadingDialog dialog;
    private Context mContext;
    static boolean isCancel = true; //能否取消

    public LoadingDialog(Context context) {
        super(context, R.style.Dialog);
        this.mContext = context;
    }

    public LoadingDialog(Context context, onLoadingCancelListener loadingCancelListener) {
        super(context, R.style.Dialog);
        this.mContext = context;
        this.loadingCancelListener = loadingCancelListener;
    }

    public LoadingDialog(Context context, onLoadingCancelListener loadingCancelListener, boolean isCancel) {
        super(context, R.style.Dialog);
        this.mContext = context;
        this.loadingCancelListener = loadingCancelListener;
        this.isCancel = isCancel;
    }

    public LoadingDialog getDialog(String info) {
        try {

            Builder builder = new Builder(mContext, loadingCancelListener);
            builder.setInfo(info == null ? "请稍候" : info);
            dialog = builder.create();
            if (!((Activity) mContext).isFinishing() && !dialog.isShowing()) {
                dialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dialog;
    }

    public LoadingDialog getDialog() {
        return getDialog("请稍候");
    }

    public void dissMissDialog() {
        if (dialog != null)
            dialog.dismiss();
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        private Context context;
        private TextView txtInfo;
        private String info;
        public onLoadingCancelListener loadingCancelListener;

        public void setInfo(String info) {
            this.info = info;
        }

        public Builder(Context context, onLoadingCancelListener loadingCancelListener) {
            this.context = context;
            this.loadingCancelListener = loadingCancelListener;
        }

        /**
         * Create the custom dialog
         */
        public LoadingDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LoadingDialog dialog = new LoadingDialog(context);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (!isCancel && keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//                        if (loadingCancelListener != null) {
//                            loadingCancelListener.cancel();
//                        }
                        LogUtil.d("setOnKeyListener");
                        return true;
                    }
                    return false;
                }
            });

//            dialog.setOnKeyListener(new OnKeyListener() {
//                @Override
//                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
//                    return true;
//                }
//            });
            View layout = inflater.inflate(R.layout.view_login_dialog, null);
            txtInfo = (TextView) layout.findViewById(R.id.txt_info);
            txtInfo.setText(info);
            dialog.addContentView(layout, new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;
        }
    }

    public onLoadingCancelListener loadingCancelListener;

    public interface onLoadingCancelListener {
        void cancel();
    }

}