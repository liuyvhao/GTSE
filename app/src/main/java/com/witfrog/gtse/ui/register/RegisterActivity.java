package com.witfrog.gtse.ui.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.ui.web.WebActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    @BindView(R.id.tv_login)
    TextView  tvLogin;
    @BindView(R.id.et_phone_number)
    EditText  etPhoneNumber;
    @BindView(R.id.et_verification_code)
    EditText  etVerificationCode;
    @BindView(R.id.btn_send)
    Button    btnSend;
    @BindView(R.id.et_password)
    EditText  etPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_recommend_code)
    EditText  etRecommendCode;
    private CountDownTimer mCountDownTimer;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        mPresenter = new RegisterPresenter(this);
        BarUtils.addMarginTopEqualStatusBarHeight(tvLogin);
        ivEye.setTag(0);
        mCountDownTimer = new CountDownTimer(Constant.SMS_WAIT_TIME * 1000, 1000) {
            @SuppressLint("StringFormatMatches")
            @Override
            public void onTick(long millisLeft) {
                btnSend.setFocusable(false);
                btnSend.setEnabled(false);
                btnSend.setText(String.format(getString(R.string.resend), millisLeft / 1000));
            }

            @Override
            public void onFinish() {
                btnSend.setFocusable(true);
                btnSend.setEnabled(true);
                btnSend.setText(getString(R.string.get_verification_code));
            }
        };
    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void onGetCode() {
        mPresenter.mProgressDialogUtils.showProgressSuccess(getString(R.string.send_success));
    }

    @Override
    public void register() {
        sendMessage(Constant.MSG_REGISTER_SUCCESS, etPhoneNumber.getText().toString());
        finish();
    }

    @OnClick({R.id.tv_login, R.id.btn_send, R.id.iv_eye, R.id.tv_register, R.id.tv_terms, R.id.layout_r})
    public void onClick(View view) {
        KeyboardUtils.hideSoftInput(this);
        switch (view.getId()) {
            case R.id.tv_login: {
                finish();
            }
            break;
            case R.id.btn_send: {
                String phone = etPhoneNumber.getText().toString();
                if (StringUtils.isSpace(phone)) {
                    ToastUtils.showShort(R.string.input_phone_number);
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShort(R.string.phone_number_format_error);
                } else {
                    mCountDownTimer.start();
                    mPresenter.getCode(phone);
                }
            }
            break;
            case R.id.iv_eye: {
                if (0 == (int) ivEye.getTag()) {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPassword.requestFocus();
                    ivEye.setTag(1);
                } else {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPassword.requestFocus();
                    ivEye.setTag(0);
                }
            }
            break;
            case R.id.tv_register: {
                String phone = etPhoneNumber.getText().toString();
                String code = etVerificationCode.getText().toString();
                String password = etPassword.getText().toString();
                String recode = etRecommendCode.getText().toString();
                if (StringUtils.isSpace(phone)) {
                    ToastUtils.showShort(R.string.input_phone_number);
                } else if (!RegexUtils.isMobileExact(phone)) {
                    ToastUtils.showShort(R.string.phone_number_format_error);
                } else if (StringUtils.isSpace(code)) {
                    ToastUtils.showShort(R.string.input_verification_code);
                } else if (code.length() < 6) {
                    ToastUtils.showShort(R.string.verification_code_format_error);
                } else if (StringUtils.isSpace(password) || password.length() < 6) {
                    ToastUtils.showShort(R.string.input_password1);
                } else {
                    mPresenter.register(phone, code, password, recode);
                }
            }
            break;
            case R.id.tv_terms: {
                WebActivity.start(this, "");
            }
            break;
            default:
                break;
        }
    }
}
