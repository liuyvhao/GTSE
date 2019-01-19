package com.witfrog.gtse.ui.setting;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.ui.login.LoginActivity;
import com.witfrog.gtse.ui.usualaddress.UsualAddressActivity;
import com.witfrog.gtse.ui.usualcontacts.UsualContactsActivity;
import com.witfrog.gtse.util.DialogHelper;
import com.witfrog.gtse.util.SPManager;

import butterknife.OnClick;

public class SettingActivity extends BaseActivity<SettingPresenter> implements SettingView {

    private Dialog mDialog;

    public static void start(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {
        mPresenter = new SettingPresenter(this);
        setTitleText(R.string.setting_center);
    }

    @Override
    public void doBusiness() {
    }

    @OnClick({R.id.tv_usual_address, R.id.tv_usual_contacts, R.id.btn_logout})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_usual_address: {
                UsualAddressActivity.start(this);
            }
            break;
            case R.id.tv_usual_contacts: {
                UsualContactsActivity.start(this);
            }
            break;
            case R.id.btn_logout: {
                mDialog = DialogHelper.showConfirmDialog(R.string.log_out, v -> {
                    mDialog.dismiss();
                    SPManager.clearUser();
                    ActivityUtils.finishToActivity(LoginActivity.class, false);
                });
            }
            break;
            default:
                break;
        }
    }
}
