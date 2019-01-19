package com.witfrog.gtse.ui.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;

public class AboutActivity extends BaseActivity<AboutPresenter> implements AboutView {

    public static void start(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_integral;
    }

    @Override
    public void initView() {
        mPresenter = new AboutPresenter(this);
        setTitleText(R.string.about_us);
    }

    @Override
    public void doBusiness() {
    }
}
