package com.ywqln.marvellib.webkit.base;

import android.content.Context;

/**
 * 描述:通过url拦截webview加载的处理对象 - 基类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/21
 */
public abstract class BaseInterceptor {
    public BaseInterceptor(String action) {
        this.action = action;
    }

    private String action;

    public String getAction() {
        return action;
    }

    public abstract boolean apply(Context context, String url);
}
