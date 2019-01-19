package com.witfrog.gtse.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.Information;
import com.witfrog.gtse.util.ImageLoaderHelper;

import java.util.List;

public class InformationAdapter extends SingleAdapter<Information> {

    private Context mContext;

    public InformationAdapter(Context context, List<Information> list) {
        super(list, R.layout.item_information);
        mContext = context;
    }

    @Override
    protected void bind(BaseViewHolder holder, Information data) {
        ImageView ivInformation = holder.getView(R.id.iv_information);
        TextView tvTitle = holder.getView(R.id.tv_title);
        TextView tvContent = holder.getView(R.id.tv_content);

        ImageLoaderHelper.loadImage(mContext, "", ivInformation);
        tvTitle.setText("全球首发");
        tvContent.setText("123456789");
    }
}
