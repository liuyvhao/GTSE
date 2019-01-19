package com.witfrog.gtse.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : IActivity
 * </pre>
 */
interface IActivity {

    /**
     * 初始化数据
     *
     * @param bundle 传递过来的 bundle
     */
    void initData(@Nullable final Bundle bundle);

    /**
     * 绑定布局
     *
     * @return 布局 Id
     */
    int bindLayout();

    /**
     * 初始化 view
     */
    void initView();

    /**
     * 业务操作
     */
    void doBusiness();

}
