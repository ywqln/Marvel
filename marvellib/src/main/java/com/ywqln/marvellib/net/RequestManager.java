package com.ywqln.marvellib.net;


import android.text.TextUtils;

import com.ywqln.marvellib.net.interceptor.LoggerInterceptor;
import com.ywqln.marvellib.net.util.AnnotationUtil;
import com.ywqln.marvellib.net.util.HttpsUtil;

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

        String baseUrl = AnnotationUtil.getBaseUrl(annotationClass);
        if (!TextUtils.isEmpty(baseUrl)) {
            mRetrofitBuilder.baseUrl(baseUrl);
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
            HttpsUtil httpsUtil = new HttpsUtil();
            mOkHttpBuilder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(new LoggerInterceptor())
                    .connectTimeout(DEFAULT_CONNECT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_READ_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_WRITE_TIMEOUT, TimeUnit.SECONDS)
                    // 添加https支持
                    .hostnameVerifier(httpsUtil.hostNameVerifier())
                    // ssl
                    .sslSocketFactory(httpsUtil.getSSLSocketFactory(), httpsUtil.getX509());
        }

        Interceptor[] interceptors = AnnotationUtil.getInterceptors(annotationClass);
        if (interceptors != null) {
            mOkHttpBuilder.interceptors().clear();
            for (Interceptor interceptor : interceptors) {
                mOkHttpBuilder.addInterceptor(interceptor);
            }
        }

        Interceptor[] netWorkInterceptors = AnnotationUtil.getNetworkInterceptors(annotationClass);
        if (netWorkInterceptors != null) {
            mOkHttpBuilder.networkInterceptors().clear();
            for (Interceptor interceptor : netWorkInterceptors) {
                mOkHttpBuilder.addNetworkInterceptor(interceptor);
            }
        }

        return mOkHttpBuilder.build();
    }
}
