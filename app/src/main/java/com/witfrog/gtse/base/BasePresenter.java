package com.witfrog.gtse.base;

import com.witfrog.gtse.http.HttpManager;
import com.witfrog.gtse.util.ProgressDialogUtils;

import rx.subscriptions.CompositeSubscription;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : BasePresenter
 * </pre>
 */
public class BasePresenter<V extends IView> implements IPresenter {

    public    ProgressDialogUtils   mProgressDialogUtils;
    protected V                     mView;
    protected HttpManager           mHttpManager;
    protected CompositeSubscription mCompositeSubscription;

    public BasePresenter(V view) {
        mView = view;
        onCreate();
    }

    @Override
    public void onCreate() {
        mHttpManager = new HttpManager();
        mCompositeSubscription = new CompositeSubscription();
        mProgressDialogUtils = new ProgressDialogUtils();
    }

    public void showLoading() {
        mProgressDialogUtils.showProgressDialog();
    }

    public void dismissLoading() {
        mProgressDialogUtils.dismissProgressDialog();
    }

    @Override
    public void onDestroy() {
        if (mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
        if (mProgressDialogUtils != null) {
            mProgressDialogUtils.dismissProgressDialog();
            mProgressDialogUtils = null;
        }
    }

}
