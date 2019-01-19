package com.witfrog.gtse.ui.shop.commodity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import com.witfrog.gtse.R;
import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.ui.shop.order.ConfirmOrderActivity;

import butterknife.OnClick;

public class CommodityActivity extends BaseActivity<CommodityPresenter> implements CommodityView {

    public static void start(Context context) {
        Intent intent = new Intent(context, CommodityActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() { return R.layout.activity_commodity; }

    @Override
    public void initView() {
        mPresenter=new CommodityPresenter(this);
        setTitleText(R.string.tab_text0);
    }

    @Override
    public void doBusiness() {

    }

    @OnClick(R.id.btn_convert)
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_convert:{
                ConfirmOrderActivity.start(this);
            }
            default:
                break;
        }
    }
}
