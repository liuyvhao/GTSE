package com.witfrog.gtse.ui.shop.tourism;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.androidkun.xtablayout.XTabLayout;
import com.witfrog.gtse.R;
import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.adapter.TablayoutAdapter;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.ui.shop.product.ProductActivity;
import com.witfrog.gtse.ui.shop.tourism.fragment.Tourism0Fragment;
import com.witfrog.gtse.ui.shop.tourism.fragment.Tourism1Fragment;
import com.witfrog.gtse.ui.shop.tourism.fragment.Tourism2Fragment;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class TourismActivity extends BaseActivity<TourismPresenter> implements TourismView, XTabLayout.OnTabSelectedListener {
    private ArrayList<String> titles = new ArrayList<String>() {{
        add("行程介绍");
        add("内部说明");
        add("预定须知");
    }};
    private Fragment[] mFragments = new Fragment[3];
    @BindView(R.id.tabLayout)
    XTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static void start(Context context) {
        Intent intent = new Intent(context, TourismActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_tourism;
    }

    @Override
    public void initView() {
        mPresenter = new TourismPresenter(this);
        setTitleText(R.string.tab_text0);
        mFragments[0] = Tourism0Fragment.newInstance();
        mFragments[1] = Tourism1Fragment.newInstance();
        mFragments[2] = Tourism2Fragment.newInstance();

        for (String item : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(item));
        }
        tabLayout.setOnTabSelectedListener(this);
        viewPager.setAdapter(new TablayoutAdapter(getSupportFragmentManager(), titles, mFragments));
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onTabSelected(XTabLayout.Tab tab) {
        if (tab != null) viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(XTabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(XTabLayout.Tab tab) {

    }

    @OnClick(R.id.btn_convert)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_convert:{
                ProductActivity.start(this);
            }
            default:
                break;
        }
    }
}
