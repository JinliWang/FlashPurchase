package com.app.library.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.library.util.TextViewUtil;
import com.mls.library.R;
import com.app.library.util.ActivityManager;
import com.app.library.util.LogUtil;
import com.app.library.view.dialog.LoadingDialog;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;


/**
 * Description:基类Activity 处理公用逻辑
 * Create By: MLS Co,Ltd
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    //在application初始化时赋值
    public static Application baseApplication;

    protected Context mContext;
    protected TextView tvTitle;
    public static final String TAG = "JSTI_TAG";
    protected Bundle extraDatas;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle bundle) {

//        LogUtil.d("onCreate run by : " + this.getClass().getSimpleName());

        super.onCreate(bundle);
        mContext = this;
        if (getLayoutId() != 0)
            setContentView(getLayoutId());
        ButterKnife.bind(this);
        ActivityManager.getActivityManager().pushActivity(this);
        //小米MIUI状态栏字体改为黑色
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        //关闭滑动返回功能 存在调用软键盘显示问题
//        if (isSetSwipeBack()) {
//            SwipeBackManager.getInstance().setSwipeBackOpen(this);
//        }
        extraDatas = getIntent().getExtras() == null ? new Bundle() : getIntent().getExtras();
        try {
            initView();
            initData(bundle);
        } catch (Exception e) {
            LogUtil.e("initView或者initData出错");
            e.printStackTrace();
        }
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        LogUtil.d("onStart run by : " + this.getClass().getSimpleName());
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        LogUtil.d("onResume run by : " + this.getClass().getSimpleName());
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        LogUtil.d("onPause run by : " + this.getClass().getSimpleName());
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        LogUtil.d("onStop run by : " + this.getClass().getSimpleName());
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        LogUtil.d("onDestroy run by : " + this.getClass().getSimpleName());
//        ActivityManager.getActivityManager().popActivity(this);
//    }
//
//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        LogUtil.d("onRestart run by : " + this.getClass().getSimpleName());
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//        LogUtil.d("onSaveInstanceState run by : " + this.getClass().getSimpleName());
//    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            //更改为LANDSCAPE
            case (Configuration.ORIENTATION_LANDSCAPE):
                //如果转换为横向屏时，有要做的事，请写在这里

                LogUtil.d("横向屏 onConfigurationChanged run by : " + this.getClass().getSimpleName());
                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                break;
            //更改为PORTRAIT
            case (Configuration.ORIENTATION_PORTRAIT):
                //如果转换为竖向屏时，有要做的事，请写在这里
                LogUtil.d("竖向屏 onConfigurationChanged run by : " + this.getClass().getSimpleName());

                //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;
        }
    }

    protected boolean isSetSwipeBack() {
        return true;
    }

    /**
     * 设置页面文件
     * 如果不设置请setContentView（）
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     * 执行findviewbyid等
     */
    protected abstract void initView();

    /**
     * 初始化数据
     * 接口获取数据等
     *
     * @param bundle
     */
    protected void initData(Bundle bundle) {

    }

    /**
     * 初始化标题栏（仅有标题）
     *
     * @param title
     */
    protected void initTitle(String title) {
        initTitle(title, "");
    }

    /**
     * 初始化标题栏（仅有标题，标题栏可点击）
     *
     * @param title
     */
    protected void initTitleSelect(String title) {
        initTitle(title, null);
    }

    /**
     * 初始化标题栏（标题+右边图片，标题栏可点击）
     *
     * @param title
     */
    protected void initTitleSelect(String title, int drawright) {
        initTitleSelect(title, drawright, null);
    }

    /**
     * 初始化标题栏（仅有标题，标题栏可点击）
     *
     * @param title
     */
    protected void initTitleSelect(String title, String content) {
        initTitleSelect(title, null, content);
    }

    /**
     * 初始化标题栏（标题+右边文字，标题栏可点击）
     *
     * @param title
     */
    protected void initTitleSelect(String title, Integer drawright, String content) {
        initTitle(title, drawright, content, true);
        TextViewUtil.setDrawableRightImg(tvTitle, R.drawable.icon_arrow_down_white);
    }

    /**
     * 初始化标题栏（标题+右边文字）
     *
     * @param title
     */
    protected void initTitle(String title, String content) {
        initTitle(title, content, true);
    }

    /**
     * 初始化标题栏（标题+右边图片）
     *
     * @param title
     */
    protected void initTitle(String title, int drawright) {
        initTitle(title, drawright, true);
    }

    /**
     * 初始化标题栏（标题+右边图片）
     *
     * @param title
     */
    protected void initTitle(String title, Integer drawleft, Integer drawright) {
        initTitle(title, drawleft, drawright, null, true);
    }

    /**
     * 初始化标题栏（标题+右边文字，是否显示返回按钮）
     *
     * @param title
     */
    protected void initTitle(String title, String content, boolean isShow) {
        initTitle(title, null, content, isShow);
    }

    /**
     * 初始化标题栏（标题+右边图片，是否显示返回按钮）
     *
     * @param title
     */
    protected void initTitle(String title, Integer drawright, boolean isShow) {
        initTitle(title, drawright, null, isShow);
    }

    /**
     * 初始化标题栏（标题+右边图片，是否显示返回按钮）
     *
     * @param title
     */
    protected void initTitle(String title, Integer drawright, String content, boolean isShow) {
        initTitle(title, null, drawright, content, isShow);
    }

    /**
     * 初始化标题栏（标题+右边图片，是否显示返回按钮）
     *
     * @param title
     */
    protected void initTitle(String title, Integer drawleft, Integer drawright, String content, boolean isShow) {
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvTitle.setText(title);
        tvTitle.setOnClickListener(this);
        if (!TextUtils.isEmpty(content)) {
            TextView txtRight = (TextView) findViewById(R.id.tv_right);
            txtRight.setText(content);
            txtRight.setVisibility(View.VISIBLE);
            txtRight.setOnClickListener(this);
        }
        if (drawright != null) {
            ImageView imgRight = (ImageView) findViewById(R.id.iv_right);
            imgRight.setVisibility(View.VISIBLE);
            imgRight.setOnClickListener(this);
            imgRight.setImageResource(drawright);
        }
        if (drawleft != null) {
            ImageView iv = (ImageView) findViewById(R.id.iv_left);
            iv.setVisibility(View.VISIBLE);
            iv.setOnClickListener(this);
            iv.setImageResource(drawleft);
        }
        if (!isShow) {
            findViewById(R.id.iv_left).setVisibility(View.GONE);
        } else {
            findViewById(R.id.iv_left).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_left) finish();
    }

    @Override
    public void finish() {
        hideInputMethod();
        super.finish();
    }

    /**
     * @param toClass
     */
    public void startActivity(Class<?> toClass) {
        startActivity(this, toClass, null);
    }

    /**
     * @param toClass
     * @param bundle
     */
    public void startActivity(Class<?> toClass, Bundle bundle) {
        startActivity(this, toClass, bundle);
    }

    /**
     * @param fromClass
     * @param toClass
     */
    public void startActivity(Context fromClass, Class<?> toClass) {
        startActivity(fromClass, toClass, null);
    }

    /**
     * @param fromClass
     * @param toClass
     * @param bundle
     */
    public void startActivity(Context fromClass, Class<?> toClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(fromClass, toClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        super.startActivity(intent);
    }

    /**
     * @param fromClass
     * @param toClass
     * @param bundle
     */
    public void startActivityForResult(Context fromClass, Class<?> toClass, Bundle bundle, int resultCode) {
        Intent intent = new Intent();
        intent.setClass(fromClass, toClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        super.startActivityForResult(intent, resultCode);
    }

    /**
     * @param fromClass
     * @param toClass
     */
    public void startActivityForResult(Context fromClass, Class<?> toClass, int resultCode) {
        startActivityForResult(fromClass, toClass, null, resultCode);
    }

    /**
     * @param toClass
     */
    public void startActivityForResult(Class<?> toClass, int resultCode) {
        startActivityForResult(this, toClass, null, resultCode);
    }

    //获取焦点
    public void requestFocus(View view) {
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    public void setResult(int resultCode, Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        super.setResult(resultCode, intent);
    }

    /**
     * 打开软键盘
     */
    public void openKeyboard() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 200);
    }

    //关闭软键盘
    public void hideInputMethod() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public final void showLoadingDialog(Context context, String info) {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(context);
        }
        loadingDialog.getDialog(info);
    }

    public final void showLoadingDialog(Context context) {
        showLoadingDialog(context, null);
    }

    public final void dissmissLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dissMissDialog();
        }
    }

}
