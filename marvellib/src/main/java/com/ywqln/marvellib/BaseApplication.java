package com.ywqln.marvellib;

import android.app.Application;

import com.ywqln.marvellib.glide.ImageLoader;
import com.ywqln.marvellib.utils.NetworkStateUtil;

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
