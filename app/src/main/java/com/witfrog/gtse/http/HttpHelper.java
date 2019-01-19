package com.witfrog.gtse.http;

import android.annotation.SuppressLint;

import com.blankj.utilcode.util.LogUtils;
import com.google.gson.GsonBuilder;
import com.witfrog.gtse.config.HttpConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : HttpHelper
 * </pre>
 */
public class HttpHelper {

    @SuppressLint("StaticFieldLeak")
    private static HttpHelper instance  = null;
    private        Retrofit   mRetrofit = null;

    private HttpHelper() {
        init();
    }

    public static HttpHelper getInstance() {
        if (instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }

    private void init() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(LogUtils::d);
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(HttpConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request();

                    HttpUrl.Builder authorizedUrlBuilder = request.url()
                            .newBuilder()
                            .addQueryParameter("g", "Api");

                    Request newRequest = request.newBuilder()
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .method(request.method(), request.body())
                            .url(authorizedUrlBuilder.build())
                            .build();
                    return chain.proceed(newRequest);
                });

        mRetrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.HTTP_BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public ApiService getServer() {
        return mRetrofit.create(ApiService.class);
    }

}
