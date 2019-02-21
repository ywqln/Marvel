package com.ywqln.marvellib.widget.webview;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.ywqln.marvellib.utils.WLog;
import com.ywqln.marvellib.widget.webview.base.BaseInterceptor;
import com.ywqln.marvellib.widget.webview.base.BaseWebViewClient;
import com.ywqln.marvellib.widget.webview.base.UrlResolve;
import com.ywqln.marvellib.widget.webview.base.WebAction;

/**
 * 描述:MarvelWebViewClient.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/19
 */
public class MarvelWebViewClient extends BaseWebViewClient {

    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mTitle = view.getTitle();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        WLog.p("WebView", request.getUrl());
        if (intercept(view, request.getUrl().toString())) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, request);
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        WLog.p("WebView", url);
        if (intercept(view, url)) {
            return true;
        }
        return super.shouldOverrideUrlLoading(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        // 加载完成
        mTitle = view.getTitle();
    }

    @Override
    public void onReceivedHttpError(WebView view, WebResourceRequest request,
            WebResourceResponse errorResponse) {
        super.onReceivedHttpError(view, request, errorResponse);
    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        super.onReceivedSslError(view, handler, error);
    }

    @Override
    public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host,
            String realm) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm);
    }

    @Override
    public void onReceivedClientCertRequest(WebView view, ClientCertRequest request) {
        super.onReceivedClientCertRequest(view, request);
    }

    /**
     * 拦截页面跳转，或通过Url来完成交互
     *
     * @param url webview url
     * @return 是否要拦截继续加载跳转页面，默认不拦截，false
     */
    protected boolean intercept(WebView webView, String url) {
        if (url.startsWith("tel:")) {
            webView.getContext().startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(url)));
            return true;
        }
        if (url.startsWith(interceptProtocol)) {
            WebAction webAction = UrlResolve.toObject(url);
            for (BaseInterceptor interceptor : mBaseInterceptors) {
                if (interceptor.getAction().equals(webAction)) {
                    return interceptor.apply(webView.getContext(), url);
                }
            }
        }
        return false;
    }
}
