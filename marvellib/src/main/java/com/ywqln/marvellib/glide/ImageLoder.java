package com.ywqln.marvellib.glide;

import android.support.annotation.DrawableRes;
import android.view.View;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/14
 */
public interface ImageLoder {
    // Glide.with(this).load(url).placeholder(R.drawable.loading)..into(imageView);
    void load(View view, @DrawableRes int placeHolder, String url, @DrawableRes int error);

    void load(View view, String url);
}
