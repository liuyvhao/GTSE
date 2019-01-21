package com.witfrog.gtse.ui.shop.product;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.androidkun.xtablayout.XTabLayout;
import com.witfrog.gtse.R;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.adapter.TablayoutAdapter;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.ui.shop.product.fragment.Product0Fragment;
import com.witfrog.gtse.ui.shop.product.fragment.Product1Fragment;

import java.util.ArrayList;

import butterknife.BindView;

public class ProductActivity extends BaseActivity<ProductPresenter> implements ProductView, XTabLayout.OnTabSelectedListener {
    private ArrayList<String> titles = new ArrayList<String>() {{
        add("费用说明");
        add("预定须知");
    }};
    private Fragment[] mFragments = new Fragment[2];
    @BindView(R.id.tabLayout)
    XTabLayout tabLayout;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    public static void start(Context context) {
        Intent intent = new Intent(context, ProductActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_product;
    }

    @Override
    public void initView() {
        mPresenter = new ProductPresenter(this);
        setTitleText(R.string.select_product);
        mFragments[0] = Product0Fragment.newInstance();
        mFragments[1] = Product1Fragment.newInstance();

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
}
