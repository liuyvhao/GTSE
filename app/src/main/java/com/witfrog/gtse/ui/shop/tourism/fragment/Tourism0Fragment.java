package com.witfrog.gtse.ui.shop.tourism.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseFragment;

public class Tourism0Fragment extends BaseFragment {

    public static Tourism0Fragment newInstance() {
        Bundle args = new Bundle();
        Tourism0Fragment fragment = new Tourism0Fragment();
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
        return R.layout.fragment_tourism0;
    }

    @Override
    public void initView() {

    }
}
