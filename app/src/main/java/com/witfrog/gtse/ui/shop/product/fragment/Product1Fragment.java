package com.witfrog.gtse.ui.shop.product.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseFragment;

public class Product1Fragment extends BaseFragment {

    public static Product1Fragment newInstance() {
        Bundle args = new Bundle();
        Product1Fragment fragment = new Product1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLazyBusiness() {

    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_product1;
    }

    @Override
    public void initView() {

    }
}
