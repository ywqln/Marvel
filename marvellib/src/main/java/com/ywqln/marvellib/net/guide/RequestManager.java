package com.ywqln.marvellib.net.guide;


import com.ywqln.marvellib.net.MappingApi;
import com.ywqln.marvellib.net.interceptor.LoggerInterceptor;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述:请求管理器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/21
 */
public class RequestManager {
    private final static long DEFAULT_CONNECT_TIMEOUT = 15;
    private final static long DEFAULT_READ_TIMEOUT = 15;
    private final static long DEFAULT_WRITE_TIMEOUT = 15;
    private static RequestManager requestManager;

    protected OkHttpClient.Builder mOkHttpBuilder;
    protected Retrofit.Builder mRetrofitBuilder;

    private RequestManager() {

    }

    public static RequestManager instance() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }
        return requestManager;
    }

    public <T> T getApi(final Class<T> apiClass) {
        return getRetrofitBuilder(apiClass).build().create(apiClass);
    }

    private Retrofit.Builder getRetrofitBuilder(Class annotationClass) {
        mRetrofitBuilder = new Retrofit.Builder()
                .baseUrl(getBaseUrl(annotationClass))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient(annotationClass).build());

        return mRetrofitBuilder;
    }

    private OkHttpClient.Builder getOkHttpClient(Class annotationClass) {
        if (mOkHttpBuilder == null) {
            mOkHttpBuilder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new LoggerInterceptor())
                    .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        }

        mOkHttpBuilder.interceptors().clear();
        mOkHttpBuilder.addInterceptor((Interceptor) getInterceptor(annotationClass));

        return mOkHttpBuilder;
    }


    public Object getInterceptor(Class annotationClass) {
        //扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(MappingApi.class)) {
            //得到注解
            MappingApi mappingApi = (MappingApi) annotationClass.getAnnotation(MappingApi.class);
            //得到注解的值
            Class cls = mappingApi.headerInterceptor();
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public String getBaseUrl(Class annotationClass) {
        //扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(MappingApi.class)) {
            //得到注解
            MappingApi mappingApi = (MappingApi) annotationClass.getAnnotation(MappingApi.class);
            //得到注解的值
            String baseUrl = mappingApi.baseUrl();
            return baseUrl;
        }
        return null;
    }
}
