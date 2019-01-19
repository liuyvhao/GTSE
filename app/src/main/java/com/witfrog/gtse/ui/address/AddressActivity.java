package com.witfrog.gtse.ui.address;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.witfrog.gtse.R;
import com.witfrog.gtse.adapter.AddressAdapter;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.model.Address;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.util.SPManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class AddressActivity extends BaseActivity<AddressPresenter> implements AddressView {

    @BindView(R.id.tv_no_data)
    TextView           tvNoData;
    @BindView(R.id.recycler_view)
    RecyclerView       recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.et_search)
    EditText           etSearch;
    private AddressAdapter mAdapter;
    private List<Address>  mList;
    private int            mPage     = 1;
    private int            mMorePage = 1;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_address;
    }

    @Override
    public void initView() {
        mPresenter = new AddressPresenter(this);
        setTitleText(R.string.address_book);
        refreshLayout.setOnRefreshListener(refreshlayout -> {
            mPage = 1;
            mMorePage = 1;
            getData("");
        });
        refreshLayout.setOnLoadMoreListener(refreshLayout -> {
            if (mMorePage == 1) {
                mPage += 1;
                getData("");
            } else {
                refreshLayout.finishLoadMore();
                ToastUtils.showShort(R.string.no_more_data);
            }
        });

        mList = new ArrayList<>();
        mAdapter = new AddressAdapter(mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider_gray1)));
        recyclerView.addItemDecoration(divider);
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            switch (actionId) {
                case EditorInfo.IME_ACTION_SEARCH:
                    mPage = 1;
                    mMorePage = 1;
                    getData(etSearch.getText().toString());
                    break;
                default:
                    break;
            }
            return false;
        });
    }

    @Override
    public void doBusiness() {
//        getData("");
        for (int i = 0; i < 10; i++) {
            mList.add(new Address());
        }
        mAdapter.update(mList);
    }

    @Override
    public void getAddressList(PageData<Address> pageData) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (pageData != null) {
            mPage = pageData.getPage();
            mMorePage = pageData.getMorePage();
            setData(pageData.getList());
        }
    }

    private void getData(String keyword) {
        mPresenter.getAddressList(SPManager.getId(), keyword, mPage);
    }

    private void setData(List<Address> list) {
        if (mPage == 1) {
            mList = list;
        } else {
            mList.addAll(list);
        }
        mAdapter.update(mList);
        if (mAdapter.getDataSize() == 0) {
            tvNoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
