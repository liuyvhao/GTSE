package com.witfrog.gtse.ui.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.witfrog.gtse.R;
import com.witfrog.gtse.adapter.InformationAdapter;
import com.witfrog.gtse.base.BaseFragment;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.Information;
import com.witfrog.gtse.model.InformationData;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.ui.home.HomeActivity;
import com.witfrog.gtse.util.ImageLoaderHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class Home1Fragment extends BaseFragment {

    @BindView(R.id.convenient_banner)
    ConvenientBanner   convenientBanner;
    @BindView(R.id.tv_information)
    TextView           tvInformation;
    @BindView(R.id.recycler_view)
    RecyclerView       recyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private InformationAdapter mAdapter;
    private List<Information>  mList;
    private int                mPage     = 1;
    private int                mMorePage = 1;

    @Override
    public void onResume() {
        super.onResume();
        if (convenientBanner != null) {
            convenientBanner.startTurning();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (convenientBanner != null) {
            convenientBanner.stopTurning();
        }
    }

    public static Home1Fragment newInstance() {
        Bundle args = new Bundle();
        Home1Fragment fragment = new Home1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home1;
    }

    @Override
    public void initView() {
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
        mAdapter = new InformationAdapter(mActivity, mList);
        @SuppressLint("InflateParams") View header = LayoutInflater.from(mActivity).inflate(R.layout.header_information, null);
        header.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SizeUtils.dp2px(40)));
        EditText etSearch = header.findViewById(R.id.et_search);
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
        mAdapter.setHeaderView(header);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void doBusiness() {
        super.doBusiness();
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        convenientBanner.setPages(
                new CBViewHolderCreator() {
                    @Override
                    public ImageHolderView createHolder(View itemView) {
                        return new ImageHolderView(itemView);
                    }

                    @Override
                    public int getLayoutId() {
                        return R.layout.item_banner;
                    }
                }, list)
                .setPageIndicator(new int[]{R.drawable.dot_white_solid, R.drawable.dot_golden_solid});
        tvInformation.setText("123456789");
        for (int i = 0; i < 10; i++) {
            mList.add(new Information());
            mAdapter.update(mList);
        }
    }

    @Override
    public void doLazyBusiness() {
    }

    @OnClick(R.id.layout_home1)
    public void onClick() {
        KeyboardUtils.hideSoftInput(mActivity);
    }

    @Override
    public void onMessageEvent(Message msg) {
        super.onMessageEvent(msg);
        switch (msg.what) {
            case Constant.MSG_GET_INFORMATION_DATA: {
                InformationData informationData = (InformationData) msg.obj;
                List<String> list = new ArrayList<>();
                list.add("");
                list.add("");
                list.add("");
                convenientBanner.setPages(
                        new CBViewHolderCreator() {
                            @Override
                            public ImageHolderView createHolder(View itemView) {
                                return new ImageHolderView(itemView);
                            }

                            @Override
                            public int getLayoutId() {
                                return R.layout.item_banner;
                            }
                        }, list)
                        .setPageIndicator(new int[]{R.drawable.dot_white_solid, R.drawable.dot_golden_solid});
                tvInformation.setText("123456789");
            }
            break;
            case Constant.MSG_GET_INFORMATION_LIST: {
                PageData<Information> pageData = (PageData<Information>) msg.obj;
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

    private void getData(String keyword) {
        ((HomeActivity) mActivity).mPresenter.getInformationList(keyword, mPage);
    }

    private void setData(List<Information> list) {
        if (mPage == 1) {
            mList = list;
        } else {
            mList.addAll(list);
        }
        mAdapter.update(mList);
    }

    public class ImageHolderView extends Holder<String> {

        private ImageView mImageView;

        public ImageHolderView(View itemView) {
            super(itemView);
        }

        @Override
        protected void initView(View itemView) {
            mImageView = itemView.findViewById(R.id.image_view);
        }

        @Override
        public void updateUI(String data) {
            ImageLoaderHelper.loadImage(mActivity, data, mImageView);
        }
    }
}
