package com.example.phoebegl.gitlabclient.data.util;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit 网络接口服务的包装类
 * Created by phoebegl on 2017/6/7.
 */
public class RetrofitWrapper {
    public  static String BASEURL="http://115.29.184.56:8090/api/";

    private static RetrofitWrapper instance;
    private Retrofit retrofit;

    private RetrofitWrapper() {
        //创建Retrofit对象
        retrofit = new Retrofit.Builder().baseUrl(BASEURL) // 定义访问的主机地址
                .addConverterFactory(GsonConverterFactory.create())  //解析方法
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
    /**
     * 单例模式
     */
    public static RetrofitWrapper getInstance() {
        if (instance == null) {
            synchronized (RetrofitWrapper.class){
                if (instance==null){
                    instance = new RetrofitWrapper();
                }
            }
        }
        return instance;
    }

    public <T> T create(final Class<T> service) {
        return retrofit.create(service);
    }

}

