package com.witfrog.gtse.base.rv;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : BaseAdapter
 * </pre>
 */
public abstract class BaseAdapter<M> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int VIEW_TYPE_EMPTY   = 0xfff0;
    private static final int VIEW_TYPE_HEADER  = 0xfff1;
    private static final int VIEW_TYPE_FOOTER  = 0xfff2;
    private static final int VIEW_TYPE_DEFAULT = 0xfff3;

    private final SparseArray<View> mViewArray = new SparseArray<>();

    private List<M>   mData;
    private ViewGroup mParent;

    private LayoutInflater mInflater;

    private OnItemClickListener     mClickListener;
    private OnItemLongClickListener mLongClickListener;

    public List<M> getData() {
        return mData;
    }

    public void setData(@NonNull final List<M> data) {
        mData = data;
    }

    @Override
    public final int getItemViewType(int position) {
        if (getDataSize() == 0 && mViewArray.get(VIEW_TYPE_EMPTY) != null) {
            return VIEW_TYPE_EMPTY;
        } else if (position == 0 && mViewArray.get(VIEW_TYPE_HEADER) != null) {
            return VIEW_TYPE_HEADER;
        } else if (position == getItemCount() - 1 && mViewArray.get(VIEW_TYPE_FOOTER) != null) {
            return VIEW_TYPE_FOOTER;
        } else {
            return VIEW_TYPE_DEFAULT;
        }
    }

    protected abstract int bindLayout(final int viewType);

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mParent == null) {
            mParent = parent;
            mInflater = LayoutInflater.from(parent.getContext());
        }
        View itemView = mViewArray.get(viewType);
        if (itemView == null) {
            itemView = inflateLayout(bindLayout(viewType));
        }
        return new BaseViewHolder(itemView);
    }

    @Override
    public final void onBindViewHolder(BaseViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_EMPTY:
            case VIEW_TYPE_HEADER:
            case VIEW_TYPE_FOOTER:
                break;
            default:
                bindCustomViewHolder(holder, position);
                break;
        }
    }

    private void bindCustomViewHolder(final BaseViewHolder holder, final int position) {
        final int dataPos = position - (mViewArray.get(VIEW_TYPE_HEADER) == null ? 0 : 1);
        holder.itemView.setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.onItemClick(v, dataPos);
            }
        });
        holder.itemView.setOnLongClickListener(v -> mLongClickListener != null && mLongClickListener.onItemLongClick(v, dataPos));
        bind(holder, mData.get(dataPos));
    }

    protected abstract void bind(final BaseViewHolder holder, final M data);

    @Override
    public int getItemCount() {
        return getDataSize() + getExtraViewCount();
    }

    public View getEmptyView() {
        return getView(VIEW_TYPE_EMPTY);
    }

    public void setEmptyView(@NonNull View emptyView) {
        setView(VIEW_TYPE_EMPTY, emptyView);
    }

    public void removeEmptyView() {
        removeView(VIEW_TYPE_EMPTY);
    }

    public View getHeaderView() {
        return getView(VIEW_TYPE_HEADER);
    }

    public void setHeaderView(@NonNull View headerView) {
        setView(VIEW_TYPE_HEADER, headerView);
    }

    public void removeHeaderView() {
        removeView(VIEW_TYPE_HEADER);
    }

    public View getFooterView() {
        return getView(VIEW_TYPE_FOOTER);
    }

    public void setFooterView(@NonNull View footerView) {
        setView(VIEW_TYPE_FOOTER, footerView);
    }

    public void removeFooterView() {
        removeView(VIEW_TYPE_FOOTER);
    }

    private void setView(final int type, @NonNull final View view) {
        mViewArray.put(type, view);
        notifyDataSetChanged();
    }

    private View getView(final int type) {
        return mViewArray.get(type);
    }

    private void removeView(final int type) {
        if (mViewArray.get(type) != null) {
            mViewArray.delete(type);
            notifyDataSetChanged();
        }
    }

    private View inflateLayout(@LayoutRes final int layoutId) {
        return mInflater.inflate(layoutId, mParent, false);
    }

    private int getExtraViewCount() {
        int extraViewCount = 0;
        if (mViewArray.get(VIEW_TYPE_HEADER) != null) {
            extraViewCount++;
        }
        if (mViewArray.get(VIEW_TYPE_FOOTER) != null) {
            extraViewCount++;
        }
        return extraViewCount;
    }

    public void setOnItemClickListener(final OnItemClickListener clickListener) {
        mClickListener = clickListener;
    }

    public void setOnItemLongClickListener(final OnItemLongClickListener longClickListener) {
        mLongClickListener = longClickListener;
    }

    public int getDataSize() {
        return mData == null ? 0 : mData.size();
    }

}
