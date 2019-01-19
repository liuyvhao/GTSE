package com.witfrog.gtse.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.FragmentUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.Product;
import com.witfrog.gtse.model.Information;
import com.witfrog.gtse.model.InformationData;
import com.witfrog.gtse.model.MyIntegral;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.WalletData;
import com.witfrog.gtse.ui.home.fragment.Home0Fragment;
import com.witfrog.gtse.ui.home.fragment.Home1Fragment;
import com.witfrog.gtse.ui.home.fragment.Home2Fragment;
import com.witfrog.gtse.ui.home.fragment.Home3Fragment;
import com.witfrog.gtse.util.SPManager;

import java.util.List;

import butterknife.BindViews;
import butterknife.OnCheckedChanged;

public class HomeActivity extends BaseActivity<HomePresenter> implements HomeView {

    @BindViews({R.id.rb_tab0, R.id.rb_tab1, R.id.rb_tab2, R.id.rb_tab3})
    List<RadioButton> mRadioButtons;
    private Fragment[] mFragments = new Fragment[4];
    private long       mExitTime  = 0;

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_home;
    }

    @Override
    public void initView() {
        mPresenter = new HomePresenter(this);
        mFragments[0] = Home0Fragment.newInstance();
        mFragments[1] = Home1Fragment.newInstance();
        mFragments[2] = Home2Fragment.newInstance();
        mFragments[3] = Home3Fragment.newInstance();
        FragmentUtils.add(getSupportFragmentManager(), mFragments, R.id.fragment_container, 0);
        mRadioButtons.get(0).setChecked(true);
    }

    @Override
    public void doBusiness() {
        init();
    }

    private void init() {
    }

    @OnCheckedChanged({R.id.rb_tab0, R.id.rb_tab1, R.id.rb_tab2, R.id.rb_tab3})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            int index = mRadioButtons.indexOf(buttonView);
            FragmentUtils.showHide(index, mFragments);
            switch (index) {
                case 0: {
                    BarUtils.setStatusBarLightMode(this, true);
                    setTitleText(R.string.tab_text0);
                    getToolbar().setIcon(null);
                    mViewBgTopBar.setBackgroundResource(R.drawable.bg_top_bar);
                    mPresenter.getMallData(1);
                }
                break;
                case 1: {
                    BarUtils.setStatusBarLightMode(this, true);
                    setTitleText(R.string.tab_text1);
                    getToolbar().setIcon(null);
                    mViewBgTopBar.setBackgroundResource(R.drawable.bg_top_bar);
                    mPresenter.getInformationData();
                    mPresenter.getInformationList("", 1);
                }
                break;
                case 2: {
                    BarUtils.setStatusBarLightMode(this, false);
                    setTitleText("");
                    getToolbar().setIcon(R.drawable.ic_logo_wallet);
                    mViewBgTopBar.setBackgroundResource(R.color.app_color1);
                    mPresenter.getWalletData(SPManager.getId());
                }
                break;
                case 3: {
                    BarUtils.setStatusBarLightMode(this, true);
                    setTitleText(R.string.tab_text3);
                    getToolbar().setIcon(null);
                    mViewBgTopBar.setBackgroundResource(R.drawable.bg_top_bar);
                    mPresenter.getMyIntegral(SPManager.getId());
                }
                break;
                default:
                    break;
            }
        }
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtils.showShort(R.string.double_click_exit);
            mExitTime = System.currentTimeMillis();
        } else {
            AppUtils.exitApp();
        }
    }

    @Override
    public void getMallData(PageData<Product> pageData) {
        sendMessage(Constant.MSG_GET_MALL_DATA, pageData);
    }

    @Override
    public void getInformationData(InformationData informationData) {
        sendMessage(Constant.MSG_GET_INFORMATION_DATA, informationData);
    }

    @Override
    public void getInformationList(PageData<Information> pageData) {
        sendMessage(Constant.MSG_GET_INFORMATION_LIST, pageData);
    }

    @Override
    public void getWalletData(WalletData walletData) {
        sendMessage(Constant.MSG_GET_WALLET_DATA, walletData);
    }

    @Override
    public void getMyIntegral(MyIntegral myIntegral) {
        sendMessage(Constant.MSG_GET_MY_INTEGRAL, myIntegral);
    }
}
