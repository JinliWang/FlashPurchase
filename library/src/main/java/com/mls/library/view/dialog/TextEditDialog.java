package com.mls.library.view.dialog;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.mls.library.R;


/**
 * Create custom Dialog windows for your application
 * Custom dialogs rely on custom layouts wich allow you to
 * create and use your own look & feel.
 * <p/>
 * Under GPL v3 : http://www.gnu.org/licenses/gpl-3.0.html
 * <p/>
 * <a href="http://my.oschina.net/arthor" target="_blank" rel="nofollow">@author</a> antoine vianey
 */
public class TextEditDialog extends Dialog {
    public static EditDialogListener editDialogListener;
    private TextEditDialog dialog;
    Builder b;
    private Context mContext;

    public TextEditDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
    }

    public TextEditDialog(Context context) {
        super(context);
        this.mContext = context;

    }

    public TextEditDialog getDialog(String title, String content) {
        b = new Builder(mContext);
        b.setMessage(title, content);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    public TextEditDialog getDialog(String title, String content, String et) {
        b = new Builder(mContext);
        b.setMessage(title, content, et);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    public TextEditDialog getDialog(String title) {
        b = new Builder(mContext);
        b.setMessage(title);
        dialog = b.create();
        dialog.show();
        return dialog;
    }

    public Builder getBuilder() {
        return b;
    }


    /**
     * Helper class for creating a custom dialog
     */
    public static class Builder {
        private TextView txtName;
        private TextView txtContent;
        private EditText editContent;
        private Context context;
        private String title, content;
        private String et;
        private int type;

        public Builder(Context context) {
            this.context = context;
        }

        public void setMessage(String title, String content) {
            type = 1;
            this.title = title;
            this.content = content;
        }

        public void setMessage(String title, String content, String et) {
            type = 1;
            this.title = title;
            this.content = content;
            this.et = et;
        }

        public void setMessage(String title) {
            this.title = title;
        }

        public void setEidInputType(int inputType) {
            editContent.setInputType(inputType);
        }

        private View layout;

        /**
         * Create the custom dialog
         */
        @SuppressLint("Override")
        public TextEditDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // instantiate the dialog with the custom Theme
            final TextEditDialog dialog = new TextEditDialog(context, R.style.Dialog);
            dialog.setCanceledOnTouchOutside(false);
            layout = inflater.inflate(R.layout.view_input_dialog, null);
            txtName = (TextView) layout.findViewById(R.id.txt_name);
            txtContent = (TextView) layout.findViewById(R.id.txt_content);
            txtContent.setVisibility(type == 1 ? View.VISIBLE : View.GONE);
            editContent = (EditText) layout.findViewById(R.id.edt_content);
            txtName.setText(title);
            txtContent.setText(content);
            editContent.setText(et);
            layout.findViewById(R.id.txt_cancel).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editDialogListener != null) {
                        editDialogListener.cancel();
                    }
                    hideInput();
                    dialog.dismiss();
                }
            });
            layout.findViewById(R.id.txt_sure).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strContent = Builder.this.editContent.getText().toString().trim();
                    if (editDialogListener != null) {
                        editDialogListener.sure(strContent);

                    }
                    hideInput();
                    dialog.dismiss();
                }
            });
            dialog.addContentView(layout, new LayoutParams(
                    LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
            dialog.setContentView(layout);
            return dialog;

        }

        private void hideInput() {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(txtContent.getWindowToken(), 0);
        }

        public void setEditMaxLength(int maxLength) {
            editContent.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength) {
            }});
        }

    }

    public interface EditDialogListener {
        void sure(String editContent);

        void cancel();
    }


    public EditDialogListener getClearDataListener() {
        return editDialogListener;
    }

    public void seteditDialogListener(EditDialogListener editDialogListener) {
        this.editDialogListener = editDialogListener;
    }

}