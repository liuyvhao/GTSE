package com.witfrog.gtse.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.witfrog.gtse.R;
import com.witfrog.gtse.adapter.OrderAdapter;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.model.Order;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.util.SPManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class OrderActivity extends BaseActivity<OrderPresenter> implements OrderView {

    @BindView(R.id.tv_no_data)
    TextView           tvNoData;
    @BindView(R.id.recycler_view)
    RecyclerView       recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.rg_order_status)
    RadioGroup         rgOrderStatus;
    private OrderAdapter mAdapter;
    private List<Order>  mList;
    private int          mPage     = 1;
    private int          mMorePage = 1;
    private String       mStatus   = "";

    public static void start(Context context) {
        Intent intent = new Intent(context, OrderActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_order;
    }

    @Override
    public void initView() {
        mPresenter = new OrderPresenter(this);
        setTitleText(R.string.order_management);
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
        mAdapter = new OrderAdapter(mList);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(Objects.requireNonNull(ContextCompat.getDrawable(this, R.drawable.divider_gray8)));
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void doBusiness() {
//        getData();
        for (int i = 0; i < 10; i++) {
            mList.add(new Order());
        }
        mAdapter.update(mList);
    }

    @Override
    public void getOrderList(PageData<Order> pageData) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (pageData != null) {
            mPage = pageData.getPage();
            mMorePage = pageData.getMorePage();
            setData(pageData.getList());
        }
    }

    private void getData() {
        mPresenter.getOrderList(SPManager.getId(), mStatus, mPage);
    }

    private void setData(List<Order> list) {
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

    @OnClick(R.id.tv_order_all)
    public void onClick() {
        if (!isFastClick()) {
            rgOrderStatus.clearCheck();
            mStatus = "";
            mPage = 1;
            mMorePage = 1;
            getData();
        }
    }

    @OnCheckedChanged({R.id.rb_status1, R.id.rb_status2, R.id.rb_status3, R.id.rb_status4})
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            switch (buttonView.getId()) {
                case R.id.rb_status1: {
                    mStatus = "1";
                }
                break;
                case R.id.rb_status2: {
                    mStatus = "2";
                }
                break;
                case R.id.rb_status3: {
                    mStatus = "3";
                }
                break;
                case R.id.rb_status4: {
                    mStatus = "4";
                }
                break;
                default:
                    break;
            }
            mPage = 1;
            mMorePage = 1;
            getData();
        }
    }
}
