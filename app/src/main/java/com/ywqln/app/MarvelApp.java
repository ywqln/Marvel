package com.ywqln.app;

import com.ywqln.marvel.BaseApplication;
import com.ywqln.marvel.utils.WLog;

/**
 * 描述：应用 Application
 * <p>
 *
 * @author yanwenqiang
 * @date 2019/1/13
 */
public class MarvelApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        WLog.strategy(new WLog.Strategy().tag("ywq"));
        WLog.setEnable(true);
    }
}
