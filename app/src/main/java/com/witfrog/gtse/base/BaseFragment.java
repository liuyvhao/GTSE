package com.witfrog.gtse.base;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements IActivity {

    private static final String STATE_SAVE_IS_HIDDEN = "STATE_SAVE_IS_HIDDEN";

    protected BaseActivity mActivity;
    protected View         mContentView;
    private   Unbinder     mUnbinder;
    private   boolean      isDataLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            boolean isSupportHidden = savedInstanceState.getBoolean(STATE_SAVE_IS_HIDDEN);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (isSupportHidden) {
                ft.hide(this);
            } else {
                ft.show(this);
            }
            ft.commitAllowingStateLoss();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(bindLayout(), null);
        mUnbinder = ButterKnife.bind(this, mContentView);
        return mContentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        initData(bundle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        EventBus.getDefault().register(this);
        initView();
        doBusiness();
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        ToastUtils.cancel();
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
        if (mContentView != null) {
            ((ViewGroup) mContentView.getParent()).removeView(mContentView);
        }
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(STATE_SAVE_IS_HIDDEN, isHidden());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && mContentView != null && !isDataLoaded) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }

    @Override
    public void doBusiness() {
        if (getUserVisibleHint()) {
            doLazyBusiness();
            isDataLoaded = true;
        }
    }

    public abstract void doLazyBusiness();

    public boolean isFastClick() {
        return mActivity.isFastClick();
    }


    public void sendMessage(int what, Object obj) {
        Message msg = new Message();
        msg.what = what;
        msg.obj = obj;
        EventBus.getDefault().post(msg);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Message msg) {
    }
}
