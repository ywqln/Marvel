package com.ywqln.marvellib.widget.webview;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.ywqln.marvellib.widget.ProgressView;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/18
 */
public class MarvelChromeClient extends WebChromeClient {
    private ProgressView mProgressView;

    public MarvelChromeClient setProgressView(ProgressView progressView) {
        mProgressView = progressView;
        return this;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        mProgressView.setProgress(newProgress);
    }
}
