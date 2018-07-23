package com.app.library.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.mls.library.R;


/**
 * Description:无标题栏的消息弹窗
 *
 */
public class MessageNoTitleDialog extends Dialog {
    public static MessageDialogListener messageDialogListener;
    private MessageNoTitleDialog dialog;
    private Context mContext;

    public MessageNoTitleDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    public MessageNoTitleDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public MessageNoTitleDialog getDialog(String content) {
        Builder b = new Builder(mContext);
        b.setMessage(content);
        b.setLeft("取消");
        b.setRight("确定");
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    public MessageNoTitleDialog getDialog(String content, String left, String right) {
        Builder b = new Builder(mContext);
        b.setMessage(content);
        b.setLeft(left);
        b.setRight(right);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    public MessageNoTitleDialog getDialog(String content, String right) {
        Builder b = new Builder(mContext);
        b.setMessage(content);
        b.setRight(right);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        private Context context;
        private TextView txtContent, txtLeft, txtRight;
        private String content;
        private String left, Right;

        public void setLeft(String left) {
            this.left = left;
        }

        public void setRight(String right) {
            Right = right;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public void setMessage(String content) {
            this.content = content;
        }

        /**
         * Create the custom dialog
         */
        @SuppressLint("Override")
        public MessageNoTitleDialog create() {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final MessageNoTitleDialog dialog = new MessageNoTitleDialog(context, R.style.Dialog);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.view_message_no_titile_dialog, null);
            txtContent = (TextView) layout.findViewById(R.id.txt_content);
            txtLeft = (TextView) layout.findViewById(R.id.txt_cancel);
            txtRight = (TextView) layout.findViewById(R.id.txt_sure);
            if (TextUtils.isEmpty(left)) {
                txtLeft.setVisibility(View.GONE);
                layout.findViewById(R.id.view_divider).setVisibility(View.GONE);
            }
            txtLeft.setText(left);
            txtRight.setText(Right);
            txtContent.setText(content);
            layout.findViewById(R.id.txt_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (messageDialogListener != null) {
                        messageDialogListener.cancel();
                    }
                    dialog.dismiss();
                }
            });
            layout.findViewById(R.id.txt_sure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (messageDialogListener != null) {
                        messageDialogListener.sure();
                    }
                    dialog.dismiss();
                }
            });
            dialog.setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    return true;
                }
            });
            dialog.addContentView(layout, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;
        }
    }

    public interface MessageDialogListener {
        void sure();

        void cancel();
    }


    public MessageDialogListener getClearDataListener() {
        return messageDialogListener;
    }

    public void seteditDialogListener(MessageDialogListener messageDialogListener) {
        this.messageDialogListener = messageDialogListener;
    }
}