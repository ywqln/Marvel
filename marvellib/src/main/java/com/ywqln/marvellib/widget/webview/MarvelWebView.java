package com.ywqln.marvellib.widget.webview;

import android.content.Context;
import android.util.AttributeSet;

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
        setWebChromeClient();
        setWebViewClient();
        getSettings().setJavaScriptEnabled(true);
    }

    private MarvelChromeClient setWebChromeClient() {
        mMarvelChromeClient = new MarvelChromeClient();
        setWebChromeClient(mMarvelChromeClient);
        return mMarvelChromeClient;
    }

    public C setWebChromeClient(C chromeClient) {
        mMarvelChromeClient = chromeClient;
        setWebChromeClient(mMarvelChromeClient);
        return chromeClient;
    }

    private MarvelWebView<C, W> setWebViewClient() {
        marvelWebViewClient = new MarvelWebViewClient();
        setWebViewClient(marvelWebViewClient);
        return this;
    }

    public MarvelWebView<C, W> setWebViewClient(W webViewClient) {
        marvelWebViewClient = webViewClient;
        setWebViewClient(marvelWebViewClient);
        return this;
    }
}
