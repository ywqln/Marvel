package com.ywqln.marvellib.net.util;

import com.ywqln.marvellib.net.annotation.BaseUrl;
import com.ywqln.marvellib.net.annotation.DynamicBaseUrl;
import com.ywqln.marvellib.net.annotation.Interceptors;
import com.ywqln.marvellib.net.annotation.NetworkInterceptors;
import com.ywqln.marvellib.net.interceptor.BaseUrlInterceptor;

import okhttp3.Interceptor;

/**
 * 描述:注解工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/23
 */
public class AnnotationUtil {

    /**
     * 根据一个注解Class获取到注解的Interceptor数组
     *
     * @param annotationClass 注解Class
     * @return Interceptor数组
     */
    public static final Interceptor[] getInterceptors(Class annotationClass) {
        // 扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(Interceptors.class)) {
            // 得到注解
            Interceptors interceptor = (Interceptors) annotationClass.getAnnotation(
                    Interceptors.class);
            // 得到注解的值
            Class<? extends Interceptor>[] interceptorClasses = interceptor.value();
            Interceptor[] interceptors = new Interceptor[interceptorClasses.length];
            try {
                for (int i = 0; i < interceptorClasses.length; i++) {
                    interceptors[i] = interceptorClasses[i].newInstance();
                }
                return interceptors;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据一个注解Class获取到注解的NetWorkInterceptor数组
     *
     * @param annotationClass 注解Class
     * @return NetWorkInterceptor数组
     */
    public static final Interceptor[] getNetworkInterceptors(Class annotationClass) {
        // 是否使用了注解
        if (annotationClass.isAnnotationPresent(NetworkInterceptors.class)) {
            // 得到注解
            NetworkInterceptors interceptor = (NetworkInterceptors) annotationClass.getAnnotation(
                    NetworkInterceptors.class);
            // 得到注解的值
            Class<? extends Interceptor>[] interceptorClasses = interceptor.value();
            Interceptor[] interceptors = new Interceptor[interceptorClasses.length];
            try {
                for (int i = 0; i < interceptorClasses.length; i++) {
                    interceptors[i] = interceptorClasses[i].newInstance();
                }
                return interceptors;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 根据一个注解Class获取到注解的baseUrl
     *
     * @param annotationClass 注解Class
     * @return baseUrl
     */
    public static final String getBaseUrl(Class annotationClass) {
        // 扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(BaseUrl.class)) {
            // 得到注解
            BaseUrl interceptors = (BaseUrl) annotationClass.getAnnotation(BaseUrl.class);
            // 得到注解的值
            String baseUrl = interceptors.value();
            return baseUrl;
        }
        return null;
    }

    /**
     * 根据一个注解Class获取到注解的baseUrl
     *
     * @param annotationClass 注解Class
     * @return baseUrl
     */
    public static final BaseUrlInterceptor getDynamicBaseUrl(Class annotationClass) {
        // 扫描这个类是否使用了注解
        if (annotationClass.isAnnotationPresent(DynamicBaseUrl.class)) {
            // 得到注解
            DynamicBaseUrl dynamicBaseUrl = (DynamicBaseUrl) annotationClass.getAnnotation(
                    DynamicBaseUrl.class);
            // 得到注解的值
            Class<? extends BaseUrlInterceptor> interceptor = dynamicBaseUrl.value();

            try {
                BaseUrlInterceptor baseUrlInterceptor = interceptor.newInstance();
                return baseUrlInterceptor;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
