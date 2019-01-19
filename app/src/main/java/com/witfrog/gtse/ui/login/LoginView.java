package com.witfrog.gtse.ui.login;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.User;

public interface LoginView extends IView {
    void onGetCode();

    void login(User user);
}
