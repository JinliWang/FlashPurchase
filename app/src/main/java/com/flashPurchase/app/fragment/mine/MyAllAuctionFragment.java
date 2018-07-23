package com.flashPurchase.app.fragment.mine;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.library.base.BaseFragment;
import com.app.library.util.LogUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.goods.GoodsDetailActivity;
import com.flashPurchase.app.activity.mine.ComfirmOrderActivity;
import com.flashPurchase.app.adapter.AllAucAdapter;
import com.flashPurchase.app.adapter.MyAllAucAdapter;
import com.flashPurchase.app.adapter.MyAucAdapter;
import com.flashPurchase.app.model.bean.MyAllAucList;
import com.flashPurchase.app.model.bean.MyAucList;
import com.flashPurchase.app.model.request.MyRequset;
import com.flashPurchase.app.view.RefreshLayout;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 10951 on 2018/7/10.
 */

public class MyAllAuctionFragment extends BaseFragment {
    @BindView(R.id.my_auction_list)
    ListView mMyAuctionList;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private MyAllAucList mMyAucList;
    private AllAucAdapter mMyAucAdapter;
    private List<MyAllAucList.ResponseBean.AucIngBean> mList;

    private WebSocketClient mWebSocketClient;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_auction;
    }

    @Override
    protected void initView(View view) {
        mList = new ArrayList<>();
        mMyAucAdapter = new AllAucAdapter(mList, getContext());
        mMyAuctionList.setAdapter(mMyAucAdapter);
        mMyAuctionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("goodsid", mList.get(i).getGoodsId() + "");
                bundle.putString("time", mList.get(i).getTime() + "");
                if (mList.get(i).getStatus().equals("aucing")) {
                    bundle.putString("isnext", "0");
                } else {
                    bundle.putString("isnext", "1");
                }
                startActivity(GoodsDetailActivity.class, bundle);
            }
        });
        mRefreshLayout.setEnableRefresh(false);
    }

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
                            mMyAucList = gson.fromJson(message, MyAllAucList.class);
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    MyRequset more = new MyRequset();
                    MyRequset.Parameter parameter = new MyRequset.Parameter();
                    parameter.setToken(SpManager.getToken());
                    parameter.setAucSt("0");
                    more.setUrlMapping("goods-myAucIng");
                    more.setParameter(parameter);
                    mWebSocketClient.send(more.myOrder());
                    break;
                case 1:
                    if (mMyAucList.getResponse().getAucIng() != null && mMyAucList.getResponse().getAucIng().size() > 0) {
                        for (MyAllAucList.ResponseBean.AucIngBean bean : mMyAucList.getResponse().getAucIng()) {
                            bean.setStatus("aucing");
                            mList.add(bean);
                        }
                    }
                    if (mMyAucList.getResponse().getAucOut() != null && mMyAucList.getResponse().getAucOut().size() > 0) {
                        for (MyAllAucList.ResponseBean.AucIngBean bean : mMyAucList.getResponse().getAucOut()) {
                            bean.setStatus("aucout");
                            mList.add(bean);
                        }
                    }
                    if (mMyAucList.getResponse().getAucSuc() != null && mMyAucList.getResponse().getAucSuc().size() > 0) {
                        for (MyAllAucList.ResponseBean.AucIngBean bean : mMyAucList.getResponse().getAucSuc()) {
                            bean.setStatus("aucsuc");
                            mList.add(bean);
                        }
                    }
                    if (mMyAucList.getResponse().getAucToBask() != null && mMyAucList.getResponse().getAucToBask().size() > 0) {
                        for (MyAllAucList.ResponseBean.AucIngBean bean : mMyAucList.getResponse().getAucToBask()) {
                            bean.setStatus("auctoback");
                            mList.add(bean);
                        }
                    }
                    if (mMyAucList.getResponse().getAucToPay() != null && mMyAucList.getResponse().getAucToPay().size() > 0) {
                        for (MyAllAucList.ResponseBean.AucIngBean bean : mMyAucList.getResponse().getAucToPay()) {
                            bean.setStatus("auctopay");
                            mList.add(bean);
                        }
                    }
                    if (mMyAucList.getResponse().getAucToReceive() != null && mMyAucList.getResponse().getAucToReceive().size() > 0) {
                        for (MyAllAucList.ResponseBean.AucIngBean bean : mMyAucList.getResponse().getAucToReceive()) {
                            bean.setStatus("auctoreceive");
                            mList.add(bean);
                        }
                    }
                    mMyAucAdapter.notifyDataSetChanged();
                    mRefreshLayout.setData(mList, mMyAucAdapter);
                    break;
            }
        }
    };
}
