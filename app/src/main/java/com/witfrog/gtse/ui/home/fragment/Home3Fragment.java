package com.witfrog.gtse.ui.home.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ObjectUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseFragment;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.MyIntegral;
import com.witfrog.gtse.ui.about.AboutActivity;
import com.witfrog.gtse.ui.address.AddressActivity;
import com.witfrog.gtse.ui.integral.IntegralActivity;
import com.witfrog.gtse.ui.invite.InviteActivity;
import com.witfrog.gtse.ui.message.MessageActivity;
import com.witfrog.gtse.ui.order.OrderActivity;
import com.witfrog.gtse.ui.security.SecurityActivity;
import com.witfrog.gtse.ui.setting.SettingActivity;
import com.witfrog.gtse.util.FormatUtil;
import com.witfrog.gtse.util.ImageLoaderHelper;
import com.witfrog.gtse.util.SPManager;

import butterknife.BindView;
import butterknife.OnClick;

public class Home3Fragment extends BaseFragment {

    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_phone)
    TextView  tvPhone;
    @BindView(R.id.tv_integral)
    TextView  tvIntegral;
    private MyIntegral mMyIntegral;

    public static Home3Fragment newInstance() {
        Bundle args = new Bundle();
        Home3Fragment fragment = new Home3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home3;
    }

    @Override
    public void initView() {
        ImageLoaderHelper.loadCirclImage(mActivity, SPManager.getAvatar(), ivAvatar);
//        tvPhone.setText(FormatUtil.invisiblePhone(SPManager.getPhone()));
        tvPhone.setText(FormatUtil.invisiblePhone("18888888888"));
        tvIntegral.setText(String.format(getString(R.string.integral), "0"));
    }

    @Override
    public void doLazyBusiness() {
    }

    @OnClick({R.id.iv_message, R.id.iv_setting, R.id.tv_my_integral, R.id.tv_order_management, R.id.tv_address_book, R.id.tv_security_center, R.id.tv_about_us, R.id.tv_immediately_invited})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_message: {
                MessageActivity.start(mActivity);
            }
            break;
            case R.id.iv_setting: {
                SettingActivity.start(mActivity);
            }
            break;
            case R.id.tv_my_integral: {
                IntegralActivity.start(mActivity);
            }
            break;
            case R.id.tv_order_management: {
                OrderActivity.start(mActivity);
            }
            break;
            case R.id.tv_address_book: {
                AddressActivity.start(mActivity);
            }
            break;
            case R.id.tv_security_center: {
                SecurityActivity.start(mActivity);
            }
            break;
            case R.id.tv_about_us: {
                AboutActivity.start(mActivity);
            }
            break;
            case R.id.tv_immediately_invited: {
                InviteActivity.start(mActivity);
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onMessageEvent(Message msg) {
        super.onMessageEvent(msg);
        switch (msg.what) {
            case Constant.MSG_GET_MY_INTEGRAL: {
                if (!ObjectUtils.isEmpty(msg.obj)) {
                    mMyIntegral = (MyIntegral) msg.obj;
                }
            }
            break;
            default:
                break;
        }
    }
}
