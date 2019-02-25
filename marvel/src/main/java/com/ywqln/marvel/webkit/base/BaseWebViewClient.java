package com.ywqln.marvel.webkit.base;

import android.graphics.Bitmap;
import android.webkit.WebView;
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
    protected String mInterceptProtocol = "marvel";
    protected List<BaseInterceptor> mBaseInterceptors;
    private String mTitle;

    public String getTitle() {
        return mTitle;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        mTitle = view.getTitle();
    }


    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        // 加载完成
        mTitle = view.getTitle();
    }


}
