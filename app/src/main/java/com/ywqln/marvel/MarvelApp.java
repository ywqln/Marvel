package com.ywqln.marvel;

import com.ywqln.marvellib.BaseApplication;
import com.ywqln.marvellib.utils.WLog;

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
