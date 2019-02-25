package com.ywqln.marvel.net.guide.interceptor;

import com.ywqln.marvel.BuildConfig;
import com.ywqln.marvel.net.interceptor.BaseUrlInterceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 描述：新闻接口header
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/21
 */
public class NewsHeaderInterceptor implements Interceptor, BaseUrlInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("Authorization", BuildConfig.NEWSAPPCODE);
        return chain.proceed(requestBuilder.build());
    }

    @Override
    public String getBaseUrl() {
        return null;
    }
}
