package com.ywqln.marvellib.net.guide.interceptor;

import com.ywqln.marvellib.net.interceptor.BaseUrlInterceptor;

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
public class NewsHeaderInterceptor implements Interceptor,BaseUrlInterceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.addHeader("Authorization", "APPCODE 1b56403f51d84d66812ac7aa274fefe6");
        return chain.proceed(requestBuilder.build());
    }

    @Override
    public String getBaseUrl() {
        return null;
    }
}
