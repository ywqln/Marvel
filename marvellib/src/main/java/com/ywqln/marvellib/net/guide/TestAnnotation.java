package com.ywqln.marvellib.net.guide;

import android.util.Log;

import com.ywqln.marvellib.net.util.AnnotationUtil;
import com.ywqln.marvellib.net.annotation.BaseUrl;
import com.ywqln.marvellib.net.annotation.DynamicBaseUrl;
import com.ywqln.marvellib.net.annotation.Interceptors;
import com.ywqln.marvellib.net.guide.dto.response.ResultResp;
import com.ywqln.marvellib.utils.WLog;

import okhttp3.Interceptor;

/**
 * 描述:测试注解.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/22
 */
public class TestAnnotation {
    @BaseUrl("http://www.xxx.com")
    @DynamicBaseUrl(TestBaseUrl.class)
    @Interceptors(ResultResp.class)
    public interface TestApi {

    }

    public void testAnnotation() {
        Interceptor[] interceptors = AnnotationUtil.getInterceptors(TestApi.class);
        String baseUrl = AnnotationUtil.getBaseUrl(TestApi.class);
        ResultResp interceptor = (ResultResp) interceptors[0];
        String daynamicBaseUrl = AnnotationUtil.getDynamicBaseUrl(TestApi.class).getBaseUrl();
        String result = interceptor.getName();
        Log.e("测试e", result);
        Log.d("测试d", result);
        WLog.p("测试Wp", result);
        WLog.e("测试We", result);
        WLog.e("baseUrl", baseUrl);
        WLog.e("动态baseUrl", daynamicBaseUrl);
    }
}
