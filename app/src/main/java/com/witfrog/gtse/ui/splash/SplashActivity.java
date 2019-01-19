package com.witfrog.gtse.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.ui.home.HomeActivity;
import com.witfrog.gtse.ui.login.LoginActivity;
import com.witfrog.gtse.util.SPManager;

public class SplashActivity extends BaseActivity {

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView() {
    }

    @Override
    public void doBusiness() {
        if (SPManager.getId().isEmpty()) {
            LoginActivity.start(this);
        } else {
            HomeActivity.start(this);
        }
        finish();
    }
}
