package com.witfrog.gtse.ui.invite;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;

public class InviteActivity extends BaseActivity<InvitePresenter> implements InviteView {

    public static void start(Context context) {
        Intent intent = new Intent(context, InviteActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_invite;
    }

    @Override
    public void initView() {
        mPresenter = new InvitePresenter(this);
        setTitleText(R.string.my_invitation);
    }

    @Override
    public void doBusiness() {
    }
}
