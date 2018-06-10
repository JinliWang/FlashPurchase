package com.flashPurchase.app.fragment.mine;

import android.view.View;
import android.widget.ImageView;

import com.app.library.base.BaseFragment;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.mine.CenterMessageActivity;
import com.flashPurchase.app.activity.mine.SetUpActivity;

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
        onClick();
    }

    private void onClick(){
        ivSetup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activity, SetUpActivity.class);
            }
        });
        ivMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(activity, CenterMessageActivity.class);
            }
        });
    }


}
