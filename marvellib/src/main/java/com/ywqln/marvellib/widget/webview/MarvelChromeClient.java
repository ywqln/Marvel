package com.ywqln.marvellib.widget.webview;

import android.net.Uri;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebStorage;
import android.webkit.WebView;

import com.ywqln.marvellib.utils.WLog;
import com.ywqln.marvellib.widget.ProgressView;

/**
 * 描述:WebChromeClient基础类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/18
 */
public class MarvelChromeClient extends WebChromeClient {
    private ProgressView mProgressView;
    private ValueCallback<Uri[]> mValueCallback;

    public MarvelChromeClient setProgressView(ProgressView progressView) {
        mProgressView = progressView;
        return this;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
        if (mProgressView != null) {
            mProgressView.setProgress(newProgress);
        }
    }

    // For Android >= 5.0
    @Override
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback,
            FileChooserParams fileChooserParams) {
        mValueCallback = filePathCallback;
        openImageChooser();
        return true;
    }

    /**
     * 在程序达到缓存峰值后
     */
    @Override
    public void onReachedMaxAppCacheSize(long requiredStorage, long quota,
            WebStorage.QuotaUpdater quotaUpdater) {
        quotaUpdater.updateQuota(requiredStorage * 2);
    }

    @Override
    public void onConsoleMessage(String message, int lineNumber, String sourceID) {
        WLog.p("网页Log", String.format("%s -- From line %s of %s", message, lineNumber, sourceID));
    }

    /**
     * 需要重写
     */
    protected void openImageChooser() {
        WLog.e("MarvelChromeClient", "请重写openImageChooser");
    }
}
