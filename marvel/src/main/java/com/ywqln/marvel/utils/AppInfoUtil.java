package com.ywqln.marvel.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;

/**
 * 描述:应用信息获取工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class AppInfoUtil {
    /**
     * 获取App 名称
     */
    public static final String getAppName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        String appName = StringUtil.Empty;
        if (packageManager != null) {
            PackageInfo pi = null;
            try {
                pi = packageManager.getPackageInfo(context.getPackageName(), 0);
                appName = pi.applicationInfo.loadLabel(packageManager).toString();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return appName;
    }

    public static final String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 获取当前版本
     */
    public static final long getAppVerCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        long longVersionCode = 0;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                longVersionCode = packageManager.getPackageInfo(context.getPackageName(),
                        0).getLongVersionCode();
            } else {
                longVersionCode = packageManager.getPackageInfo(context.getPackageName(),
                        0).versionCode;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            longVersionCode = -1;
        }
        return longVersionCode;
    }
}
