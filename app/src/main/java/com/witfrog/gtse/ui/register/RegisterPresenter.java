package com.witfrog.gtse.ui.register;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.HttpCallback;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RegisterPresenter extends BasePresenter<RegisterView> {

    RegisterPresenter(RegisterView view) {
        super(view);
    }

    public void getCode(String phone) {
        mCompositeSubscription.add(mHttpManager.getCode(phone)
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

    public void register(String phone, String code, String password, String recode) {
        mCompositeSubscription.add(mHttpManager.register(phone, code, password, recode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.register();
                    }
                }));
    }
}
