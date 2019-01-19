package com.witfrog.gtse.ui.usualaddress;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.ExceptionHandler;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.UsualAddress;
import com.witfrog.gtse.model.PageData;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UsualAddressPresenter extends BasePresenter<UsualAddressView> {

    UsualAddressPresenter(UsualAddressView view) {
        super(view);
    }

    public void getUsualAddressList(String id, int page) {
        mCompositeSubscription.add(mHttpManager.getUsualAddressList(id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<UsualAddress>>(this) {
                    @Override
                    public void onResponse(PageData<UsualAddress> pageData) {
                        mView.getUsualAddressList(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getUsualAddressList(null);
                    }
                }));
    }
}
