package com.witfrog.gtse.ui.forgetpassword;

import com.witfrog.gtse.base.IView;

public interface ForgetPasswordView extends IView {
    void onGetCode();

    void retrievePwd();
}
