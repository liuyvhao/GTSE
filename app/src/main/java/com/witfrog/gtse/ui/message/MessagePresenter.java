package com.witfrog.gtse.ui.message;

import com.witfrog.gtse.base.BasePresenter;
import com.witfrog.gtse.http.ExceptionHandler;
import com.witfrog.gtse.http.HttpCallback;
import com.witfrog.gtse.model.Message;
import com.witfrog.gtse.model.PageData;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MessagePresenter extends BasePresenter<MessageView> {

    MessagePresenter(MessageView view) {
        super(view);
    }

    public void getMessageList(String id, int page) {
        mCompositeSubscription.add(mHttpManager.getMessageList(id, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpCallback<PageData<Message>>(this) {
                    @Override
                    public void onResponse(PageData<Message> pageData) {
                        mView.getMessageList(pageData);
                    }

                    @Override
                    public void onErrors(ExceptionHandler.ResponseThrowable throwable) {
                        super.onErrors(throwable);
                        mView.getMessageList(null);
                    }
                }));
    }
}
