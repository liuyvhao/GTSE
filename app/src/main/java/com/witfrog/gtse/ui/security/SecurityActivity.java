package com.witfrog.gtse.ui.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;

public class SecurityActivity extends BaseActivity<SecurityPresenter> implements SecurityView {

    public static void start(Context context) {
        Intent intent = new Intent(context, SecurityActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_security;
    }

    @Override
    public void initView() {
        mPresenter = new SecurityPresenter(this);
        setTitleText(R.string.security_center);
    }

    @Override
    public void doBusiness() {
    }
}
