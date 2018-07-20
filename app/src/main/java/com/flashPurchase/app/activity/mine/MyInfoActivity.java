package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.ImageLoadManager;
import com.app.library.view.CircleImageView;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.login.ChangeNameActivity;
import com.flashPurchase.app.event.UpdateSuccess;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/10.
 */

public class MyInfoActivity extends BaseActivity {
    @BindView(R.id.rel_photo)
    RelativeLayout mRelPhoto;
    @BindView(R.id.tv_nick_name)
    TextView mTvNickName;
    @BindView(R.id.rel_nick_name)
    RelativeLayout mRelNickName;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.rel_phone)
    RelativeLayout mRelPhone;
    @BindView(R.id.rel_address)
    RelativeLayout mRelAddress;
    @BindView(R.id.iv_photo)
    CircleImageView mIvPhoto;

    private String mPic;
    private String mNickName;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_info;
    }

    @Override
    protected void initView() {
        initTitle("我的信息");
        EventBus.getDefault().register(this);
        mPic = extraDatas.getString("pic");
        mNickName = extraDatas.getString("name");

        ImageLoadManager.getInstance().setImage(this, mPic, mIvPhoto);
        mTvNickName.setText(mNickName);
        mRelPhoto.setOnClickListener(this);
        mRelNickName.setOnClickListener(this);
        mRelAddress.setOnClickListener(this);
        mRelPhone.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.rel_photo:
                break;
            case R.id.rel_nick_name:
                Bundle bundle = new Bundle();
                bundle.putString("name", mNickName);
                startActivity(ChangeNameActivity.class, bundle);
                break;
            case R.id.rel_phone:
                break;
            case R.id.rel_address:
                startActivity(MyAddressActivity.class);
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(UpdateSuccess event) {
        mTvNickName.setText(SpManager.getUserName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
