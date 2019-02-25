package com.ywqln.marvel.utils;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 描述:为方便而生.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class EasyUtil {
    /**
     * 布局填充
     */
    public static View inflatView(Context context, @LayoutRes int id) {
        return LayoutInflater.from(context).inflate(id, null);
    }

    /**
     * 布局填充
     */
    public static View inflatView(Context context, @LayoutRes int id, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(id, parent, false);
    }
}
