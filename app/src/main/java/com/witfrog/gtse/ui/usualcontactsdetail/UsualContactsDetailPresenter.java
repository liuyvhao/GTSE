package com.witfrog.gtse.ui.usualcontactsdetail;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.HttpCallback;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsualContactsDetailPresenter extends BasePresenter<UsualContactsDetailView> {

    UsualContactsDetailPresenter(UsualContactsDetailView view) {
        super(view);
    }

    public void addUsualContacts(String id, String contacts, String certificateType, String idNumber, String phone, String email, String crowd) {
        mCompositeSubscription.add(mHttpManager.addUsualContacts(id, contacts, certificateType, idNumber, phone, email, crowd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.addUsualContacts();
                    }
                }));
    }

    public void modifyUsualContacts(String id, String contacts, String certificateType, String idNumber, String phone, String email, String crowd) {
        mCompositeSubscription.add(mHttpManager.modifyUsualContacts(id, contacts, certificateType, idNumber, phone, email, crowd)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<Object>(this) {
                    @Override
                    public void onResponse(Object o) {
                        mView.modifyUsualContacts();
                    }
                }));
    }
}
