package com.witfrog.gtse.ui.shop.order;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.ImageView;
import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.util.ImageLoaderHelper;

import butterknife.BindView;

public class ConfirmOrderActivity extends BaseActivity<ConfirmOrderPresenter> implements ConfirmOrderView {

    @BindView(R.id.shop_img)
    ImageView       shop_img;

    public static void start(Context context) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
//        ImageLoaderHelper.loadImage(this,"",shop_img);
    }

    @Override
    public int bindLayout() { return R.layout.activity_confirm_order; }

    @Override
    public void initView() {
        mPresenter=new ConfirmOrderPresenter(this);
        setTitleText(R.string.confirm_order);
    }

    @Override
    public void doBusiness() {

    }
}
