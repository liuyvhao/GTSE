package com.witfrog.gtse.ui.integral;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;

public class IntegralActivity extends BaseActivity<IntegralPresenter> implements IntegralView {

    public static void start(Context context) {
        Intent intent = new Intent(context, IntegralActivity.class);
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
        mPresenter = new IntegralPresenter(this);
        setTitleText(R.string.my_integral);
    }

    @Override
    public void doBusiness() {
    }
}
