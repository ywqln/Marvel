package com.ywqln.marvellib.widget.webview.base;

import android.webkit.WebViewClient;

import java.util.List;

/**
 * 描述:BaseWebViewClient.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/21
 */
public abstract class BaseWebViewClient extends WebViewClient implements UrlResolve {
    protected String interceptProtocol = "marvel";
    protected List<BaseInterceptor> mBaseInterceptors;
}
