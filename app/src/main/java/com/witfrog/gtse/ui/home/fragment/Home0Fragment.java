package com.witfrog.gtse.ui.home.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.witfrog.gtse.R;
import com.witfrog.gtse.adapter.MallAdapter;
import com.witfrog.gtse.base.BaseFragment;
import com.witfrog.gtse.base.rv.OnItemClickListener;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.Product;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.ui.home.HomeActivity;
import com.witfrog.gtse.ui.shop.commodity.CommodityActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Home0Fragment extends BaseFragment implements OnItemClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView       recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private MallAdapter   mAdapter;
    private List<Product> mList;
    private int           mPage     = 1;
    private int           mMorePage = 1;

    public static Home0Fragment newInstance() {
        Bundle args = new Bundle();
        Home0Fragment fragment = new Home0Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home0;
    }

    @Override
    public void initView() {
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            mPage = 1;
            mMorePage = 1;
            getData();
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (mMorePage == 1) {
                mPage += 1;
                getData();
            } else {
                refreshLayout.finishLoadMore();
                ToastUtils.showShort(R.string.no_more_data);
            }
        });

        mList = new ArrayList<>();
        mAdapter = new MallAdapter(mActivity, mList,this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(mActivity, 2));
    }

    @Override
    public void onItemClick(View view, int position) {
        CommodityActivity.start(mActivity);
    }

    @Override
    public void doBusiness() {
        super.doBusiness();
        for (int i = 0; i < 10; i++) {
            mList.add(new Product());
            mAdapter.update(mList);
        }
    }

    @Override
    public void doLazyBusiness() {
    }

    @Override
    public void onMessageEvent(Message msg) {
        super.onMessageEvent(msg);
        switch (msg.what) {
            case Constant.MSG_GET_MALL_DATA: {
                PageData<Product> pageData = (PageData<Product>) msg.obj;
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
                if (pageData != null) {
                    mPage = pageData.getPage();
                    mMorePage = pageData.getMorePage();
                    setData(pageData.getList());
                }
            }
            break;
            default:
                break;
        }
    }

    private void getData() {
        ((HomeActivity) mActivity).mPresenter.getMallData(mPage);
    }

    private void setData(List<Product> list) {
        if (mPage == 1) {
            mList = list;
        } else {
            mList.addAll(list);
        }
        mAdapter.update(mList);
    }
}
