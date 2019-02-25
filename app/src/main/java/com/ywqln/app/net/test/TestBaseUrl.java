package com.ywqln.app.net.test;

import com.ywqln.marvel.net.interceptor.BaseUrlInterceptor;

/**
 * 描述:测试动态获取baseUrl.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/22
 */
public class TestBaseUrl implements BaseUrlInterceptor {
    @Override
    public String getBaseUrl() {
        return "http://aaa.bbb.ccc";
    }
}
