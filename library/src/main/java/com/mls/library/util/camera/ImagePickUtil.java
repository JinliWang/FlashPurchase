package com.mls.library.util.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.mls.library.R;
import com.mls.library.util.LogUtil;
import com.mls.library.util.UIUtil;


/**
 * @author CXX
 * @since 2015/5/28
 */
public class ImagePickUtil extends DialogFragment implements OnClickListener, CropHandler {

    CropParams mCropParams;
    private static boolean isHeard;
    private static boolean isCustomer;
    private static HeadPickListener mHeadPickListener;

    public static ImagePickUtil newInstance() {
        ImagePickUtil dialog = new ImagePickUtil();
        dialog.setStyle(STYLE_NO_TITLE, R.style.DialogHeadPick);
        return dialog;
    }

    public ImagePickUtil show(FragmentActivity ctx, HeadPickListener headPickListener, Boolean isHeard, Boolean isCustomer) {
        mHeadPickListener = headPickListener;
        this.isHeard = isHeard;
        this.isCustomer = isCustomer;
        ImagePickUtil dialog;
        dialog = newInstance();
        dialog.show(ctx.getSupportFragmentManager(), "ImagePickUtil");
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mCropParams = new CropParams(getActivity());
        View view = UIUtil.inflate(R.layout.dialog_head_pick);
        view.findViewById(R.id.btn_camera).setOnClickListener(this);
        view.findViewById(R.id.btn_album).setOnClickListener(this);
        view.findViewById(R.id.btn_cancel).setOnClickListener(this);
        view.findViewById(R.id.rel_main).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        mCropParams.refreshUri();
        int id = v.getId();
        if (id == R.id.btn_camera) {
            LogUtil.e("isHeard" + isHeard + "isCustomer");
            if (isHeard) {
                mCropParams.enable = true;
                mCropParams.compress = true;
                mCropParams.clip = true;
                mCropParams.returnData = true;
                if (isCustomer) {
                    mCropParams.customer = isCustomer;
                }
            } else {
                mCropParams.clip = false;
                mCropParams.enable = false;
                mCropParams.compress = true;
            }
            Intent intent = CropHelper.buildCameraIntent(mCropParams);
            startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
        } else if (id == R.id.btn_album) {

            if (isHeard) {
                mCropParams.enable = false;
                mCropParams.compress = true;
                mCropParams.clip = true;
                if (isCustomer) {
                    mCropParams.customer = isCustomer;
                }

            } else {
                mCropParams.clip = false;
                mCropParams.enable = false;
                mCropParams.compress = true;
            }
            Intent albumIntent = CropHelper.buildGalleryIntent(mCropParams);
            startActivityForResult(albumIntent, CropHelper.REQUEST_CROP);
        } else if (id == R.id.btn_cancel || id == R.id.rel_main) {
            dismiss();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        CropHelper.handleResult(this, requestCode, resultCode, data);
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        LogUtil.e("onPhotoCropped" + uri);
        if (!mCropParams.compress) {
            Bitmap bitmap = null;
            try {
                LogUtil.e("onCompressed .bitmap." + bitmap);
                LogUtil.e("onCompressed .bitmap uri." + uri);
                mHeadPickListener.onHeadPick(uri, "");
                dismiss();
            } catch (Exception ex) {

            }

        }
    }

    @Override
    public void onCompressed(Uri uri) {
        LogUtil.e("onCompressed" + uri.toString().substring(15));
        Bitmap bitmap = null;
        try {
            LogUtil.e("onCompressed .bitmap." + bitmap);
            LogUtil.e("onCompressed .bitmap uri." + uri.toString());
            mHeadPickListener.onHeadPick(uri, "");
            dismiss();
        } catch (Exception ex) {

        }
    }

    @Override
    public void onCompressed(Uri uri, String originalUrl) {
        try {
            mHeadPickListener.onHeadPick(uri, originalUrl);
            dismiss();
        } catch (Exception e) {
            dismiss();
        }
    }

    @Override
    public void onCancel() {
    }

    @Override
    public void onFailed(String message) {
    }

    @Override
    public void handleIntent(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);

    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    public interface HeadPickListener {
        void onHeadPick(Uri headUri, String path);
    }
}
