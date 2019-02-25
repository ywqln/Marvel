package com.ywqln.marvel;

import android.app.Application;

import com.ywqln.marvel.glide.ImageLoader;
import com.ywqln.marvel.utils.NetworkStateUtil;

/**
 * 描述:Application.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 注册网络状态监听
        NetworkStateUtil.registerReceiver(this);
        // 仅在Wifi状态下加载图片
        ImageLoader.onlyWifiLoad();
    }
}
