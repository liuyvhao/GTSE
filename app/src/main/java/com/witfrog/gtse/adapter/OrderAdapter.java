package com.witfrog.gtse.adapter;

import android.widget.TextView;

import com.blankj.utilcode.util.Utils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.Order;

import java.util.List;

public class OrderAdapter extends SingleAdapter<Order> {

    public OrderAdapter(List<Order> list) {
        super(list, R.layout.item_order);
    }

    @Override
    protected void bind(BaseViewHolder holder, Order data) {
        TextView tvOrderNo = holder.getView(R.id.tv_order_no);
        TextView tvDeliveryTime = holder.getView(R.id.tv_delivery_time);
        TextView tvProductName = holder.getView(R.id.tv_product_name);
        TextView tvStatus = holder.getView(R.id.tv_status);

        tvOrderNo.setText(String.format(Utils.getApp().getString(R.string.order_no), ""));
        tvDeliveryTime.setText(String.format(Utils.getApp().getString(R.string.delivery_time), ""));
        tvProductName.setText(String.format(Utils.getApp().getString(R.string.product_name), ""));
        tvStatus.setText("status");
    }
}
