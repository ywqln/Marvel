package com.ywqln.marvel.webkit.base;

import com.google.gson.Gson;
import com.ywqln.marvel.utils.StringUtil;

/**
 * 描述:UrlResolve.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/21
 */
public interface UrlResolve {
    String PARAMKEY = "urlParams";
    String URL_QUESTION_MARK = "?";
    String URL_AND_MARK = "&";

    static WebAction toObject(String url) {
        WebAction webAction = toObject(url, WebAction.class);
        return webAction;
    }

    static <T> T toObject(String url, Class<T> clz) {
        int index = url.indexOf(PARAMKEY + "=");
        String params = url.substring(index + PARAMKEY.length() + 1, url.length());
        T object = new Gson().fromJson(params, clz);
        return object;
    }

    static String toJson(Object object) {
        String json = new Gson().toJson(object);
        return json;
    }

    static String toUrlParam(String url, Object json) {
        String params = PARAMKEY + "=" + toJson(json);
        String startTag = StringUtil.Empty;
        if (!url.endsWith(URL_QUESTION_MARK)) {
            if (url.contains(URL_QUESTION_MARK)) {
                startTag = URL_AND_MARK;
            } else {
                startTag = URL_QUESTION_MARK;
            }
        }
        String urlParam = url + startTag + params;
        return urlParam;
    }
}
