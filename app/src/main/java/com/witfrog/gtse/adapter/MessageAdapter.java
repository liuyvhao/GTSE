package com.witfrog.gtse.adapter;

import android.widget.TextView;

import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.Message;

import java.util.List;

public class MessageAdapter extends SingleAdapter<Message> {

    public MessageAdapter(List<Message> list) {
        super(list, R.layout.item_message);
    }

    @Override
    protected void bind(BaseViewHolder holder, Message data) {
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);

        tvTitle.setText("title");
        tvContent.setText("content");
    }
}
