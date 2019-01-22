package com.ywqln.marvellib.net.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:设置baseUrl注解.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/22
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface BaseUrl {
    /**
     * 设置baseUrl
     *
     * @return 返回baseUrl
     */
    String value() default "";
}
