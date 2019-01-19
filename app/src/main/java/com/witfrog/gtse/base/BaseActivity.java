package com.witfrog.gtse.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.r0adkll.slidr.Slidr;
import com.r0adkll.slidr.model.SlidrConfig;
import com.witfrog.gtse.R;
import com.witfrog.gtse.ui.home.HomeActivity;
import com.witfrog.gtse.ui.login.LoginActivity;
import com.witfrog.gtse.ui.splash.SplashActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {

    protected Activity mActivity;
    public    P        mPresenter;
    protected View     mViewBgTopBar;
    private   Toolbar  mToolbar;
    private   TextView mTvToolbarTitle;
    private   long     lastClick = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        EventBus.getDefault().register(this);
        if (ScreenUtils.isPortrait()) {
            AdaptScreenUtils.adaptWidth(getResources(), 720);
        } else {
            AdaptScreenUtils.adaptWidth(getResources(), 1280);
        }
        Bundle bundle = getIntent().getExtras();
        initData(bundle);
        setBaseView(bindLayout());
        ButterKnife.bind(this);
        initView();
        doBusiness();
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        ToastUtils.cancel();
        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
        super.onDestroy();
    }

    @SuppressLint("ResourceType")
    protected void setBaseView(@LayoutRes int layoutId) {
        if (layoutId > 0) {
            setContentView(layoutId);

            BarUtils.setStatusBarAlpha(this, 0);
            BarUtils.setStatusBarLightMode(this, true);

            mToolbar = findViewById(R.id.toolbar);
            if (mToolbar != null) {
                mViewBgTopBar = findViewById(R.id.view_bg_top_bar);
                mTvToolbarTitle = findViewById(R.id.tv_toolbar_title);
                BarUtils.addMarginTopEqualStatusBarHeight(mToolbar);
                setSupportActionBar(mToolbar);
                getToolbar().setDisplayShowTitleEnabled(false);
                if (!(ActivityUtils.getTopActivity() instanceof HomeActivity)) {
                    getToolbar().setDisplayHomeAsUpEnabled(true);
                }
            }

            initSlideBack();
        }
    }

    protected ActionBar getToolbar() {
        return getSupportActionBar();
    }

    private void initSlideBack() {
        if (!(ActivityUtils.getTopActivity() instanceof SplashActivity)
                && !(ActivityUtils.getTopActivity() instanceof LoginActivity)
                && !(ActivityUtils.getTopActivity() instanceof HomeActivity)) {
            SlidrConfig config = new SlidrConfig.Builder()
                    .edge(true)
                    .build();
            Slidr.attach(this, config);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 判断是否快速点击
     *
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public boolean isFastClick() {
        long now = System.currentTimeMillis();
        if (now - lastClick >= 500) {
            lastClick = now;
            return false;
        }
        return true;
    }

    public void setTitleText(CharSequence text) {
        mTvToolbarTitle.setText(text);
    }

    public void setTitleText(@StringRes int resid) {
        mTvToolbarTitle.setText(resid);
    }

    public void sendMessage(int what, Object obj) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        EventBus.getDefault().post(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Message msg) {
    }
}
