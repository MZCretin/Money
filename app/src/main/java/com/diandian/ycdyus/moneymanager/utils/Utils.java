package com.diandian.ycdyus.moneymanager.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.diandian.ycdyus.moneymanager.constants.AppConfig;


/**
 * Created by ycdyus on 2015/9/22.
 */
public class Utils {
    public static final String isFirstKey = "isFirst";
    public static final String versinKey = "version";
    //判断是否是第一次进入
    public static boolean isFirst(Context context){
        boolean flag = false;
        int version = 1;
        SharedPreferences preferences = context.getSharedPreferences(AppConfig.ISFRTST,Context.MODE_PRIVATE);
        flag = preferences.getBoolean(isFirstKey,true);
        version = preferences.getInt(versinKey,1);
        if(flag || version != getVersion(context)){
            preferences.edit().putBoolean(isFirstKey,false).commit();
            preferences.edit().putInt(versinKey,getVersion(context)).commit();
            return true;
        }
        return false;
    }
    //获取版本信息
    public static int getVersion(Context context){
        int version = 1;
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),0);
            version = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return version;
    }
}
