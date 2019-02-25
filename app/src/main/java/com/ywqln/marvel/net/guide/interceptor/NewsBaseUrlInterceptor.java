package com.ywqln.marvel.net.guide.interceptor;

import com.ywqln.marvel.net.interceptor.BaseUrlInterceptor;

/**
 * 描述:动态获取新闻baseUrl.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/22
 */
public class NewsBaseUrlInterceptor implements BaseUrlInterceptor {
    @Override
    public String getBaseUrl() {
        return "http://toutiao-ali.juheapi.com";
    }
}
