package com.ywqln.marvellib.glide;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ywqln.marvellib.utils.NetworkStateUtil;

/**
 * 描述:图片加载器.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/15
 */
public class ImageLoader implements ImageLoad {

    private static ImageLoader mImageLoader;

    private ImageLoader() {

    }

    public static ImageLoader instance() {
        if (mImageLoader == null) {
            mImageLoader = new ImageLoader();
        }
        return mImageLoader;
    }

    private static boolean ONLY_WIFI = false;

    /**
     * 仅在Wifi状态下才加载
     */
    public static void onlyWifiLoad() {
        ONLY_WIFI = true;
    }

    /**
     * 总是加载，在任何网络状态下都会加载
     */
    public static void alwaysLoad() {
        ONLY_WIFI = false;
    }

    private void loadImage(ImageView view, String url, RequestOptions options) {
        if (ONLY_WIFI & !wifiState()) {
            return;
        }
        alwaysLoad(view, url, options);
    }

    @Override
    public void load(ImageView view, String url, RequestOptions options) {
        loadImage(view, url, options);
    }

    @Override
    public void load(ImageView view, int placeholder, String url, int error) {
        loadImage(view, url, ImageLoad.options(placeholder, error));
    }

    @Override
    public void load(ImageView view, String url) {
        loadImage(view, url, null);
    }

    @Override
    public void loadCircle(ImageView view, int placeholder, String url, int error) {
        loadImage(view, url, ImageLoad.options(placeholder, error).circleCrop());
    }

    @Override
    public void loadCircle(ImageView view, String url) {
        loadImage(view, url, ImageLoad.circle());
    }

    @Override
    public void alwaysLoad(ImageView view, String url, @Nullable RequestOptions options) {
        if (options != null) {
            Glide.with(view.getContext()).load(url).apply(options).into(view);
            return;
        }
        Glide.with(view.getContext()).load(url).into(view);
    }

    private boolean wifiState() {
        return NetworkStateUtil.isWifiState();
    }
}
