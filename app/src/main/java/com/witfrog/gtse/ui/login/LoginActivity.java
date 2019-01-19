package com.witfrog.gtse.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Message;
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
import com.witfrog.gtse.model.User;
import com.witfrog.gtse.ui.forgetpassword.ForgetPasswordActivity;
import com.witfrog.gtse.ui.home.HomeActivity;
import com.witfrog.gtse.ui.register.RegisterActivity;
import com.witfrog.gtse.ui.web.WebActivity;
import com.witfrog.gtse.util.SPManager;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {

    @BindView(R.id.tv_register)
    TextView  tvRegister;
    @BindView(R.id.et_phone_number)
    EditText  etPhoneNumber;
    @BindView(R.id.et_password)
    EditText  etPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.et_verification_code)
    EditText  etVerificationCode;
    @BindView(R.id.btn_send)
    Button    btnSend;
    @BindView(R.id.tv_forget_password)
    TextView  tvForgetPassword;
    @BindView(R.id.view_divider)
    View      viewDivider;
    @BindView(R.id.tv_login_mode)
    TextView  tvLoginMode;
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
        Intent intent = new Intent(context, LoginActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void initView() {
        mPresenter = new LoginPresenter(this);
        BarUtils.addMarginTopEqualStatusBarHeight(tvRegister);
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onMessageEvent(Message msg) {
        super.onMessageEvent(msg);
        switch (msg.what) {
            case Constant.MSG_REGISTER_SUCCESS: {
                mPresenter.mProgressDialogUtils.showProgressSuccess(getString(R.string.register_success));
                updateView((String) msg.obj);
            }
            break;
            case Constant.MSG_MODIFY_PASSWORD_SUCCESS: {
                mPresenter.mProgressDialogUtils.showProgressSuccess(getString(R.string.modify_password_success));
                updateView((String) msg.obj);
            }
            break;
            default:
                break;
        }
    }

    private void updateView(String phoneNumber) {
        etPhoneNumber.setText(phoneNumber);
        etPhoneNumber.requestFocus();
    }

    @Override
    public void onGetCode() {
        mPresenter.mProgressDialogUtils.showProgressSuccess(getString(R.string.send_success));
    }

    @Override
    public void login(User user) {
        SPManager.setId(user.getId());
        SPManager.setPhone(user.getPhone());
        SPManager.setAvatar(user.getAvatar());
        HomeActivity.start(this);
    }

    @OnClick({R.id.tv_register, R.id.iv_eye, R.id.btn_send, R.id.tv_login, R.id.tv_forget_password, R.id.tv_login_mode, R.id.tv_terms, R.id.layout_l})
    public void onClick(View view) {
        KeyboardUtils.hideSoftInput(this);
        switch (view.getId()) {
            case R.id.tv_register: {
                RegisterActivity.start(this);
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
            case R.id.tv_login: {
//                String phone = etPhoneNumber.getText().toString();
//                String password = etPassword.getText().toString();
//                String code = etVerificationCode.getText().toString();
//                if (StringUtils.equals(getString(R.string.verification_code_login), tvLoginMode.getText())) {
//                    if (StringUtils.isSpace(phone)) {
//                        ToastUtils.showShort(R.string.input_phone_number);
//                    } else if (!RegexUtils.isMobileExact(phone)) {
//                        ToastUtils.showShort(R.string.phone_number_format_error);
//                    } else if (StringUtils.isSpace(password) || password.length() < 6) {
//                        ToastUtils.showShort(R.string.input_password1);
//                    } else {
//                        mPresenter.login(phone, password, "");
//                    }
//                } else {
//                    if (StringUtils.isSpace(phone)) {
//                        ToastUtils.showShort(R.string.input_phone_number);
//                    } else if (!RegexUtils.isMobileExact(phone)) {
//                        ToastUtils.showShort(R.string.phone_number_format_error);
//                    } else if (StringUtils.isSpace(code)) {
//                        ToastUtils.showShort(R.string.input_verification_code);
//                    } else if (code.length() < 6) {
//                        ToastUtils.showShort(R.string.verification_code_format_error);
//                    } else {
//                        mPresenter.login(phone, "", code);
//                    }
//                }
                HomeActivity.start(this);
            }
            break;
            case R.id.tv_forget_password: {
                ForgetPasswordActivity.start(this);
            }
            break;
            case R.id.tv_login_mode: {
                if (StringUtils.equals(getString(R.string.verification_code_login), tvLoginMode.getText())) {
                    etPassword.setVisibility(View.GONE);
                    ivEye.setVisibility(View.GONE);
                    etVerificationCode.setVisibility(View.VISIBLE);
                    btnSend.setVisibility(View.VISIBLE);
                    tvForgetPassword.setVisibility(View.GONE);
                    viewDivider.setVisibility(View.GONE);
                    tvLoginMode.setText(R.string.phone_number_login);
                } else {
                    etPassword.setVisibility(View.VISIBLE);
                    ivEye.setVisibility(View.VISIBLE);
                    etVerificationCode.setVisibility(View.GONE);
                    btnSend.setVisibility(View.GONE);
                    tvForgetPassword.setVisibility(View.VISIBLE);
                    viewDivider.setVisibility(View.VISIBLE);
                    tvLoginMode.setText(R.string.verification_code_login);
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
