package com.app.library.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.mls.library.R;
import com.app.library.util.LogUtil;
import com.app.library.util.UIUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Description:基类Fragment 处理公用逻辑
 * Create By: MLS Co,Ltd
 */

public abstract class BaseFragment extends Fragment {

    protected Activity activity;
    protected View mView;
    protected Unbinder unbinder;

    /**
     * 控件是否初始化完成
     */
    private boolean isViewCreated;
    /**
     * 数据是否已加载完毕
     */
    private boolean isLoadDataCompleted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        LogUtil.d(this.getClass().getSimpleName() + "  fragment onCreateView mView " + mView);
        if (null == mView) {
            activity = this.getActivity();
            mView = UIUtil.inflate(getLayoutId(), activity);
        }
        unbinder = ButterKnife.bind(this, mView);
        isViewCreated = true;
        return mView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
//        LogUtil.d(this.getClass().getSimpleName() + "  fragment onViewCreated  isVisibleToUser " + isVisibleToUser + " isLoadDataCompleted " + isLoadDataCompleted);
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isViewCreated && !isLoadDataCompleted) {
            loadData(null);
            isLoadDataCompleted = true;

        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        LogUtil.d(this.getClass().getSimpleName() + "  fragment onViewCreated");

        try {
            initView(mView);
            initData(savedInstanceState);
            //第一个展示的fragment
            if (getUserVisibleHint()) {
                loadData(savedInstanceState);
                isLoadDataCompleted = true;

            }
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.e("initView或者initData出错");
        }
    }

    /**
     * 设置页面文件
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化View
     * 执行findviewbyid等
     */
    protected abstract void initView(View view);

    /**
     * 初始化数据
     * 接口获取数据等
     *
     * @param bundle
     */
    protected void initData(Bundle bundle) {
    }

    //页面显示之后再加载数据
    protected void loadData(Bundle savedInstanceState) {
    }

    protected void initTitle(String title) {
        TextView view = (TextView) mView.findViewById(R.id.tv_title);
        view.setText(title);
        mView.findViewById(R.id.iv_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    /**
     * @param toClass
     */
    public void startActivity(Class<?> toClass) {
        startActivity(getActivity(), toClass, null);
    }

    /**
     * @param toClass
     * @param bundle
     */
    public void startActivity(Class<?> toClass, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), toClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        super.startActivity(intent);
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
     * @param toClass
     * @param bundle
     */
    public void startActivityForResult(Class<?> toClass, Bundle bundle, int resultCode) {
        startActivityForResult(getActivity(), toClass, bundle, resultCode);
    }

    /**
     * @param toClass
     */
    public void startActivityForResult(Class<?> toClass, int resultCode) {
        startActivityForResult(toClass, null, resultCode);
    }


    //关闭软键盘
    public void hideInputMethod() {
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputManager = (InputMethodManager)
                    getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onDestroyView() {
//        LogUtil.d(this.getClass().getSimpleName() + " onDestroyView");
        super.onDestroyView();
//        if (null != mView) {
//            ((ViewGroup) mView.getParent()).removeView(mView);
//        }
        if (unbinder != null)
            unbinder.unbind();
    }
}
