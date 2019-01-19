package com.witfrog.gtse.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.witfrog.gtse.R;
import com.witfrog.gtse.base.rv.BaseViewHolder;
import com.witfrog.gtse.base.rv.OnItemClickListener;
import com.witfrog.gtse.base.rv.SingleAdapter;
import com.witfrog.gtse.model.Product;
import com.witfrog.gtse.util.ImageLoaderHelper;

import java.util.List;

public class MallAdapter extends SingleAdapter<Product> {

    private Context mContext;

    public MallAdapter(Context context, List<Product> list, OnItemClickListener onItemClickListener) {
        super(list, R.layout.item_mall);
        mContext = context;
        setOnItemClickListener(onItemClickListener);
    }

    @Override
    protected void bind(BaseViewHolder holder, Product data) {
        ImageView ivGoods = holder.getView(R.id.iv_goods);
        TextView tvName = holder.getView(R.id.tv_name);
        TextView tvPrice = holder.getView(R.id.tv_price);

        ImageLoaderHelper.loadImage(mContext, "", ivGoods);
        tvName.setText("name");
        tvPrice.setText(String.format(mContext.getString(R.string.integral), "1000"));
    }
}
