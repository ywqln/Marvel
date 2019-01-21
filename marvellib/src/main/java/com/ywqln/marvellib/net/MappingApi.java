package com.ywqln.marvellib.net;

/**
 * 描述：Api集合的映射
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/21
 */
public @interface MappingApi {
    /**
     * 返回一个为添加Header的Interceptor
     * Todo:这里想要约束返回类型为 实现了Interceptor的类
     * @return
     */
    Class headerInterceptor();

    /**
     * 设置baseUrl
     * @return
     */
    String baseUrl();
}
