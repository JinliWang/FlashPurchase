package com.flashPurchase.app.activity.goods;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.ToastUtil;
import com.flashPurchase.app.Constant.SpManager;
import com.flashPurchase.app.R;
import com.flashPurchase.app.activity.MainActivity;
import com.flashPurchase.app.model.bean.Login;
import com.flashPurchase.app.model.request.LoginReq;
import com.flashPurchase.app.model.request.MyRequset;
import com.google.gson.Gson;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 10951 on 2018/7/19.
 */

public class AucBaskActivity extends BaseActivity {
    @BindView(R.id.tv_right)
    TextView mRelTitle;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;

    private String goodsid;

    private WebSocketClient mWebSocketClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_auc_bask;
    }

    @Override
    protected void initView() {
        initTitle("发布晒单", "提交");
        goodsid = extraDatas.getString("id");
        mRelTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyRequset loginReq = new MyRequset();
                MyRequset.Parameter parameter = new MyRequset.Parameter();
                parameter.setToken(SpManager.getToken());
                parameter.setGoodsId(goodsid);
                parameter.setPics("");
                parameter.setComments(mEtPwd.getText().toString());
                loginReq.setUrlMapping("comment-save");
                loginReq.setParameter(parameter);
                mWebSocketClient.send(loginReq.addBask());
            }
        });
    }

    @Override
    protected void initData(Bundle bundle) {
        super.initData(bundle);
        try {
            mWebSocketClient = new WebSocketClient(new URI("ws://120.78.204.97:8086/auction?user=" + SpManager.getClientId()), new Draft_17()) {
                @Override
                public void onOpen(ServerHandshake handshakedata) {
                }

                @Override
                public void onMessage(String message) {
                    LogUtil.d(message);
                    if (!message.contains("response")) {
                        Message msg = new Message();
                        msg.what = 0;
                        mHandler.sendMessage(msg);
                    } else if (message.contains("comment-save")) {//返回收藏状态，判断是否收藏
                        if (message.contains("\"success\":1")) {
                            Message msg = new Message();
                            msg.what = 1;
                            mHandler.sendMessage(msg);
                        }else if(message.contains("\"success\":0")) {
                            Message msg = new Message();
                            msg.what = 2;
                            mHandler.sendMessage(msg);
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

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:

                    break;
                case 1:
                    ToastUtil.show("晒单成功！");
                    finish();
                    break;
                case 2:
                    ToastUtil.show("晒单失败，请重试！");
                    break;
            }
        }
    };

}
