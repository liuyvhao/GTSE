package com.witfrog.gtse.base.rv;

import android.support.annotation.LayoutRes;

import java.util.List;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : SingleAdapter
 * </pre>
 */
public abstract class SingleAdapter<M> extends BaseAdapter<M> {

    private final int mLayoutId;

    public SingleAdapter(List<M> list, @LayoutRes int layoutId) {
        setData(list);
        mLayoutId = layoutId;
    }

    @Override
    protected int bindLayout(final int viewType) {
        return mLayoutId;
    }

    public void update(List<M> list) {
        setData(list);
        notifyDataSetChanged();
    }
}
