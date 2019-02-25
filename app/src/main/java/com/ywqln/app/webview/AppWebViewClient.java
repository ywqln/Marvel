package com.ywqln.app.webview;

import com.ywqln.marvel.webkit.MarvelWebViewClient;

/**
 * 描述:AppWebViewClient.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/22
 */
public class AppWebViewClient extends MarvelWebViewClient {
    public AppWebViewClient() {
        setInterceptProtocol("ywq");
    }
}
