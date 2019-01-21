package com.witfrog.gtse.ui.shop.tourism.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseFragment;

public class Tourism2Fragment extends BaseFragment {

    public static Tourism2Fragment newInstance() {
        Bundle args = new Bundle();
        Tourism2Fragment fragment = new Tourism2Fragment();
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
        return R.layout.fragment_tourism2;
    }

    @Override
    public void initView() {

    }
}
