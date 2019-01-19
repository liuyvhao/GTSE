package com.witfrog.gtse.ui.usualcontacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.witfrog.gtse.R;
import com.witfrog.gtse.adapter.UsualContactsAdapter;
import com.witfrog.gtse.base.BaseActivity;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.UsualContacts;
import com.witfrog.gtse.ui.usualcontactsdetail.UsualContactsDetailActivity;
import com.witfrog.gtse.util.SPManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;

public class UsualContactsActivity extends BaseActivity<UsualContactsPresenter> implements UsualContactsView {

    @BindView(R.id.tv_no_data)
    TextView           tvNoData;
    @BindView(R.id.recycler_view)
    RecyclerView       recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private UsualContactsAdapter mAdapter;
    private List<UsualContacts>  mList;
    private int                  mPage     = 1;
    private int                  mMorePage = 1;

    public static void start(Context context) {
        Intent intent = new Intent(context, UsualContactsActivity.class);
        ActivityUtils.startActivity(intent);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_usual_contacts;
    }

    @Override
    public void initView() {
        mPresenter = new UsualContactsPresenter(this);
        setTitleText(R.string.usual_contacts);
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
        mAdapter = new UsualContactsAdapter(mList);
        View header = new View(this);
        mAdapter.setHeaderView(header);
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
            mList.add(new UsualContacts());
        }
        mAdapter.update(mList);
    }

    @Override
    public void getUsualContactsList(PageData<UsualContacts> pageData) {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
        if (pageData != null) {
            mPage = pageData.getPage();
            mMorePage = pageData.getMorePage();
            setData(pageData.getList());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        MenuItem item = menu.findItem(R.id.menu_add);
        item.setVisible(true);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_add: {
                UsualContactsDetailActivity.start(this,0,null);
            }
            break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getData() {
        mPresenter.getUsualContactsList(SPManager.getId(), mPage);
    }

    private void setData(List<UsualContacts> list) {
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
