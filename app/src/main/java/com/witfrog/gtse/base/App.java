package com.witfrog.gtse.base;

import android.annotation.SuppressLint;
import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : App
 * </pre>
 */
public class App extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        SmartRefreshLayout.setDefaultRefreshInitializer((context, layout) -> {
            layout.setEnableAutoLoadMore(true);
            layout.setEnableHeaderTranslationContent(false);
            layout.setEnableLoadMoreWhenContentNotFull(true);
            layout.setEnableOverScrollBounce(true);
            layout.setEnableOverScrollDrag(true);
            layout.setEnableScrollContentWhenRefreshed(false);
        });
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> new MaterialHeader(context));
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> new ClassicsFooter(context));
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        initLog();
        initCrash();
    }

    private void initLog() {
        final LogUtils.Config config = LogUtils.getConfig()
                .setLogSwitch(true)// 设置 log 总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(true)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置 log 全局标签，默认为空
                // 当全局标签不为空时，我们输出的 log 全部为该 tag，
                // 为空时，如果传入的 tag 为空那就显示类名，否则显示 tag
                .setLogHeadSwitch(true)// 设置 log 头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印 log 时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setFilePrefix("")// 当文件前缀为空时，默认为"util"，即写入文件为"util-MM-dd.txt"
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setSingleTagSwitch(true)// 一条日志仅输出一条，默认开，为美化 AS 3.1 的 Logcat
                .setConsoleFilter(LogUtils.V)// log 的控制台过滤器，和 logcat 过滤器同理，默认 Verbose
                .setFileFilter(LogUtils.V)// log 文件过滤器，和 logcat 过滤器同理，默认 Verbose
                .setStackDeep(1)// log 栈深度，默认为 1
                .setStackOffset(0);// 设置栈偏移，比如二次封装的话就需要设置，默认为 0
        LogUtils.d(config.toString());
    }

    @SuppressLint("MissingPermission")
    private void initCrash() {
        CrashUtils.init((crashInfo, e) -> {
            LogUtils.e(crashInfo);
            AppUtils.relaunchApp();
        });
    }
}