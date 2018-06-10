package com.flashPurchase.app.fragment.mine;

import android.view.View;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.flashPurchase.app.R;
import com.flashPurchase.app.adapter.SystemMessageAdapter;
import com.flashPurchase.app.model.MessageInfo;
import com.flashPurchase.app.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by zms on 2018/4/8.
 */

public class SystemMessageFragment extends BaseFragment {

    @BindView(R.id.auction_message_list)
    ListView mAuctionMessageList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private SystemMessageAdapter mAdapter;
    private List<MessageInfo> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_auction_message;
    }

    @Override
    protected void initView(View view) {
        MessageInfo m1 = new MessageInfo();
        m1.setTime("2018-05-06");
        m1.setTitle("关于支付通道升级完成得通知");
        MessageInfo m2 = new MessageInfo();
        m2.setTime("2018-05-05");
        m2.setTitle("关于支付通道升级维护得通知");
        mList = new ArrayList<>();
        mList.add(m1);
        mList.add(m2);
        mAdapter = new SystemMessageAdapter(mList);
        mAuctionMessageList.setAdapter(mAdapter);
    }
}
