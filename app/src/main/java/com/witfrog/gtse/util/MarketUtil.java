package com.witfrog.gtse.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.StringUtils;

public class MarketUtil {

    public static void toMarket(Context context) {
        LogUtils.d(android.os.Build.BRAND);
        if (TextUtils.equals(android.os.Build.BRAND, "samsung")) {
            goToSamsungMarket(context);
        } else if (StringUtils.equals(android.os.Build.BRAND, "sony")) {
            goToSonyMarket(context);
        } else {
            Uri uri = Uri.parse("market://details?id=" + AppUtils.getAppPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            try {
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void goToSamsungMarket(Context context) {
        Uri uri = Uri.parse("http://www.samsungapps.com/appquery/appDetail.as?appId=" + AppUtils.getAppPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.sec.android.app.samsungapps");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void goToSonyMarket(Context context) {
        Uri uri = Uri.parse("http://m.sonyselect.cn/" + AppUtils.getAppPackageName());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
