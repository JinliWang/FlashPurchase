package com.flashpurchase.app.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;
import com.flashpurchase.app.adapter.HomeListAdapter;
import com.flashpurchase.app.model.HomeList;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 10951 on 2018/4/6.
 */

public class HomeListFragment extends BaseFragment {
    @BindView(R.id.list)
    RecyclerView mList;

    private HomeListAdapter mHomeListAdapter;
    private List<HomeList> mLists;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initView(View view) {
        HomeList homeList1 = new HomeList();
        homeList1.setGoodsName("iphone 8 256GB");
        homeList1.setGoodsPrice("￥8288.00");
        HomeList homeList2 = new HomeList();
        homeList2.setGoodsName("iphone 6 256GB");
        homeList2.setGoodsPrice("￥1188.00");
        HomeList homeList3 = new HomeList();
        homeList3.setGoodsName("iphone 7 256GB");
        homeList3.setGoodsPrice("￥8822.00");
        HomeList homeList4 = new HomeList();
        homeList4.setGoodsName("iphone X 256GB");
        homeList4.setGoodsPrice("￥8888.00");
        mLists = new ArrayList<>();
        mLists.add(homeList1);
        mLists.add(homeList2);
        mLists.add(homeList3);
        mLists.add(homeList4);
        mHomeListAdapter = new HomeListAdapter();
        mHomeListAdapter.setDataList(mLists);
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        mList.setAdapter(mHomeListAdapter);
        mList.setLayoutManager(gridLayoutManager);

    }
}
