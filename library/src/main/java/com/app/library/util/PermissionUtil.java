package com.app.library.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Description:
 *
 */

public class PermissionUtil extends Activity {

    public static void requestPermission(final @NonNull Activity activity, final @IntRange(from = 0) int requestCode) {

        if (ContextCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            LogUtil.d("request");
            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else {
            LogUtil.d("Allowed");
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                LogUtil.d("Allow");
            } else {
                // Permission Denied
                Toast.makeText(this, "您拒绝了权限", Toast.LENGTH_SHORT).show();
//                checkCameraPermission();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private static final int MY_PERMISSIONS_REQUEST_CAMERA = 1;
}
