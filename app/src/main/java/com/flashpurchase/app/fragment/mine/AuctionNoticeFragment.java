package com.flashpurchase.app.fragment.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.flashpurchase.app.R;
import com.flashpurchase.app.adapter.AuctionNoticeAdapter;
import com.flashpurchase.app.model.MessageInfo;
import com.flashpurchase.app.view.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by zms on 2018/4/8.
 */

public class AuctionNoticeFragment extends BaseFragment {
    @BindView(R.id.auction_message_list)
    ListView mAuctionMessageList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private AuctionNoticeAdapter mAdapter;
    private List<MessageInfo> mList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_auction_message;
    }

    @Override
    protected void initView(View view) {
        MessageInfo m1 = new MessageInfo();
        m1.setTime("2018-05-06");
        m1.setTitle("DW00002期拍卖公告第二期");
        MessageInfo m2 = new MessageInfo();
        m2.setTime("2018-05-05");
        m2.setTitle("DW00001期拍卖公告第一期");
        mList = new ArrayList<>();
        mList.add(m1);
        mList.add(m2);
        mAdapter = new AuctionNoticeAdapter(mList);
        mAuctionMessageList.setAdapter(mAdapter);
    }
}
