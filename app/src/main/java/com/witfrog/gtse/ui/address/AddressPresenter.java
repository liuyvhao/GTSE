package com.witfrog.gtse.ui.address;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.ExceptionHandler;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.Address;
import com.witfrog.gtse.model.PageData;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddressPresenter extends BasePresenter<AddressView> {

    AddressPresenter(AddressView view) {
        super(view);
    }

    public void getAddressList(String id, String keyword, int page) {
        mCompositeSubscription.add(mHttpManager.getAddressList(id, keyword, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<Address>>(this) {
                    @Override
                    public void onResponse(PageData<Address> pageData) {
                        mView.getAddressList(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getAddressList(null);
                    }
                }));
    }
}
