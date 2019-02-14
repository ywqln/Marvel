package com.ywqln.marvellib.adapter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 描述:设置布局资源Id.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LayoutResId {
    /**
     * 设置布局
     *
     * @return 布局资源Id
     */
    int value() default 0;
}
