package com.witfrog.gtse.adapter;

import android.widget.TextView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.Utils;
import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.UsualContacts;
import com.witfrog.gtse.ui.usualcontactsdetail.UsualContactsDetailActivity;
import com.witfrog.gtse.util.FormatUtil;

import java.util.List;

public class UsualContactsAdapter extends SingleAdapter<UsualContacts> {

    public UsualContactsAdapter(List<UsualContacts> list) {
        super(list, R.layout.item_usual_contacts);
    }

    @Override
    protected void bind(BaseViewHolder holder, UsualContacts data) {
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPhoneNumber = holder.getView(R.id.tv_phone_number);
        TextView tvIdNumber = holder.getView(R.id.tv_id_number);

        tvName.setText("name");
        tvPhoneNumber.setText(String.format(Utils.getApp().getString(R.string.phone_number), FormatUtil.invisiblePhone("13669245513")));
        tvIdNumber.setText(String.format(Utils.getApp().getString(R.string.id_number), FormatUtil.invisibleIDCard("610115199407036019")));

        holder.getView(R.id.tv_modify).setOnClickListener(v -> {
            UsualContactsDetailActivity.start(ActivityUtils.getTopActivity(), 1, data);
        });
    }
}
