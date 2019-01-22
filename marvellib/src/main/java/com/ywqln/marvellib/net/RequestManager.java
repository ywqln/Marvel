package com.ywqln.marvellib.net;


import android.text.TextUtils;

import com.ywqln.marvellib.net.annotation.BaseUrl;
import com.ywqln.marvellib.net.annotation.DynamicBaseUrl;
import com.ywqln.marvellib.net.annotation.Interceptors;
import com.ywqln.marvellib.net.annotation.NetworkInterceptors;
import com.ywqln.marvellib.net.interceptor.BaseUrlInterceptor;
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

    /**
     * okHttpBuilder
     */
    protected OkHttpClient.Builder mOkHttpBuilder;
    /**
     * retrofitBuilder
     */
    protected Retrofit.Builder mRetrofitBuilder;

    /**
     * 禁止手动实例化
     */
    private RequestManager() {

    }

    /**
     * 得到一个RequestManager对象，保证唯一性
     */
    public static RequestManager instance() {
        if (requestManager == null) {
            requestManager = new RequestManager();
        }
        return requestManager;
    }

    /**
     * 通过接口定义interface获取一个api请求
     *
     * @param apiClass interface class
     * @param <T>      interface类型
     * @return 返回interface的实例对象
     */
    public <T> T getApi(final Class<T> apiClass) {
        return getRetrofit(apiClass).create(apiClass);
    }

    /**
     * 设置超时时间
     *
     * @param timeOut 超时时间
     * @return 返回当前对象
     */
    public RequestManager setTimeOut(long timeOut) {
        if (mOkHttpBuilder == null) {
            return null;
        }
        mOkHttpBuilder.connectTimeout(timeOut, TimeUnit.SECONDS)
                .readTimeout(timeOut, TimeUnit.SECONDS)
                .writeTimeout(timeOut, TimeUnit.SECONDS);
        return this;
    }

    /**
     * 获取一个retrofit对象，不对外提供
     *
     * @param annotationClass 注解Class
     * @return 返回一个retrofit对象
     */
    private Retrofit getRetrofit(Class annotationClass) {
        if (mRetrofitBuilder == null) {
            mRetrofitBuilder = new Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());
        }

        // 动态的优先
        BaseUrlInterceptor baseUrlInterceptor = getDynamicBaseUrl(annotationClass);
        if (baseUrlInterceptor != null) {
            mRetrofitBuilder.baseUrl(baseUrlInterceptor.getBaseUrl());
        } else {
            String baseUrl = getBaseUrl(annotationClass);
            if (!TextUtils.isEmpty(baseUrl)) {
                mRetrofitBuilder.baseUrl(baseUrl);
            }
        }

        mRetrofitBuilder.client(getOkHttpClient(annotationClass));
        return mRetrofitBuilder.build();
    }

    /**
     * 获取一个okHttpClient对象，不对外提供
     *
     * @param annotationClass 注解Class
     * @return 返回一个okHttpClient对象
     */
    private OkHttpClient getOkHttpClient(Class annotationClass) {
        if (mOkHttpBuilder == null) {
            mOkHttpBuilder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new LoggerInterceptor())
                    .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS);
        }

        Interceptor[] interceptors = getInterceptors(annotationClass);
        if (interceptors != null) {
            mOkHttpBuilder.interceptors().clear();
            for (Interceptor interceptor : interceptors) {
                mOkHttpBuilder.addInterceptor(interceptor);
            }
        }

        Interceptor[] netWorkInterceptors = getNetworkInterceptors(annotationClass);
        if (netWorkInterceptors != null) {
            mOkHttpBuilder.networkInterceptors().clear();
            for (Interceptor interceptor : netWorkInterceptors) {
                mOkHttpBuilder.addNetworkInterceptor(interceptor);
            }
        }

        return mOkHttpBuilder.build();
    }


    /**
     * 根据一个注解Class获取到注解的Interceptor数组
     *
     * @param annotationClass 注解Class
     * @return Interceptor数组
     */
    protected Interceptor[] getInterceptors(Class annotationClass) {
        // 扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(Interceptors.class)) {
            // 得到注解
            Interceptors interceptor = (Interceptors) annotationClass.getAnnotation(
                    Interceptors.class);
            // 得到注解的值
            Class<? extends Interceptor>[] interceptorClasses = interceptor.value();
            Interceptor[] interceptors = new Interceptor[interceptorClasses.length];
            try {
                for (int i = 0; i < interceptorClasses.length; i++) {
                    interceptors[i] = interceptorClasses[i].newInstance();
                }
                return interceptors;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据一个注解Class获取到注解的NetWorkInterceptor数组
     *
     * @param annotationClass 注解Class
     * @return NetWorkInterceptor数组
     */
    protected Interceptor[] getNetworkInterceptors(Class annotationClass) {
        // 是否使用了注解
        if (annotationClass.isAnnotationPresent(NetworkInterceptors.class)) {
            // 得到注解
            NetworkInterceptors interceptor = (NetworkInterceptors) annotationClass.getAnnotation(
                    NetworkInterceptors.class);
            // 得到注解的值
            Class<? extends Interceptor>[] interceptorClasses = interceptor.value();
            Interceptor[] interceptors = new Interceptor[interceptorClasses.length];
            try {
                for (int i = 0; i < interceptorClasses.length; i++) {
                    interceptors[i] = interceptorClasses[i].newInstance();
                }
                return interceptors;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据一个注解Class获取到注解的baseUrl
     *
     * @param annotationClass 注解Class
     * @return baseUrl
     */
    protected String getBaseUrl(Class annotationClass) {
        //扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(BaseUrl.class)) {
            //得到注解
            BaseUrl interceptors = (BaseUrl) annotationClass.getAnnotation(BaseUrl.class);
            //得到注解的值
            String baseUrl = interceptors.value();
            return baseUrl;
        }
        return null;
    }

    /**
     * 根据一个注解Class获取到注解的baseUrl
     *
     * @param annotationClass 注解Class
     * @return baseUrl
     */
    protected BaseUrlInterceptor getDynamicBaseUrl(Class annotationClass) {
        //扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(DynamicBaseUrl.class)) {
            //得到注解
            DynamicBaseUrl dynamicBaseUrl = (DynamicBaseUrl) annotationClass.getAnnotation(
                    DynamicBaseUrl.class);
            //得到注解的值
            Class<? extends BaseUrlInterceptor> interceptor = dynamicBaseUrl.value();

            try {
                BaseUrlInterceptor baseUrlInterceptor = interceptor.newInstance();
                return baseUrlInterceptor;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
