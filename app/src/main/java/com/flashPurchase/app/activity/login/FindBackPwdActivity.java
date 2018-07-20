package com.flashPurchase.app.activity.login;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.MainActivity;

import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import cn.smssdk.SMSSDK;

/**
 * Created by 10951 on 2018/6/10.
 */

public class FindBackPwdActivity extends BaseActivity {
    @BindView(R.id.et_username)
    EditText mEtUsername;
    @BindView(R.id.tv_yzm)
    TextView mTvYzm;
    @BindView(R.id.et_code)
    EditText mEtCode;
    @BindView(R.id.tv_get_code)
    TextView mTvGetCode;
    @BindView(R.id.btn_next)
    Button mBtnNext;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void initView() {
        initTitle("找回密码");
        mTvGetCode.setOnClickListener(this);
        mBtnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_get_code:
                String strPhoneNumber = mEtUsername.getText().toString();
                if (null == strPhoneNumber || "".equals(strPhoneNumber) || strPhoneNumber.length() != 11) {
                    Toast.makeText(this, "电话号码输入有误", Toast.LENGTH_SHORT).show();
                    return;
                }
                SMSSDK.getVerificationCode("86", strPhoneNumber);

                //开启线程去更新button的text
                new Thread() {
                    @Override
                    public void run() {
                        int totalTime = 60;
                        for (int i = 0; i < totalTime; i++) {
                            Message message = myHandler.obtainMessage(0x01);
                            message.arg1 = totalTime - i;
                            myHandler.sendMessage(message);
                            try {
                                sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        myHandler.sendEmptyMessage(0x02);
                    }
                }.start();
                break;
            case R.id.btn_next:
                String phoneNumber = mEtUsername.getText().toString();
                if (null == phoneNumber || "".equals(phoneNumber) || phoneNumber.length() != 11) {
                    ToastUtil.show("请输入正确的手机号");
                    return;
                }
                if (TextUtils.isEmpty(mEtCode.getText().toString())) {
                    ToastUtil.show("请获取并填写验证码！");
                    return;
                }
                SMSSDK.submitVerificationCode("86", phoneNumber, mEtCode.getText().toString());
                break;
        }
    }

    Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x00:
                    int event = msg.arg1;
                    int result = msg.arg2;
                    Object data = msg.obj;
                    Log.e(TAG, "result : " + result + ", event: " + event + ", data : " + data);
                    if (result == SMSSDK.RESULT_COMPLETE) { //回调  当返回的结果是complete
                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) { //获取验证码
                            Toast.makeText(FindBackPwdActivity.this, "发送验证码成功", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "get verification code successful.");
                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) { //提交验证码
                            Log.d(TAG, "submit code successful");
                            HashMap<String, Object> mData = (HashMap<String, Object>) data;
                            //返回国家的编号
                            String country = (String) mData.get("country");
                            String phone = (String) mData.get("phone");
                            Log.e("TAG", country + "====" + phone);
                            if (phone.equals(mEtUsername.getText().toString())) {
                                //如果返回的用户注册的手机号和用户输入的手机号相同,则代表通过验证
                                Bundle bundle = new Bundle();
                                bundle.putString("phone", mEtUsername.getText().toString());
                                startActivity(ChangePwdActivity.class, bundle);
                            }
                        } else {
                            Log.d(TAG, data.toString());
                        }
                    } else { //进行操作出错，通过下面的信息区分析错误原因
                        try {
                            Throwable throwable = (Throwable) data;
                            throwable.printStackTrace();
                            JSONObject object = new JSONObject(throwable.getMessage());
                            String des = object.optString("detail");//错误描述
                            int status = object.optInt("status");//错误代码
                            //错误代码：  http://wiki.mob.com/android-api-%E9%94%99%E8%AF%AF%E7%A0%81%E5%8F%82%E8%80%83/
                            Log.e(TAG, "status: " + status + ", detail: " + des);
                            if (status > 0 && !TextUtils.isEmpty(des)) {
                                Toast.makeText(FindBackPwdActivity.this, des, Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 0x01:
                    mTvGetCode.setText("重新发送(" + msg.arg1 + ")");
                    break;
                case 0x02:
                    mTvGetCode.setText("获取验证码");
                    mTvGetCode.setClickable(true);
                    break;
            }
        }
    };
}
