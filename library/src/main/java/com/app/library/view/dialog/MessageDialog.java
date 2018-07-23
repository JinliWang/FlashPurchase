package com.app.library.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.mls.library.R;


/**
 * Description:有标题栏的消息弹窗
 *
 */
public class MessageDialog extends Dialog {

    public static MessageDialogListener messageDialogListener;
    private MessageDialog dialog;
    private Context mContext;

    public MessageDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    public MessageDialog(Context context) {
        super(context);
        this.mContext = context;

    }

    public MessageDialog getDialog(String title, String content) {
        Builder b = new Builder(mContext);
        b.setMessage(title, content);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    public MessageDialog getDialog(String title, String content, String left, String right) {
        Builder b = new Builder(mContext);
        b.setMessage(title, content, left, right);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        private Context context;
        private TextView txtCancel;
        private TextView txtSure;
        private TextView txtName;
        private TextView txtContent;

        private String title;
        private String content;
        private String left="取消";
        private String right="确定";

        public Builder(Context context) {
            this.context = context;
        }

        public void setMessage(String title, String content) {
            this.title = title;
            this.content = content;
        }

        public void setMessage(String title, String content, String left, String right) {
            this.title = title;
            this.content = content;
            this.left = left;
            this.right = right;
        }

        /**
         * Create the custom dialog
         */
        @SuppressLint("Override")
        public MessageDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final MessageDialog dialog = new MessageDialog(context, R.style.Dialog);
            dialog.setCanceledOnTouchOutside(false);
            View layout = inflater.inflate(R.layout.view_message_dialog, null);
            txtName = (TextView) layout.findViewById(R.id.txt_name);
            txtContent = (TextView) layout.findViewById(R.id.txt_content);
            txtCancel = (TextView) layout.findViewById(R.id.txt_cancel);
            txtSure = (TextView) layout.findViewById(R.id.txt_sure);
            txtName.setText(title);
            txtContent.setText(content);
            txtCancel.setText(left);
            txtSure.setText(right);

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