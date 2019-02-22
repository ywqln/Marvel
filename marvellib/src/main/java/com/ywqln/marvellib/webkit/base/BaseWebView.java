package com.ywqln.marvellib.webkit.base;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * 描述:WebView基础类.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/19
 */
public abstract class BaseWebView extends WebView {

    public BaseWebView(Context context) {
        super(context);
    }

    public BaseWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
