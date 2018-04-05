package com.mls.library.util.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.util.Log;

import com.mls.library.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/**
 * Created with Android Studio.
 * User: ryan@xisue.com
 * Date: 11/24/14
 * Time: 2:20 PM
 * Desc: CompressImageUtils
 */
public class CompressImageUtils {

    public static final String TAG = "ChoosePhotoUtils";

    public static void compressImageFile(CropParams cropParams, Uri originUri, Uri compressUri) {
        Bitmap bitmap = null;
        OutputStream out = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(originUri.getPath(), options);
            // Calculate inSampleSize
            int minSideLength = cropParams.compressWidth > cropParams.compressHeight
                    ? cropParams.compressHeight : cropParams.compressWidth;
            options.inSampleSize = computeSampleSize(options, minSideLength, cropParams.compressWidth * cropParams.compressHeight);
            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(originUri.getPath(), options);
            LogUtil.e("原来的角度" + readPictureDegree(originUri.getPath()));
            bitmap = rotaingImageView(readPictureDegree(originUri.getPath()), bitmap);
            File compressFile = new File(compressUri.getPath());
            if (!compressFile.exists()) {
                boolean result = compressFile.createNewFile();
                Log.d(TAG, "Target " + compressUri + " not exist, create a new one " + result);
            }
            out = new FileOutputStream(compressFile);
            boolean result = bitmap.compress(Bitmap.CompressFormat.JPEG, cropParams.compressQuality, out);
            Log.d(TAG, "Compress bitmap " + (result ? "succeed" : "failed"));
        } catch (Exception e) {
            Log.e(TAG, "compressInputStreamToOutputStream", e);
        } finally {
            if (bitmap != null)
                bitmap.recycle();
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }

    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);
        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }
        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;
        LogUtil.e("w" + w + "h" + h);
        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));
        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }
        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    //获取图片的旋转角度
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            LogUtil.e("orientationorientation" + orientation);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /*
   * 旋转图片
   * @param angle
   * @param bitmap
   * @return Bitmap
   */
    public static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        //旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        System.out.println("angle2=" + angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0,
                bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    public static void compressOriginImage(Uri originUri) {
        Bitmap bitmap = null;
        OutputStream out = null;
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(originUri.getPath(), options);
            //5.得到图片的宽和高
            int outWidth = options.outWidth;
            int outHeight = options.outHeight;
            //6.计算缩放比
            int mscare = 1;
            int scareX = outWidth /CropParams.DEFAULT_COMPRESS_WIDTH;
            int scareY = outHeight /CropParams.DEFAULT_COMPRESS_HEIGHT;
            int min = Math.max(scareX, scareY);
            if (min > 1) {
                mscare = min;
            }
            //7.按照缩放比显示
            options.inSampleSize = mscare;
            //8.真正解析图片
            options.inJustDecodeBounds = false;
            bitmap = BitmapFactory.decodeFile(originUri.getPath(), options);
            LogUtil.e("原来的角度" + readPictureDegree(originUri.getPath()));
            bitmap = rotaingImageView(readPictureDegree(originUri.getPath()), bitmap);
            File compressFile = new File(originUri.getPath());
            if (!compressFile.exists()) {
                boolean result = compressFile.createNewFile();
                Log.d(TAG, "Target " + originUri + " not exist, create a new one " + result);
            }
            out = new FileOutputStream(compressFile);
            boolean result = bitmap.compress(Bitmap.CompressFormat.JPEG,50, out);
            Log.d(TAG, "Compress bitmap " + (result ? "succeed" : "failed"));
        } catch (Exception e) {
            Log.e(TAG, "compressInputStreamToOutputStream", e);
        } finally {
            if (bitmap != null)
                bitmap.recycle();
            try {
                if (out != null) {
                    out.flush();
                    out.close();
                }
            } catch (IOException ignore) {
            }
        }
    }
}
