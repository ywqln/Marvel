package com.ywqln.marvel.webview;

import android.webkit.WebView;

import com.ywqln.marvellib.webkit.MarvelChromeClient;
import com.ywqln.marvellib.widget.ProgressView;

/**
 * 描述:AppWebChromeClient.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/19
 */
public class AppWebChromeClient extends MarvelChromeClient {

    public AppWebChromeClient(ProgressView view) {
        setProgressView(view);
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        super.onProgressChanged(view, newProgress);
//        WLog.p("进度", newProgress);
    }
}
