package com.witfrog.gtse.ui.login;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.User;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginPresenter extends BasePresenter<LoginView> {

    LoginPresenter(LoginView view) {
        super(view);
    }

    public void getCode(String phone) {
        mCompositeSubscription.add(mHttpManager.getCode(phone)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.onGetCode();
                    }
                }));
    }

    public void login(String phone, String password, String code) {
        mCompositeSubscription.add(mHttpManager.login(phone, password, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<User>(this) {
                    @Override
                    public void onResponse(User user) {
                        mView.login(user);
                    }
                }));
    }
}
