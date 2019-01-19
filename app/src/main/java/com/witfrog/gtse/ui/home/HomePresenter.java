package com.witfrog.gtse.ui.home;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.ExceptionHandler;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.Product;
import com.witfrog.gtse.model.Information;
import com.witfrog.gtse.model.InformationData;
import com.witfrog.gtse.model.MyIntegral;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.WalletData;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomePresenter extends BasePresenter<HomeView> {

    HomePresenter(HomeView view) {
        super(view);
    }

    public void getMallData(int page) {
        mCompositeSubscription.add(mHttpManager.getMallData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<Product>>(this) {
                    @Override
                    public void onResponse(PageData<Product> pageData) {
                        mView.getMallData(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getMallData(null);
                    }
                }));
    }

    public void getInformationData() {
        mCompositeSubscription.add(mHttpManager.getInformationData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<InformationData>(this, false) {
                    @Override
                    public void onResponse(InformationData informationData) {
                        mView.getInformationData(informationData);
                    }
                }));
    }

    public void getInformationList(String keyword, int page) {
        mCompositeSubscription.add(mHttpManager.getInformationList(keyword, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<Information>>(this) {
                    @Override
                    public void onResponse(PageData<Information> pageData) {
                        mView.getInformationList(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getInformationList(null);
                    }
                }));
    }

    public void getWalletData(String id) {
        mCompositeSubscription.add(mHttpManager.getWalletData(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<WalletData>(this) {
                    @Override
                    public void onResponse(WalletData walletData) {
                        mView.getWalletData(walletData);
                    }
                }));
    }

    public void getMyIntegral(String id) {
        mCompositeSubscription.add(mHttpManager.getMyIntegral(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<MyIntegral>(this) {
                    @Override
                    public void onResponse(MyIntegral myIntegral) {
                        mView.getMyIntegral(myIntegral);
                    }
                }));
    }
}
