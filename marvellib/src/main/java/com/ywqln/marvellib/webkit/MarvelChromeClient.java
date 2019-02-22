package com.ywqln.marvellib.webkit;

import android.webkit.WebStorage;
import android.webkit.WebView;

import com.ywqln.marvellib.utils.WLog;
import com.ywqln.marvellib.webkit.base.BaseChromeClient;
import com.ywqln.marvellib.widget.ProgressView;

/**
 * 描述:WebChromeClient基础类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/18
 */
public class MarvelChromeClient extends BaseChromeClient {
    private ProgressView mProgressView;

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
        WLog.tempP("网页Log",
                String.format("%s -- From line %s of %s", message, lineNumber, sourceID));
    }
}
