package com.ywqln.marvel.adapter.annotation;

/**
 * 描述:通过注解获取布局资源Id.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
public class LayoutUtil {

    /**
     * 根据一个注解Class获取到布局资源Id
     *
     * @param annotationClass 注解类
     * @return layoutResId
     */
    public static final int layoutResId(Class annotationClass) {
        // 扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(LayoutResId.class)) {
            // 得到注解
            LayoutResId interceptor = (LayoutResId) annotationClass.getAnnotation(
                    LayoutResId.class);
            // 得到注解的值
            int layoutResId = interceptor.value();
            return layoutResId;
        }
        return 0;
    }
}
