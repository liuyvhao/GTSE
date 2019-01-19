package com.witfrog.gtse.ui.usualcontacts;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.ExceptionHandler;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.UsualContacts;
import com.witfrog.gtse.model.PageData;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsualContactsPresenter extends BasePresenter<UsualContactsView> {

    UsualContactsPresenter(UsualContactsView view) {
        super(view);
    }

    public void getUsualContactsList(String id, int page) {
        mCompositeSubscription.add(mHttpManager.getUsualContactsList(id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<UsualContacts>>(this) {
                    @Override
                    public void onResponse(PageData<UsualContacts> pageData) {
                        mView.getUsualContactsList(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getUsualContactsList(null);
                    }
                }));
    }
}
