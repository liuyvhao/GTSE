package com.witfrog.gtse.ui.forgetpassword;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.HttpCallback;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ForgetPasswordPresenter extends BasePresenter<ForgetPasswordView> {

    ForgetPasswordPresenter(ForgetPasswordView view) {
        super(view);
    }

    public void getCode(String username) {
        mCompositeSubscription.add(mHttpManager.getCode(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        dismissLoading();
                        mView.onGetCode();
                    }
                }));
    }

    public void modifyPassword(String username, String code, String password) {
        mCompositeSubscription.add(mHttpManager.retrievePwd(username, code, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.retrievePwd();
                    }
                }));
    }
}
