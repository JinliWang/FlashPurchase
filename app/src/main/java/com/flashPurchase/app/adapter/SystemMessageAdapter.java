package com.flashPurchase.app.adapter;

import android.view.View;
import android.widget.TextView;

import com.app.library.base.BaseAdapter;
import com.app.library.base.BaseHolder;
import com.app.library.util.UIUtil;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.MessageInfo;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/5/6.
 */

public class SystemMessageAdapter extends BaseAdapter<MessageInfo> {
    public SystemMessageAdapter(List<MessageInfo> mDatas) {
        super(mDatas);
    }

    @Override
    protected BaseHolder getHolder() {
        return new Holder();
    }

    class Holder extends BaseHolder<MessageInfo> {

        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_title)
        TextView mTvTitle;

        @Override
        protected View initView() {
            return UIUtil.inflate(R.layout.item_sys_message);
        }

        @Override
        protected void refreshView() {
            mTvTime.setText(getData().getTime());
            mTvTitle.setText(getData().getTitle());
        }
    }
}
