package com.app.library.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

import com.mls.library.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


/**
 * Description: 文件管理工具类
 *
 */

public class FileUtil {

    /**
     * 打开文件管理器
     *
     * @param activity
     * @param requestCode
     */
    public static void showFileChooser(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            activity.startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"), requestCode);
        } catch (android.content.ActivityNotFoundException ex) {
            LogUtil.e("Please install a File Manager.");
        }
    }

    @SuppressLint("NewApi")
    public static String getFilePath(Context context, Uri uri) {
        String selection = null;
        String[] selectionArgs = null;
        // Uri is different in versions after KITKAT (Android 4.4), we need to
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, uri)) {//DocumentsContract.isDocumentUri(context.getApplicationContext(), uri))
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                return Environment.getExternalStorageDirectory() + "/" + split[1];
            } else if (isDownloadsDocument(uri)) {
                final String id = DocumentsContract.getDocumentId(uri);
                uri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            } else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("image".equals(type)) {
                    uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }
                selection = "_id=?";
                selectionArgs = new String[]{
                        split[1]
                };
            }
        }
        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = {
                    MediaStore.Images.Media.DATA
            };
            Cursor cursor = null;
            try {
                cursor = context.getContentResolver()
                        .query(uri, projection, selection, selectionArgs, null);
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                if (cursor.moveToFirst()) {
                    return cursor.getString(column_index);
                }
            } catch (Exception e) {
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    private static boolean isExternalStorageDocument(Uri uri) {
        return uri.getHost().equals("com.android.externalstorage.documents");
    }

    private static boolean isDownloadsDocument(Uri uri) {
        return uri.getHost().equals("com.android.providers.downloads.documents");
    }

    private static boolean isMediaDocument(Uri uri) {
        return uri.getHost().equals("com.android.providers.media.documents");
    }

    /**
     * 获取文件intent
     *
     * @param filePath
     */
    public static Intent getFileIntent(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) return null;
        /* 取得扩展名 */
        String end = subSubEnd(file.getName());
        /* 依扩展名的类型决定MimeType */
        if (isAudio(end)) {
            return getAudioFileIntent(filePath);
        } else if (isVideo(end)) {
            return getAudioFileIntent(filePath);
        } else if (isJpg(end)) {
            return getImageFileIntent(filePath);
        } else if (end.equalsIgnoreCase("apk")) {
            return getApkFileIntent(filePath);
        } else if (isPpt(end)) {
            return getPptFileIntent(filePath);
        } else if (isExcel(end)) {
            return getExcelFileIntent(filePath);
        } else if (isDoc(end)) {
            return getWordFileIntent(filePath);
        } else if (isPdf(end)) {
            return getPdfFileIntent(filePath);
        } else if (end.equalsIgnoreCase("chm")) {
            return getChmFileIntent(filePath);
        } else if (isTxt(end)) {
            return getTextFileIntent(filePath, false);
        } else {
            return getAllIntent(filePath);
        }
    }

    private static String subSubEnd(String name) {
        try {
            String end = name.substring(name.lastIndexOf(".") + 1, name.length()).toLowerCase();
            return end;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 打开文件
     *
     * @param filePath
     */
    public static void openFile(String filePath, Activity c) {

        try {
            Intent intent = getFileIntent(filePath);
            String end = subSubEnd(filePath);
            if (isDoc(end) || isPdf(end) || isTxt(end) || isExcel(end) || isPpt(end)) {

                List<ResolveInfo> resInfo = c.getPackageManager().queryIntentActivities(intent, 0);
                if (!resInfo.isEmpty()) {
                    List<Intent> targetedShareIntents = new ArrayList<Intent>();
                    for (ResolveInfo info : resInfo) {
                        Intent targeted = getFileIntent(filePath);
                        ActivityInfo activityInfo = info.activityInfo;
                        // judgments : activityInfo.packageName, activityInfo.name, etc.
                        if (activityInfo.packageName.contains("com.tencent.mobileqq")
                                || activityInfo.packageName.contains("com.eg.android.AlipayGphone")
                                || activityInfo.packageName.contains("com.tencent")) {
                            continue;
                        }
                        targeted.setPackage(activityInfo.packageName);
                        targetedShareIntents.add(targeted);
                    }
                    if (targetedShareIntents.size() != 0) {
                        Intent chooserIntent = Intent.createChooser(targetedShareIntents.remove(0), "Select app to Open");
                        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, targetedShareIntents.toArray(new Parcelable[]{}));
                        c.startActivity(chooserIntent);
                    } else {
                        ToastUtil.show("无应用可以打开文档，请下载Office软件");
                    }
                } else {
                    ToastUtil.show("无应用可以打开文档，请下载Office软件");
                }
            } else {

                isIntentAvailable(c, intent);
                c.startActivity(intent);
            }


        } catch (Exception e) {
            e.printStackTrace();
            ToastUtil.show("无应用可以打开文档，请下载Office软件");
        }
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getAllIntent(String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    //Android获取一个用于打开APK文件的intent
    public static Intent getApkFileIntent(String param) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    //Android获取一个用于打开VIDEO文件的intent
    public static Intent getVideoFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "video/*");
        return intent;
    }

    //Android获取一个用于打开AUDIO文件的intent
    public static Intent getAudioFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "audio/*");
        return intent;
    }

    //Android获取一个用于打开Html文件的intent
    public static Intent getHtmlFileIntent(String param) {
        Uri uri = Uri.parse(param).buildUpon().encodedAuthority("com.android.htmlfileprovider").scheme("content").encodedPath(param).build();
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(uri, "text/html");
        return intent;
    }

    //Android获取一个用于打开图片文件的intent
    public static Intent getImageFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "image/*");
        LogUtil.d(uri.toString() + " path " + param);
        return intent;
    }

    //Android获取一个用于打开PPT文件的intent
    public static Intent getPptFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-powerpoint");
        return intent;
    }

    //Android获取一个用于打开Excel文件的intent
    public static Intent getExcelFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "application/vnd.ms-excel");
        return intent;
    }

    //Android获取一个用于打开Word文件的intent
    public static Intent getWordFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "application/msword");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        return intent;
    }

    //Android获取一个用于打开CHM文件的intent
    public static Intent getChmFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "application/x-chm");
        return intent;
    }

    //Android获取一个用于打开文本文件的intent
    public static Intent getTextFileIntent(String param, boolean paramBoolean) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (paramBoolean) {
//            Uri uri1 = Uri.parse(param);
            Uri uri = getFileUri(new File(param));
            intent.setDataAndType(uri, "text/plain");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
//            Uri uri2 = Uri.fromFile(new File(param));
            Uri uri = getFileUri(new File(param));
            intent.setDataAndType(uri, "text/plain");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }
        return intent;
    }

    //Android获取一个用于打开PDF文件的intent
    public static Intent getPdfFileIntent(String param) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = getFileUri(new File(param));
        intent.setDataAndType(uri, "application/pdf");
        return intent;
    }

    public static int getIconBuFileSuffix(String suffix) {
        if (isDoc(suffix)) {
            return R.drawable.icon_word;
        } else if (isJpg(suffix)) {
            return R.drawable.icon_jpg;
        } else if (isAudio(suffix)) {
            return R.drawable.icon_video;
        } else if (isVideo(suffix)) {
            return R.drawable.icon_video;
        } else if (isExcel(suffix)) {
            return R.drawable.icon_excel;
        } else if (isPdf(suffix)) {
            return R.drawable.icon_pdf;
        } else if (isPpt(suffix)) {
            return R.drawable.icon_ppt;
        } else if (isTxt(suffix)) {
            return R.drawable.icon_txt;
        } else {
            return R.drawable.icon_other;
        }
    }

    public static boolean isDoc(String suffix) {
        return suffix.equalsIgnoreCase("doc") || suffix.equalsIgnoreCase("docx");
    }

    public static boolean isJpg(String end) {
        return end.equalsIgnoreCase("jpg") || end.equalsIgnoreCase("gif") || end.equalsIgnoreCase("png") ||
                end.equalsIgnoreCase("jpeg") || end.equalsIgnoreCase("bmp");
    }

    public static boolean isAudio(String end) {
        return end.equalsIgnoreCase("m4a") || end.equalsIgnoreCase("mp3") || end.equalsIgnoreCase("mid") ||
                end.equalsIgnoreCase("xmf") || end.equalsIgnoreCase("ogg") || end.equalsIgnoreCase("wav");
    }

    public static boolean isVideo(String end) {
        return end.equalsIgnoreCase("mp4") || end.equalsIgnoreCase("3gp");
    }

    public static boolean isExcel(String suffix) {
        return suffix.equalsIgnoreCase("xls") || suffix.equalsIgnoreCase("xlsx");
    }

    public static boolean isPdf(String suffix) {
        return suffix.equalsIgnoreCase("pdf");
    }

    public static boolean isPpt(String suffix) {
        return suffix.equalsIgnoreCase("ppt") || suffix.equalsIgnoreCase("pptx");
    }

    public static boolean isTxt(String suffix) {
        return suffix.equalsIgnoreCase("txt");
    }

    private static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.GET_META_DATA);
        return list.size() > 0;
    }

    private static Uri getFileUri(File file) {
//        Uri.fromFile(file);
        return Uri.fromFile(file);
    }
}
