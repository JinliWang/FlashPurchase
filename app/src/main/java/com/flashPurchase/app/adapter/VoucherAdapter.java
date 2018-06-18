package com.flashPurchase.app.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flashPurchase.app.R;
import com.flashPurchase.app.model.VoucherMoney;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by 10951 on 2018/6/14.
 */

public class VoucherAdapter extends RecyclerView.Adapter<VoucherAdapter.BaseViewHolder> {
    private ArrayList<VoucherMoney> dataList = new ArrayList<>();
    private int lastPressIndex = -1;
    private Context mContext;
    private Activity mActivity;
    private InputMethodManager imm;

    public void replaceAll(ArrayList<VoucherMoney> list, Context context) {
        mContext = context;
        dataList.clear();
        if (list != null && list.size() > 0) {
            dataList.addAll(list);
        }
        notifyDataSetChanged();
    }

    public VoucherAdapter(Activity activity) {
        mActivity = activity;
        imm = (InputMethodManager) mActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    @Override
    public VoucherAdapter.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case VoucherMoney.TWO:
                return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tv_voucher_money, null, false));

            case VoucherMoney.THREE:
                return new TWoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_et_voucher_money, null, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(VoucherAdapter.BaseViewHolder holder, int position) {

        holder.setData(dataList.get(position).data);
    }

    @Override
    public int getItemViewType(int position) {
        return dataList.get(position).type;
    }

    @Override
    public int getItemCount() {
        return dataList != null ? dataList.size() : 0;
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        void setData(Object data) {
        }
    }

    private class OneViewHolder extends BaseViewHolder {
        private TextView tv;

        public OneViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("TAG", "OneViewHolder: ");
                    notyfy = 0;
                    tv.setFocusable(true);
                    imm.hideSoftInputFromWindow(tv.getWindowToken(), 0);
                    int position = getAdapterPosition();
                    VoucherMoney model = dataList.get(position);
                    Log.e("TAG", "OneViewHolder: " + model.toString());
                    EventBus.getDefault().post(model);
                    if (lastPressIndex == position) {
                        lastPressIndex = -1;
                    } else {
                        lastPressIndex = position;
                    }
                    notifyDataSetChanged();


                }

            });
        }

        @Override
        void setData(Object data) {
            if (data != null) {
                String text = (String) data;
                tv.setText(text);
                if (getAdapterPosition() == lastPressIndex) {
                    tv.setSelected(true);
                    tv.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.red));

                } else {
                    tv.setSelected(false);
                    tv.setTextColor(ContextCompat.getColor(itemView.getContext(), R.color.divider_gray));
                }

            }


        }
    }

    int notyfy = 0;

    private class TWoViewHolder extends BaseViewHolder {
        private EditText et;
        private String chargeFunds;

        public TWoViewHolder(View view) {
            super(view);

            et = (EditText) view.findViewById(R.id.et);
            final int position = getAdapterPosition();
            Log.e("TWoViewHolder", "TWoViewHolder: ");
            et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    et.setSelected(true);
                    if (hasFocus) {

                        if (lastPressIndex != position) {
                            notifyItemChanged(lastPressIndex);
                            lastPressIndex = position;
                        }
                    }
                }
            });
            et.addTextChangedListener(new TextWatcher() {


                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (charSequence != null || !charSequence.equals("")) {
                        try {
                            int str = Integer.parseInt(String.valueOf(charSequence));
//                            if (str > 1000000) {
//                                Toast.makeText(mContext, "输入的金额大于最大支付金额1000000元", Toast.LENGTH_SHORT).show();
//                                et.setText("1000000");
//                                et.setSelection(et.getText().length());
//                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {


                    if (editable.length() > 0) {
                        String inputText = et.getText().toString().trim();
                        if (Double.parseDouble(inputText) > 1000000) {
                            chargeFunds = "1000000";
                        } else {
                            chargeFunds = inputText;
                        }
                    }

                    String funds = chargeFunds;
                    VoucherMoney model = new VoucherMoney(VoucherMoney.THREE, funds);
                    EventBus.getDefault().post(model);
                }
            });


        }


        @Override
        void setData(Object data) {
            super.setData(data);
            final int position = getAdapterPosition();
            if (5 == lastPressIndex) {
                et.requestFocus();
                imm.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            } else {
                et.setSelected(false);
                et.clearFocus();
                imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
            }
        }
    }

}