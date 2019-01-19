package com.witfrog.gtse.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.PermissionUtils;
import com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest;
import com.blankj.utilcode.util.SizeUtils;
import com.witfrog.gtse.R;

@SuppressLint("InflateParams")
public class DialogHelper {

    public static void showRationaleDialog(final ShouldRequest shouldRequest) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) {
            return;
        }
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(R.string.permission_rationale_message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> shouldRequest.again(true))
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> shouldRequest.again(false))
                .setCancelable(false)
                .create()
                .show();
    }

    public static void showOpenAppSettingDialog() {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) {
            return;
        }
        new AlertDialog.Builder(topActivity)
                .setTitle(android.R.string.dialog_alert_title)
                .setMessage(R.string.permission_denied_forever_message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> PermissionUtils.launchAppDetailsSettings())
                .setNegativeButton(android.R.string.cancel, (dialog, which) -> {
                })
                .setCancelable(false)
                .create()
                .show();
    }

    public static Dialog showConfirmDialog(int stringId, View.OnClickListener listener) {
        Activity topActivity = ActivityUtils.getTopActivity();
        if (topActivity == null) {
            return null;
        }
        View view = LayoutInflater.from(topActivity).inflate(R.layout.dialog_confirm_alert, null);
        TextView tvConfirmAlert = view.findViewById(R.id.tv_confirm_alert);
        tvConfirmAlert.setText(String.format(topActivity.getString(R.string.confirm_alert), topActivity.getString(stringId)));
        Dialog dialog = new AlertDialog
                .Builder(topActivity)
                .setView(view)
                .show();
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = SizeUtils.dp2px(280);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);

        view.findViewById(R.id.tv_cancel).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.tv_confirm).setOnClickListener(listener);
        return dialog;
    }
}
