package com.witfrog.gtse.http;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.config.HttpConfig.ErrorCode;
import com.witfrog.gtse.model.HttpResult;

import rx.Subscriber;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : HttpCallback
 * </pre>
 */
public abstract class HttpCallback<T> extends Subscriber<HttpResult<T>> {

    private Context       mContext;
    private BasePresenter mPresenter;
    private boolean       mShowLoading = true;

    protected HttpCallback(BasePresenter presenter) {
        mContext = Utils.getApp();
        mPresenter = presenter;
    }

    protected HttpCallback(BasePresenter presenter, boolean showLoading) {
        mContext = Utils.getApp();
        mPresenter = presenter;
        mShowLoading = showLoading;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!NetworkUtils.isConnected()) {
            if (!isUnsubscribed()) {
                unsubscribe();
            }
            onErrors(new ExceptionHandler.ResponseThrowable(null, ErrorCode.NETWORK_ERROR, mContext.getString(R.string.http_network_error)));
        } else {
            if (mShowLoading) {
                mPresenter.showLoading();
            }
        }
    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onNext(HttpResult<T> httpResult) {
        try {
            int status = httpResult.getCode();
            if (status == ErrorCode.SUCCESS) {
                if (mShowLoading) {
                    mPresenter.dismissLoading();
                }
                onResponse(httpResult.getResult());
            } else {
                String message = httpResult.getMessage();
                if (!StringUtils.isEmpty(message)) {
                    ToastUtils.showShort(message);
                }
                onErrors(new ExceptionHandler.ResponseThrowable(null, httpResult.getCode(), httpResult.getMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            onErrors(new ExceptionHandler.ResponseThrowable(e, ErrorCode.PARSE_ERROR, mContext.getString(R.string.http_parse_error)));
        }
    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        onErrors(new ExceptionHandler(mContext).handleException(e));
    }

    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
        if (mShowLoading) {
            mPresenter.dismissLoading();
        }
        if (!TextUtils.isEmpty(throwable.message)) {
            ToastUtils.showShort(throwable.message);
        }
    }

    public abstract void onResponse(T response);

}
