package com.ywqln.marvel.webkit;

import android.content.Context;
import android.util.AttributeSet;

import com.ywqln.marvel.webkit.base.BaseWebView;

/**
 * 描述:MarvelWebView.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/18
 */
public class MarvelWebView<C extends MarvelChromeClient, W extends MarvelWebViewClient> extends
        BaseWebView {
    private MarvelChromeClient mMarvelChromeClient;
    private MarvelWebViewClient marvelWebViewClient;

    public MarvelWebView(Context context) {
        super(context);
        initView();
    }

    public MarvelWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MarvelWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        webChromeClient();
        webViewClient();
        getSettings().setJavaScriptEnabled(true);
    }

    private MarvelWebView webChromeClient() {
        mMarvelChromeClient = new MarvelChromeClient();
        setWebChromeClient(mMarvelChromeClient);
        return this;
    }

    public MarvelWebView<C, W> webChromeClient(C chromeClient) {
        mMarvelChromeClient = chromeClient;
        setWebChromeClient(mMarvelChromeClient);
        return this;
    }

    private MarvelWebView<C, W> webViewClient() {
        marvelWebViewClient = new MarvelWebViewClient();
        setWebViewClient(marvelWebViewClient);
        return this;
    }

    public MarvelWebView<C, W> webViewClient(W webViewClient) {
        marvelWebViewClient = webViewClient;
        setWebViewClient(marvelWebViewClient);
        return this;
    }
}
