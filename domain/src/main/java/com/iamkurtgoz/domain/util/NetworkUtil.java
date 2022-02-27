package com.iamkurtgoz.domain.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.lang.reflect.Constructor;

public class NetworkUtil {

    public static NetworkInfo getNetworkInfo(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return manager.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnected();
    }

    public static boolean isWifiConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI;
    }

    public static boolean isMobileConnected(Context context) {
        NetworkInfo info = getNetworkInfo(context);
        return info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE;
    }

    public static String getUserAgent(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }

}
