package com.ywqln.marvellib.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 描述:MarvelWebView.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/18
 */
public class MarvelWebView extends WebView {
    private MarvelChromeClient mMarvelChromeClient;

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
        setClient();
        settings();
    }

    public MarvelChromeClient setWebChromeClient() {
        if (mMarvelChromeClient == null) {
            mMarvelChromeClient = new MarvelChromeClient();
        }
        setWebChromeClient(mMarvelChromeClient);
        return mMarvelChromeClient;
    }

    public MarvelChromeClient setWebChromeClient(Class<? extends MarvelChromeClient> chromeClient) {
        if (mMarvelChromeClient == null) {
            try {
                mMarvelChromeClient = chromeClient.newInstance();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
        setWebChromeClient(mMarvelChromeClient);
        return mMarvelChromeClient;
    }

    private void setClient() {
        setWebViewClient(new WebViewClient());
    }

    private void settings() {
        getSettings().setJavaScriptEnabled(true);
    }
}
