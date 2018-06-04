package com.flashpurchase.app.fragment.classification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;
import com.flashpurchase.app.adapter.ShoppingLeftAdapter;
import com.flashpurchase.app.model.Dynamics;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 商品分类
 * Created by 10951 on 2018/4/11.
 */

public class GoodsClassification2Fragment extends BaseFragment {


    @BindView(R.id.lv_left)
    ListView mLvLeft;
    @BindView(R.id.recycler_right)
    ListView mRecyclerRight;

    private ShoppingLeftAdapter mLeftAdapter;
    private String[] dynamics = {"全部商品", "十元专区", "手机专区", "珠宝配饰", "电脑平板", "生活家电", "数码影音", "其他专区"
            , "美食天地", "运动户外", "美妆个护", "家居生活"};
    private List<Dynamics> mDynamics = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_good_classification2;
    }

    @Override
    protected void initView(View view) {
        initTitle("商品分类");
        for (int i = 0; i < dynamics.length; i++) {
            Dynamics dynamic = new Dynamics();
            dynamic.setDynamicName(dynamics[i]);
            mDynamics.add(dynamic);
        }
        mLeftAdapter = new ShoppingLeftAdapter(mDynamics);
        mLvLeft.setAdapter(mLeftAdapter);
        mLvLeft.setItemChecked(0, true);
    }
}
