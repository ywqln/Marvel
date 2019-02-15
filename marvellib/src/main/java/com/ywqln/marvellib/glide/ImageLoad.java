package com.ywqln.marvellib.glide;

import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.request.RequestOptions;

/**
 * 描述:图片加载.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
public interface ImageLoad {

    void load(ImageView view, String url, RequestOptions options);

    void load(ImageView view, @DrawableRes int placeholder, String url, @DrawableRes int error);

    void load(ImageView view, String url);

    void loadCircle(ImageView view, @DrawableRes int placeholder, String url,
            @DrawableRes int error);

    void loadCircle(ImageView view, String url);

    void alwaysLoad(ImageView view, String url, RequestOptions options);

    static RequestOptions options(int placeholder, int error) {
        return new RequestOptions().placeholder(placeholder).error(error);
    }

    static RequestOptions circle() {
        return new RequestOptions().circleCrop();
    }
}
