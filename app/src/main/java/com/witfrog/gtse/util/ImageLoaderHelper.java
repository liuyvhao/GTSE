package com.witfrog.gtse.util;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.widget.ImageView;

import com.blankj.utilcode.util.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.witfrog.gtse.R;

import java.io.File;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class ImageLoaderHelper {

    /**
     * 加载网络图片
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url.replace("\\", ""))
                .preload();// 预加载
        Glide.with(context)
                .load(url.replace("\\", ""))
                .apply(getOptions())
                .into(imageView);
    }

    /**
     * 加载本地图片
     */
    public static void loadImage(Context context, File file, ImageView imageView) {
        Glide.with(context)
                .load(file)
                .apply(getNoCacheOptions())
                .into(imageView);
    }

    /**
     * 加载应用资源
     */
    public static void loadImage(Context context, int resource, ImageView imageView) {
        Glide.with(context)
                .load(resource)
                .apply(getNoCacheOptions())
                .into(imageView);
    }

    /**
     * 加载二进制流
     */
    public static void loadImage(Context context, byte[] image, ImageView imageView) {
        Glide.with(context)
                .load(image)
                .apply(getNoCacheOptions())
                .into(imageView);
    }

    /**
     * 加载Uri图片
     */
    public static void loadImage(Context context, Uri uri, ImageView imageView) {
        Glide.with(context)
                .load(uri)
                .apply(getNoCacheOptions())
                .into(imageView);
    }

    public static void loadCirclImage(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(getCircleOptions())
                .into(imageView);
    }

    public static void loadCirclImage1(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .apply(getCircleOptions1())
                .into(imageView);
    }

    private static RequestOptions getOptions() {
        return new RequestOptions()
                .placeholder(new ColorDrawable(Utils.getApp().getResources().getColor(R.color.light_gray2)))
                .error(new ColorDrawable(Utils.getApp().getResources().getColor(R.color.light_gray2)));
    }

    private static RequestOptions getNoCacheOptions() {
        return getOptions()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true);
    }

    private static RequestOptions getCircleOptions() {
        return new RequestOptions()
                .placeholder(R.drawable.ic_avatar_white)
                .error(R.drawable.ic_avatar_white)
                .transforms(new CircleCrop());
    }

    private static RequestOptions getCircleOptions1() {
        return new RequestOptions()
                .placeholder(R.drawable.ic_avatar_black)
                .error(R.drawable.ic_avatar_black)
                .transforms(new CircleCrop());
    }

    private static RequestOptions getRoundedCornersOptions() {
        return getNoCacheOptions().transforms(new RoundedCornersTransformation(8, 0));
    }

}