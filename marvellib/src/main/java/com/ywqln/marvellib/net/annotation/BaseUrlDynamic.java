package com.ywqln.marvellib.net.annotation;

import com.ywqln.marvellib.net.interceptor.BaseUrlInterceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseUrlDynamic {
    /**
     * 设置baseUrl，Url可能是动态获取，而不是constant类型
     *
     * @return 返回一个继承BaseUrlInterceptor的类
     */
    Class<? extends BaseUrlInterceptor> value();
}
