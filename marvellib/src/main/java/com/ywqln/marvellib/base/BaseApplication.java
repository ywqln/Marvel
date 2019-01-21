package com.ywqln.marvellib.base;

import android.app.Application;

import com.ywqln.marvellib.utils.WLog;

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
        WLog.setDebug(true);
    }
}
