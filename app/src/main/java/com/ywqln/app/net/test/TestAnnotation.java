package com.ywqln.app.net.test;

import com.ywqln.marvel.net.annotation.BaseUrl;
import com.ywqln.marvel.net.annotation.Interceptors;
import com.ywqln.marvel.net.util.AnnotationUtil;

import okhttp3.Interceptor;

/**
 * 描述:测试注解.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/22
 */
public class TestAnnotation {
    @BaseUrl(dynamic = TestBaseUrl.class)
    @Interceptors(ResultResp.class)
    public interface TestApi {

    }

    public void testAnnotation() {
        Interceptor[] interceptors = AnnotationUtil.getInterceptors(TestApi.class);
        String baseUrl = AnnotationUtil.getBaseUrl(TestApi.class);
        ResultResp interceptor = (ResultResp) interceptors[0];
        String result = interceptor.getName();

        System.out.println("测试interceptor设置 -> " + result);
        System.out.println("测试baseUrl设置 -> " + baseUrl);
    }
}
