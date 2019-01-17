package com.ywqln.marvellib.utils;

import android.content.Context;
import android.support.annotation.RequiresPermission;
import android.telephony.TelephonyManager;

/**
 * 描述:设备信息获取工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/1/17
 */
public class DeviceUtil {
    /**
     * 获取udid
     */
    @RequiresPermission(android.Manifest.permission.READ_PHONE_STATE)
    public static final String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(
                Context.TELEPHONY_SERVICE);
        return tm == null ? StringUtil.Empty : tm.getDeviceId();
    }
}
