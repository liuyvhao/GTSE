package com.witfrog.gtse.adapter;

import android.widget.TextView;

import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.Address;
import com.witfrog.gtse.util.FormatUtil;

import java.util.List;

public class AddressAdapter extends SingleAdapter<Address> {

    public AddressAdapter(List<Address> list) {
        super(list, R.layout.item_address);
    }

    @Override
    protected void bind(BaseViewHolder holder, Address data) {
        TextView tvNickname = holder.getView(R.id.tv_nickname);
        TextView tvAddress = holder.getView(R.id.tv_address);

        tvNickname.setText("nickname");
        tvAddress.setText(FormatUtil.invisibleString("0x12345678123456789012345678"));
    }
}
