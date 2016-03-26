package com.diandian.ycdyus.moneymanager;

import android.app.Application;
import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;
import cn.bmob.v3.Bmob;

/**
 * Created by cretin on 16/3/19.
 */
public class BaseApp extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "90444e10d14909d1d2320ec90fad8394");

        Hawk.init(this)
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(this))
                .setLogLevel(LogLevel.FULL)
                .build();
    }
}
