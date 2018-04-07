package com.flashpurchase.app.fragment;

import android.view.View;
import android.widget.ImageView;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;

import butterknife.BindView;

/**
 * Created by zms on 2018/4/7.
 */

public class MineCenterFragment extends BaseFragment {
    @BindView(R.id.iv_maine_setup)
    ImageView ivSetup;              //设置
    @BindView(R.id.iv_maine_msg)
    ImageView ivMsg;                //消息

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_center;
    }

    @Override
    protected void initView(View view) {

    }

    private void onClick(){
        ivSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ivMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
