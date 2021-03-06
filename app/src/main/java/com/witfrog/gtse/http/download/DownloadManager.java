package com.witfrog.gtse.http.download;

import com.blankj.utilcode.util.LogUtils;
import com.witfrog.gtse.config.HttpConfig;
import com.witfrog.gtse.http.ApiService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : DownloadManager
 * </pre>
 */
public class DownloadManager {

    private static DownloadManager instance = null;

    private Retrofit.Builder builder = new Retrofit.Builder()
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(HttpConfig.HTTP_BASE_URL);
    private OkHttpClient     client  = new OkHttpClient.Builder()
            .connectTimeout(HttpConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(HttpConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(HttpConfig.HTTP_TIMEOUT, TimeUnit.MILLISECONDS)
            .build();

    public static DownloadManager getInstance() {
        if (instance == null) {
            instance = new DownloadManager();
        }
        return instance;
    }

    public void downloadFileProgress(String url, final ProgressListener listener, Callback<ResponseBody> callback) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> LogUtils.d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = client.newBuilder()
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(chain -> {
                    Response response = chain.proceed(chain.request());
                    return response.newBuilder()
                            .body(new ProgressResponseBody(response.body(), listener))
                            .build();
                }).build();

        ApiService apiService = builder.client(okHttpClient).build().create(ApiService.class);

        apiService.downloadFile(url).enqueue(callback);
    }

}
