package com.flashPurchase.app.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.event.EditAddressEvent;
import com.flashPurchase.app.model.bean.MyAddress;
import com.flashPurchase.app.model.request.Address;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;

import org.greenrobot.eventbus.EventBus;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/10.
 */

public class MyAddressActivity extends BaseActivity {
    @BindView(R.id.tv_right)
    TextView mTvRight;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.et_qq)
    EditText mEtQq;
    @BindView(R.id.tv_qq)
    TextView mTvQq;
    @BindView(R.id.et_zfb_account)
    EditText mEtZfbAccount;
    @BindView(R.id.tv_zfb_account)
    TextView mTvZfbAccount;
    @BindView(R.id.et_zfb_name)
    EditText mEtZfbName;
    @BindView(R.id.tv_zfb_name)
    TextView mTvZfbName;
    @BindView(R.id.tv_address)
    TextView mTvAddress;
    @BindView(R.id.tv_street)
    TextView mTvStreet;
    @BindView(R.id.et_address_detail)
    EditText mEtAddressDetail;
    @BindView(R.id.tv_address_detail)
    TextView mTvAddressDetail;
    @BindView(R.id.et_remark)
    EditText mEtRemark;
    @BindView(R.id.tv_remark)
    TextView mTvRemark;
    @BindView(R.id.lin_choose_address)
    LinearLayout mLinChooseAddress;
    @BindView(R.id.lin_choose_street)
    LinearLayout mLinChooseStreet;
    @BindView(R.id.line_address)
    View mLineAddress;
    @BindView(R.id.line_street)
    View mLineStreet;

    private WebSocketClient mWebSocketClient;
    private MyAddress mMyAddress;
    private MyAddress.ResponseBean mResponseBean;
    private boolean isEditable = false;
    private String mProvince, mCity, mDistrict, mId;
    //申明对象
    CityPickerView mPicker = new CityPickerView();
    Address address = new Address();
    Address.Parameter parameter = new Address.Parameter();


    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @Override
    protected void initView() {
        mTvRight.setOnClickListener(this);
        mLinChooseAddress.setOnClickListener(this);
        mPicker.init(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_right:
                if (mTvRight.getText().equals("编辑")) {
                    mTvRight.setText("保存");
                    if (mResponseBean != null) {
                        mEtName.setVisibility(View.VISIBLE);
                        mTvName.setVisibility(View.GONE);
                        mEtName.setText(mResponseBean.getRecipient());

                        mEtPhone.setVisibility(View.VISIBLE);
                        mTvPhone.setVisibility(View.GONE);
                        mEtPhone.setText(mResponseBean.getPhone());

                        mEtQq.setVisibility(View.VISIBLE);
                        mTvQq.setVisibility(View.GONE);
                        mEtQq.setText(mResponseBean.getQq());

                        mEtZfbAccount.setVisibility(View.VISIBLE);
                        mEtZfbName.setVisibility(View.VISIBLE);
                        mTvZfbAccount.setVisibility(View.GONE);
                        mTvZfbName.setVisibility(View.GONE);
                        mEtZfbName.setText(mResponseBean.getAlipayName());
                        mEtZfbAccount.setText(mResponseBean.getAlipayAccount());

                        mLinChooseAddress.setVisibility(View.VISIBLE);
//                        mLinChooseStreet.setVisibility(View.VISIBLE);
                        mLineAddress.setVisibility(View.VISIBLE);
//                        mLineStreet.setVisibility(View.VISIBLE);
                        mTvAddress.setText(mResponseBean.getRegion());
                        mTvStreet.setText(mResponseBean.getStreet());
                        mEtAddressDetail.setVisibility(View.VISIBLE);
                        mTvAddressDetail.setVisibility(View.GONE);
                        mEtAddressDetail.setText(mResponseBean.getSpecificAddress());
                        mEtRemark.setVisibility(View.VISIBLE);
                        mTvRemark.setVisibility(View.GONE);
                        mEtRemark.setText(mResponseBean.getRemark());
                    }

                } else if (mTvRight.getText().equals("保存")) {
                    if (TextUtils.isEmpty(mEtName.getText())) {
                        ToastUtil.show("收货人不能为空，请填写完整");
                        return;
                    }
                    if (TextUtils.isEmpty(mEtPhone.getText()) || mEtPhone.getText().length() != 11) {
                        ToastUtil.show("请输入正确的手机号");
                        return;
                    }
                    if (TextUtils.isEmpty(mEtQq.getText())) {
                        ToastUtil.show("QQ不能为空，请填写完整");
                        return;
                    }
                    if (TextUtils.isEmpty(mEtQq.getText())) {
                        ToastUtil.show("QQ不能为空，请填写完整");
                        return;
                    }
                    if (TextUtils.isEmpty(mEtZfbAccount.getText())) {
                        ToastUtil.show("支付宝不能为空，请填写完整");
                        return;
                    }
                    if (TextUtils.isEmpty(mEtZfbName.getText())) {
                        ToastUtil.show("支付宝账号名不能为空，请填写完整");
                        return;
                    }
                    if (TextUtils.isEmpty(mTvAddress.getText())) {
                        ToastUtil.show("请选择所在地区");
                        return;
                    }
//                    if (TextUtils.isEmpty(mTvStreet.getText())) {
//                        ToastUtil.show("请选择所在街道");
//                        return;
//                    }
                    if (TextUtils.isEmpty(mEtAddressDetail.getText())) {
                        ToastUtil.show("详细地址不能为空，请填写完整");
                        return;
                    }
                    if (TextUtils.isEmpty(mId)) {
                        saveAddress();
                        address.setParameter(parameter);
                        mWebSocketClient.send(address.toString());
                    } else {
                        saveAddress();
                        parameter.setId(mId);
                        address.setParameter(parameter);
                        mWebSocketClient.send(address.update());
                    }
                }
                break;
            case R.id.lin_choose_address:
                //添加默认的配置，不需要自己定义
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);

//监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份
                        if (province != null) {
                            mProvince = province.getName();
                        }
                        //城市
                        if (city != null) {
                            mCity = city.getName();
                        }
                        //地区
                        if (district != null) {
                            mDistrict = district.getName();
                        }
                        mTvAddress.setText(mProvince + mCity + mDistrict);
                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.showLongToast(MyAddressActivity.this, "已取消");
                    }
                });

                //显示
                mPicker.showCityPicker();
                break;
        }
    }

    private void saveAddress() {
        parameter.setRecipient(mEtName.getText().toString());
        parameter.setToken(SpManager.getToken());
        parameter.setPhone(mEtPhone.getText().toString());
        parameter.setQq(mEtQq.getText().toString());
        parameter.setAlipayAccount(mEtZfbAccount.getText().toString());
        parameter.setAlipayName(mEtZfbName.getText().toString());
        parameter.setRegion(mTvAddress.getText().toString());
        parameter.setStreet("");
        parameter.setSpecificAddress(mEtAddressDetail.getText().toString());
        parameter.setRemark(mEtRemark.getText().toString());
        address.setUrlMapping("address-save");
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {

                }

                @Override
                public void onMessage(String message) {
                    LogUtil.d(message);
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else if (message.contains("address-get") || message.contains("address-save")) {
                        Gson gson = new Gson();
                        mMyAddress = gson.fromJson(message, MyAddress.class);
                        Message msg = new Message();
                        msg.what = 1;
                        handler.sendMessage(msg);
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setToken(SpManager.getToken());
                    more.setUrlMapping("address-get");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    mResponseBean = mMyAddress.getResponse();
                    SpManager.setMyAddress(mMyAddress);
                    if (mResponseBean != null) {
                        initTitle("地址信息", "编辑");
                        mId = mResponseBean.getId();
                        mEtName.setVisibility(View.GONE);
                        mTvName.setVisibility(View.VISIBLE);
                        mTvName.setText(mResponseBean.getRecipient());
                        mEtPhone.setVisibility(View.GONE);
                        mTvPhone.setVisibility(View.VISIBLE);
                        mTvPhone.setText(mResponseBean.getPhone());
                        mEtQq.setVisibility(View.GONE);
                        mTvQq.setVisibility(View.VISIBLE);
                        mTvQq.setText(mResponseBean.getQq());
                        mEtZfbAccount.setVisibility(View.GONE);
                        mEtZfbName.setVisibility(View.GONE);
                        mTvZfbAccount.setVisibility(View.VISIBLE);
                        mTvZfbName.setVisibility(View.VISIBLE);
                        mTvZfbName.setText(mResponseBean.getAlipayName());
                        mTvZfbAccount.setText(mResponseBean.getAlipayAccount());
                        mLinChooseAddress.setVisibility(View.GONE);
                        mLinChooseStreet.setVisibility(View.GONE);
                        mLineAddress.setVisibility(View.GONE);
                        mLineStreet.setVisibility(View.GONE);
                        mEtAddressDetail.setVisibility(View.GONE);
                        mTvAddressDetail.setVisibility(View.VISIBLE);
                        mTvAddressDetail.setText(mResponseBean.getRegion() + mResponseBean.getStreet() + mResponseBean.getSpecificAddress());
                        mEtRemark.setVisibility(View.GONE);
                        mTvRemark.setVisibility(View.VISIBLE);
                        mTvRemark.setText(mResponseBean.getRemark());
                        EventBus.getDefault().post(new EditAddressEvent());
                    } else {
                        initTitle("地址信息", "保存");
                    }
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebSocketClient.close();
    }
}
