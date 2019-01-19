package com.witfrog.gtse.ui.order;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.ExceptionHandler;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.Order;
import com.witfrog.gtse.model.PageData;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OrderPresenter extends BasePresenter<OrderView> {

    OrderPresenter(OrderView view) {
        super(view);
    }

    public void getOrderList(String id, String status, int page) {
        mCompositeSubscription.add(mHttpManager.getOrderList(id, status, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<Order>>(this) {
                    @Override
                    public void onResponse(PageData<Order> pageData) {
                        mView.getOrderList(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getOrderList(null);
                    }
                }));
    }
}
