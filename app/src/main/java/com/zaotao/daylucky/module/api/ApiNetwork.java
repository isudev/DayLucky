package com.zaotao.daylucky.module.api;


import com.zaotao.daylucky.App;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description ApiNetwork
 * Created by wangisu@qq.com on 2019/7/15.
 */

public class ApiNetwork {
    private static volatile Retrofit retrofit;
    private static ApiNetwork instance;

    private ApiNetwork() {

    }

    private static ApiNetwork getInstance() {
        if (instance == null) {
            synchronized (ApiNetwork.class) {
                if (instance == null) {
                    instance = new ApiNetwork();
                }
            }
        }
        return instance;
    }


    public static <S> S getNetService(String baseUrl, Class<S> service) {
        return getInstance().getRetrofit(baseUrl).create(service);
    }

    public static <S> S getNetService(Class<S> service) {
        return getNetService(AppBaseURL.API_URL, service);
    }

    private Retrofit getRetrofit(String baseUrl) {

        if (baseUrl.isEmpty()) {
            throw new IllegalStateException("baseUrl can not be null");
        }

        if (retrofit == null) {
            synchronized (ApiNetwork.class) {
                if (retrofit == null) {
                    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                    httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    File cacheFile = new File(App.getApplication().getCacheDir(), "cache");
                    Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //50Mb 缓存的大小
                    OkHttpClient client = new OkHttpClient
                            .Builder()
                            .addInterceptor(new RequestInterceptor())
                            .addInterceptor(httpLoggingInterceptor) //日志,所有的请求响应
                            .cache(cache)  //添加缓存
                            .connectTimeout(15, TimeUnit.SECONDS)//连接超时时间
                            .readTimeout(15, TimeUnit.SECONDS)//读取超时时间
                            .writeTimeout(15, TimeUnit.SECONDS)//写入超时时间
                            .build();

                    retrofit = new Retrofit
                            .Builder()
                            .baseUrl(baseUrl)
                            .client(client)
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }
        return retrofit;
    }

}
