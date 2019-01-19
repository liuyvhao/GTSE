package com.witfrog.gtse.ui.usualaddressdetail;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.HttpCallback;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsualAddressDetailPresenter extends BasePresenter<UsualAddressDetailView> {

    UsualAddressDetailPresenter(UsualAddressDetailView view) {
        super(view);
    }

    public void addUsualAddress(String id, String recipient, String phone, String area, String address, String code) {
        mCompositeSubscription.add(mHttpManager.addUsualAddress(id, recipient, phone, area, address, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.addUsualAddress();
                    }
                }));
    }

    public void modifyUsualAddress(String id, String recipient, String phone, String area, String address, String code) {
        mCompositeSubscription.add(mHttpManager.modifyUsualAddress(id, recipient, phone, area, address, code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.modifyUsualAddress();
                    }
                }));
    }
}
