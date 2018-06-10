package com.flashPurchase.app.fragment.classification;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.flashPurchase.app.R;
import com.flashPurchase.app.model.TimeBean;
import com.flashPurchase.app.util.TimeTools;
import com.flashPurchase.app.util.TimerItemUtil;
import com.flashPurchase.app.view.RefreshLayout;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/23.
 */

public class ClassificationListFragment extends BaseFragment {
    @BindView(R.id.goods_list)
    RecyclerView mGoodsList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    GoodsTimerAdapter mAdapter;
    List<TimeBean> timerItems = TimerItemUtil.getTimerItemList();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_class_list;
    }

    @Override
    protected void initView(View view) {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mGoodsList.setLayoutManager(layoutManager);
        mAdapter = new GoodsTimerAdapter(getContext(), timerItems);
        mGoodsList.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                mAdapter.add(new TimeBean("new ", System.currentTimeMillis() + 11 * 1000));
            }
        });
    }

    public static class GoodsTimerAdapter extends RecyclerView.Adapter<GoodsTimerAdapter.ViewHolder> {

        private List<TimeBean> mDatas;
        //用于退出activity,避免countdown，造成资源浪费。
        private SparseArray<CountDownTimer> countDownMap;
        private Context mContext;

        public GoodsTimerAdapter(Context context, List<TimeBean> datas) {
            mDatas = datas;
            mContext = context;
            countDownMap = new SparseArray<>();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_goods_cls, parent, false);
            return new ViewHolder(view);
        }

        /**
         * 清空资源
         */
        public void cancelAllTimers() {
            if (countDownMap == null) {
                return;
            }
            for (int i = 0, length = countDownMap.size(); i < length; i++) {
                CountDownTimer cdt = countDownMap.get(countDownMap.keyAt(i));
                if (cdt != null) {
                    cdt.cancel();
                }
            }
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            final TimeBean data = mDatas.get(position);
            holder.mTvTime.setText(data.name);
            long time = data.expirationTime;
            time = time - System.currentTimeMillis();
            //将前一个缓存清除
            if (holder.countDownTimer != null) {
                holder.countDownTimer.cancel();
            }
            if (time > 0) {
                holder.countDownTimer = new CountDownTimer(time, 1000) {
                    public void onTick(long millisUntilFinished) {
                        holder.mTvTime.setText(TimeTools.getCountTimeByLong(millisUntilFinished));
                    }

                    public void onFinish() {
                        holder.mTvTime.setText("00:00:00");
                        holder.mTvGooodsTitle.setText(data.name + ":结束");
                    }
                }.start();

                countDownMap.put(holder.mTvTime.hashCode(), holder.countDownTimer);
            } else {
                holder.mTvTime.setText("00:00:00");
                holder.mTvGooodsTitle.setText(data.name + ":结束");
            }

        }

        @Override
        public int getItemCount() {
            if (mDatas != null && !mDatas.isEmpty()) {
                return mDatas.size();
            }
            return 0;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            //        @BindView(R.id.iv_goods)
//        ImageView mIvGoods;
//        @BindView(R.id.tv_gooods_title)
//        TextView mTvGooodsTitle;
//        @BindView(R.id.tv_goods_price)
//        TextView mTvGoodsPrice;
//        @BindView(R.id.tv_people_num)
//        TextView mTvPeopleNum;
//        @BindView(R.id.tv_time)
//        TextView mTvTime;
            public TextView mTvGooodsTitle;
            public TextView mTvTime;
            public CountDownTimer countDownTimer;

            public ViewHolder(View itemView) {
                super(itemView);
                mTvGooodsTitle = (TextView) itemView.findViewById(R.id.tv_gooods_title);
                mTvTime = (TextView) itemView.findViewById(R.id.tv_time);
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mAdapter != null) {
            mAdapter.cancelAllTimers();
        }
    }
}
