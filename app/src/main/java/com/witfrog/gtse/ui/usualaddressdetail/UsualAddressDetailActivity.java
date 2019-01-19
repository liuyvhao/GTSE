package com.witfrog.gtse.ui.usualaddressdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.UsualAddress;

public class UsualAddressDetailActivity extends BaseActivity<UsualAddressDetailPresenter> implements UsualAddressDetailView {

    private int          mMode;
    private UsualAddress mUsualAddress;

    public static void start(Context context, int mode, UsualAddress usualAddress) {
        Intent intent = new Intent(context, UsualAddressDetailActivity.class);
        intent.putExtra("mode", mode);
        intent.putExtra("usualAddress", usualAddress);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        assert bundle != null;
        mMode = bundle.getInt("mode");
        mUsualAddress = bundle.getParcelable("usualAddress");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_usual_address_detail;
    }

    @Override
    public void initView() {
        mPresenter = new UsualAddressDetailPresenter(this);
        if (mMode == 0) {
            setTitleText(R.string.add_address);
        } else {
            setTitleText(R.string.modify_address);
        }
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void addUsualAddress() {
        sendMessage(Constant.MSG_REFRESH_USUAL_ADDRESS, null);
        finish();
    }

    @Override
    public void modifyUsualAddress() {
        sendMessage(Constant.MSG_REFRESH_USUAL_ADDRESS, null);
        finish();
    }
}
