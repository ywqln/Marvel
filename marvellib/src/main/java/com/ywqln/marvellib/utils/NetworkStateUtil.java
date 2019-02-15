package com.ywqln.marvellib.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * 描述:网络状态工具.
 * <p>
 *
 * @author yanwenqiang.
 * @date 2019/2/15
 */
public class NetworkStateUtil {
    /**
     * 网络是否可用
     */
    private static boolean netWorkEnable;

    /**
     * 网络类型 none; mobile; wifi
     */
    private static String netWorkType;


    public static void checkNetWork(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            netWorkEnable = networkInfo.isAvailable();
            netWorkType = networkInfo.getTypeName().equalsIgnoreCase("WIFI") ? "wifi" : "mobile";
        } else {
            netWorkEnable = false;
            netWorkType = "none";
        }
    }

    public static boolean isNetWorkEnable() {
        return netWorkEnable;
    }

    public static String getNetWorkType() {
        return netWorkType;
    }

    public static boolean isWifiState() {
        return "wifi".equals(netWorkType);
    }

    /**
     * 注册网络状态监听
     *
     * @param context 上下文
     */
    public static void registerReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                NetworkStateUtil.checkNetWork(context);
            }
        }, intentFilter);
    }
}
