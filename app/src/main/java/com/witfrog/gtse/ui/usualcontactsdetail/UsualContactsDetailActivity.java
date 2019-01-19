package com.witfrog.gtse.ui.usualcontactsdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.UsualContacts;

public class UsualContactsDetailActivity extends BaseActivity<UsualContactsDetailPresenter> implements UsualContactsDetailView {

    private int           mMode;
    private UsualContacts mUsualContacts;

    public static void start(Context context, int mode, UsualContacts usualContacts) {
        Intent intent = new Intent(context, UsualContactsDetailActivity.class);
        intent.putExtra("mode", mode);
        intent.putExtra("usualContacts", usualContacts);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        assert bundle != null;
        mMode = bundle.getInt("mode");
        mUsualContacts = bundle.getParcelable("usualContacts");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_usual_contacts_detail;
    }

    @Override
    public void initView() {
        mPresenter = new UsualContactsDetailPresenter(this);
        if (mMode == 0) {
            setTitleText(R.string.add_contacts);
        } else {
            setTitleText(R.string.modify_contacts);
        }
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void addUsualContacts() {
        sendMessage(Constant.MSG_REFRESH_USUAL_CONTACTS, null);
        finish();
    }

    @Override
    public void modifyUsualContacts() {
        sendMessage(Constant.MSG_REFRESH_USUAL_CONTACTS, null);
        finish();
    }
}
