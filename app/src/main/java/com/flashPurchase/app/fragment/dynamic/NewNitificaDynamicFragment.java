package com.flashPurchase.app.fragment.dynamic;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.DynamicAdapter;
import com.flashPurchase.app.model.Dynamics;
import com.flashPurchase.app.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/9.
 */

public class NewNitificaDynamicFragment extends BaseFragment {
    @BindView(R.id.iv_left)
    ImageView mIvLeft;
    @BindView(R.id.iv_right)
    ImageView mIvRight;
    @BindView(R.id.nitificate_list)
    ListView mNitificateList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private DynamicAdapter mAdapter;
    private List<Dynamics> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_new_dynamic;
    }

    @Override
    protected void initView(View view) {
        initTitle("最新动态");
        mIvLeft.setVisibility(View.GONE);
        mIvRight.setVisibility(View.VISIBLE);
        mIvRight.setImageDrawable(getContext().getResources().getDrawable(R.drawable.icon_message));

        Dynamics dynamics = new Dynamics();
        dynamics.setComsumer("李元芳");
        dynamics.setCurrentprice("成交价：￥9.99");
        dynamics.setMarketprice("市场价：￥9999.99");
        dynamics.setName("米家智能 米家智能投影机 大屏享受，可远观等等");
        dynamics.setRate("99.0%");
        dynamics.setTime("2018-05-03 21:55:59");
        Dynamics dynamics2 = new Dynamics();
        dynamics2.setComsumer("李元芳");
        dynamics2.setCurrentprice("成交价：￥9.99");
        dynamics2.setMarketprice("市场价：￥9999.99");
        dynamics2.setName("米家智能 米家智能投影机 大屏享受，可远观等等");
        dynamics2.setRate("99.0%");
        dynamics2.setTime("2018-05-03 21:55:59");
        mList = new ArrayList<>();
        mList.add(dynamics);
        mList.add(dynamics2);
        mAdapter = new DynamicAdapter(mList);
        mNitificateList.setAdapter(mAdapter);
    }
}
