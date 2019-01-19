package com.witfrog.gtse.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.witfrog.gtse.config.Constant;

import java.io.File;
import java.io.IOException;

public class AlbumCameraHelper {

    public static void openAlbum(Activity context) {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        context.startActivityForResult(intent, Constant.ACTIVITY_RESULT_ALBUM);
    }

    public static void openCamera(Activity context) {
        Uri uri;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File outputImage = new File(context.getExternalCacheDir(),
                    "image.jpg");
            try {
                if (outputImage.exists()) {
                    outputImage.delete();
                }
                outputImage.createNewFile();
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    uri = FileProvider.getUriForFile(context,
                            "com.chubeile.server.FileProvider", outputImage);
                } else {
                    uri = Uri.fromFile(outputImage);
                }
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                context.startActivityForResult(intent, Constant.ACTIVITY_RESULT_CAMERA);
            } catch (Exception e) {
                ToastUtils.showShort("No storage directory.");
            }
        } else {
            ToastUtils.showShort("No storage card.");
        }
    }

    public static void cropAlbumImage(Activity context, Uri uri, String lastName) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            File file = new File(Environment.getExternalStorageDirectory().getPath(),
                    "crop_image" + lastName + ".png");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Uri outputUri = Uri.fromFile(file);

            // 是否可以剪裁
            intent.putExtra("crop", "false");
            // 宽高
            intent.putExtra("outputX", ScreenUtils.getScreenWidth());
            intent.putExtra("outputY", ScreenUtils.getScreenWidth());
            intent.putExtra("scale", true);
            // 如果图片过大，会导致oom，设置为false
            intent.putExtra("return-data", false);
            if (uri != null) {
                intent.setDataAndType(uri, "image/*");
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            // 压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            context.startActivityForResult(intent, Constant.ACTIVITY_RESULT_CROP_IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cropCameraImage(Activity context, File image, String lastName) {
        try {
            Intent intent = new Intent("com.android.camera.action.CROP");
            File file = new File(Environment.getExternalStorageDirectory().getPath(),
                    "crop_image" + lastName + ".png");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            Uri outputUri = Uri.fromFile(file);

            Uri imageUri;
            if (Build.VERSION.SDK_INT >= 24) {
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                imageUri = FileProvider.getUriForFile(context,
                        "com.chubeile.server.FileProvider",
                        image);
            } else {
                imageUri = Uri.fromFile(image);
            }

            // 是否可以剪裁
            intent.putExtra("crop", "false");
            // 宽高
            intent.putExtra("outputX", ScreenUtils.getScreenWidth());
            intent.putExtra("outputY", ScreenUtils.getScreenWidth());
            intent.putExtra("scale", true);
            // 如果图片过大，会导致oom，设置为false
            intent.putExtra("return-data", false);
            if (imageUri != null) {
                intent.setDataAndType(imageUri, "image/*");
            }
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            if (outputUri != null) {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
            }
            intent.putExtra("noFaceDetection", true);
            // 压缩图片
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
            context.startActivityForResult(intent, Constant.ACTIVITY_RESULT_CROP_IMAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据Uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    public static String getRealPathFromUri(Context context, Uri uri) {
        int sdkVersion = Build.VERSION.SDK_INT;
        if (sdkVersion >= 19) { // api >= 19
            return getRealPathFromUriAboveApi19(context, uri);
        } else { // api < 19
            return getRealPathFromUriBelowAPI19(context, uri);
        }
    }

    /**
     * 适配api19以下(不包括api19),根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    private static String getRealPathFromUriBelowAPI19(Context context, Uri uri) {
        return getDataColumn(context, uri, null, null);
    }

    /**
     * 适配api19及以上,根据uri获取图片的绝对路径
     *
     * @param context 上下文对象
     * @param uri     图片的Uri
     * @return 如果Uri对应的图片存在, 那么返回该图片的绝对路径, 否则返回null
     */
    @SuppressLint("NewApi")
    private static String getRealPathFromUriAboveApi19(Context context, Uri uri) {
        String filePath = null;
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // 如果是document类型的 uri, 则通过document id来进行处理
            String documentId = DocumentsContract.getDocumentId(uri);
            if (isMediaDocument(uri)) { // MediaProvider
                // 使用':'分割
                String id = documentId.split(":")[1];

                String selection = MediaStore.Images.Media._ID + "=?";
                String[] selectionArgs = {id};
                filePath = getDataColumn(context, MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection, selectionArgs);
            } else if (isDownloadsDocument(uri)) { // DownloadsProvider
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                filePath = getDataColumn(context, contentUri, null, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是 content 类型的 Uri
            filePath = getDataColumn(context, uri, null, null);
        } else if ("file".equals(uri.getScheme())) {
            // 如果是 file 类型的 Uri,直接获取图片对应的路径
            filePath = uri.getPath();
        }
        return filePath;
    }

    /**
     * 获取数据库表中的 _data 列，即返回Uri对应的文件路径
     */
    private static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        String path = null;

        String[] projection = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(projection[0]);
                path = cursor.getString(columnIndex);
            }
        } catch (Exception e) {
            if (cursor != null) {
                cursor.close();
            }
        }
        return path;
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is MediaProvider
     */
    private static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri the Uri to check
     * @return Whether the Uri authority is DownloadsProvider
     */
    private static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

}
