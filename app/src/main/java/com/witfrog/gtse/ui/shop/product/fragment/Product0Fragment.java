package com.witfrog.gtse.ui.shop.product.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseFragment;

public class Product0Fragment extends BaseFragment {

    public static Product0Fragment newInstance() {
        Bundle args = new Bundle();
        Product0Fragment fragment = new Product0Fragment();
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
        return R.layout.fragment_product0;
    }

    @Override
    public void initView() {

    }
}
