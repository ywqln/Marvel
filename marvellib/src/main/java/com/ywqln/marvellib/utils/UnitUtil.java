package com.ywqln.marvellib.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * 描述:待描述.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/18
 */
public class UnitUtil {

    /**
     * dp转px
     */
    public static final float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static final float px2dp(Context context, float px) {
//        float density = context.getResources().getDisplayMetrics().density;
//        return density / (px + 0.5f);

        final float scale = context.getResources().getDisplayMetrics().density;
        return px / scale;
    }

}