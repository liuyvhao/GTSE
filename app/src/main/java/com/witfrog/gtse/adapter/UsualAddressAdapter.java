package com.witfrog.gtse.adapter;

import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.UsualAddress;
import com.witfrog.gtse.ui.usualaddressdetail.UsualAddressDetailActivity;

import java.util.List;

public class UsualAddressAdapter extends SingleAdapter<UsualAddress> {

    public UsualAddressAdapter(List<UsualAddress> list) {
        super(list, R.layout.item_usual_address);
    }

    @Override
    protected void bind(BaseViewHolder holder, UsualAddress data) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvAddress = holder.getView(R.id.tv_address);
        TextView tvPhone = holder.getView(R.id.tv_phone);

        tvName.setText("name");
        tvAddress.setText("address");
        tvPhone.setText("phone");

        holder.getView(R.id.tv_modify).setOnClickListener(v -> UsualAddressDetailActivity.start(ActivityUtils.getTopActivity(), 1, data));
    }
}
