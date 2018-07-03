package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.view.View;

import com.app.library.base.BaseFragment;
import com.flashPurchase.app.R;

/**
 * Created by 10951 on 2018/7/2.
 */

public class ExpenseDetailFragment extends BaseFragment {

    public static ExpenseDetailFragment getInstance(int position) {
        ExpenseDetailFragment saleCenterFragment = new ExpenseDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        saleCenterFragment.setArguments(bundle);

        return saleCenterFragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_expense_detail;
    }

    @Override
    protected void initView(View view) {

    }


}
