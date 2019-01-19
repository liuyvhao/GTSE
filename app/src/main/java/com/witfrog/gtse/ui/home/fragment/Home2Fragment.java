package com.witfrog.gtse.ui.home.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.BaseFragment;
import com.witfrog.gtse.config.Constant;
import com.witfrog.gtse.model.WalletData;

import butterknife.BindView;
import butterknife.OnClick;

public class Home2Fragment extends BaseFragment {

    @BindView(R.id.tv_exchange_rmb)
    TextView  tvExchangeRmb;
    @BindView(R.id.tv_exchange_dollar)
    TextView  tvExchangeDollar;
    @BindView(R.id.tv_yesterday_rose_value)
    TextView  tvYesterdayRoseValue;
    @BindView(R.id.tv_today_highest_value)
    TextView  tvTodayHighestValue;
    @BindView(R.id.tv_today_lowest_value)
    TextView  tvTodayLowestValue;
    @BindView(R.id.tv_market_value_value)
    TextView  tvMarketValueValue;
    @BindView(R.id.tv_quantity_value)
    TextView  tvQuantityValue;
    @BindView(R.id.tv_amount_value)
    TextView  tvAmountValue;
    @BindView(R.id.view_bg_chart_top)
    View      viewBgChartTop;
    @BindView(R.id.line_chart)
    LineChart lineChart;

    public static Home2Fragment newInstance() {
        Bundle args = new Bundle();
        Home2Fragment fragment = new Home2Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home2;
    }

    @Override
    public void initView() {
        tvExchangeRmb.setText(String.format(getString(R.string.exchange_rmb), "0.00"));
        tvExchangeDollar.setText(String.format(getString(R.string.exchange_dollar), "0.00"));
        tvYesterdayRoseValue.setText(String.format(getString(R.string.plus), "0.00%"));
        tvTodayHighestValue.setText("1");
        tvTodayLowestValue.setText("2");
        tvMarketValueValue.setText(String.format(getString(R.string.rmb), "1.00"));
        tvQuantityValue.setText(String.format(getString(R.string.taf), "0"));
        tvAmountValue.setText(String.format(getString(R.string.rmb), "123.00"));
    }

    @Override
    public void doLazyBusiness() {
    }

    @OnClick({R.id.btn_transaction, R.id.btn_wallet, R.id.tv_day, R.id.tv_week, R.id.tv_month, R.id.tv_all, R.id.btn_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_transaction: {
            }
            break;
            case R.id.btn_wallet: {
            }
            break;
            case R.id.tv_day: {
            }
            break;
            case R.id.tv_week: {
            }
            break;
            case R.id.tv_month: {
            }
            break;
            case R.id.tv_all: {
            }
            break;
            case R.id.btn_more: {
            }
            break;
            default:
                break;
        }
    }

    @Override
    public void onMessageEvent(Message msg) {
        super.onMessageEvent(msg);
        switch (msg.what) {
            case Constant.MSG_GET_WALLET_DATA: {
                WalletData walletData = (WalletData) msg.obj;
            }
            break;
            default:
                break;
        }
    }
}
