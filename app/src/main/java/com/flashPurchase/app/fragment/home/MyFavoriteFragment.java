package com.flashPurchase.app.fragment.home;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.library.base.BaseFragment;
import com.app.library.base.BaseRecyclerAdapter;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.adapter.HomeListAdapter;
import com.flashPurchase.app.event.RefreshGoodsEvent;
import com.flashPurchase.app.model.bean.RecommendMoreResponse;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/4/6.
 */

public class MyFavoriteFragment extends BaseFragment implements BaseRecyclerAdapter.OnItemClickListener {
    @BindView(R.id.list)
    RecyclerView mList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.tv_go_buy)
    TextView mTvGoBuy;
    @BindView(R.id.lin_no_data)
    LinearLayout mLinNoData;

    private WebSocketClient mWebSocketClient;
    private HomeListAdapter mAdapter;
    private int pageIndex = 1;
    private RecommendMoreResponse mHomeBean;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_list;
    }

    @Override
    protected void initView(View view) {
        EventBus.getDefault().register(this);
        mAdapter = new HomeListAdapter();
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity(), 2);
        mList.setAdapter(mAdapter);
        mList.setLayoutManager(gridLayoutManager);
        mAdapter.setOnItemClickListener(this);
        mRefreshLayout.setEnableRefresh(false);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setToken(SpManager.getToken());
                    more.setUrlMapping("collect-myCollect");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myCollect());
                    break;
                case 1:
                    mRefreshLayout.setVisibility(View.VISIBLE);
                    mLinNoData.setVisibility(View.GONE);
                    mAdapter.setDataList(mHomeBean.getResponse().getGoods());
                    mRefreshLayout.setData(mHomeBean.getResponse().getGoods());
                    break;
                case 2:
                    mRefreshLayout.setVisibility(View.GONE);
                    mLinNoData.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };

    @Override
    protected void loadData(Bundle savedInstanceState) {
        super.loadData(savedInstanceState);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://39.104.102.255:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                }

                @Override
                public void onMessage(String message) {
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        handler.sendMessage(msg);
                    } else {
                        LogUtil.d(message);
                        try {
                            Gson gson = new Gson();
                            mHomeBean = gson.fromJson(message, RecommendMoreResponse.class);
                            Message msg = new Message();
                            msg.what = 1;
                            handler.sendMessage(msg);
                        } catch (Exception e) {
                            Message msg = new Message();
                            msg.what = 2;
                            handler.sendMessage(msg);
                        }
                    }
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {

                }

                @Override
                public void onError(Exception ex) {

                }
            };
            mWebSocketClient.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void itemClick(int position) {
        Bundle bundle = new Bundle();
        bundle.putString("goodsid", mHomeBean.getResponse().getGoods().get(position).getId());
        bundle.putString("time", mHomeBean.getResponse().getGoods().get(position).getTime());
        startActivity(GoodsDetailActivity.class, bundle);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(RefreshGoodsEvent event) {
        Message msg = new Message();
        msg.what = 0;
        handler.sendMessage(msg);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
