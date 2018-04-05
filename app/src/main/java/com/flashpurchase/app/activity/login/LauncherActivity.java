package com.flashpurchase.app.activity.login;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.app.library.base.BaseActivity;
import com.app.library.util.LogUtil;
import com.app.library.util.NetworkUtil;
import com.app.library.util.StatusBarUtil;
import com.app.library.util.ToastUtil;
import com.flashpurchase.app.R;
import com.flashpurchase.app.activity.MainActivity;

/**
 * Created by Administrator on 2018/1/9 0009.
 */

public class LauncherActivity extends BaseActivity {
    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_launch;
    }

    @Override
    protected void initView() {
        StatusBarUtil.setFull(this);
        requestPermission(this, MY_PERMISSIONS_REQUEST_CAMERA);
        if (!NetworkUtil.hasNetwork(this)) {
            ToastUtil.show("没有网络");
        }
    }

    public void requestPermission(final @NonNull Activity activity, final @IntRange(from = 0) int requestCode) {
        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.d("request");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            LogUtil.d("Allowed");
            enter();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            LogUtil.d("Allow 1");
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                LogUtil.d("Allow 2");
                enter();
            } else {
                Toast.makeText(this, "您拒绝了权限", Toast.LENGTH_SHORT).show();
                requestPermission(this, MY_PERMISSIONS_REQUEST_CAMERA);
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void enter() {
        startActivity(MainActivity.class);
        finish();
    }
}
