package com.wangzh.vultr.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;


/**
 * Created by WangZH on 2017/4/26.
 */

public class MainApplication extends Application {

    private static MainApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        Utils.init(getApplicationContext());
        CrashReport.initCrashReport(getApplicationContext(), "f2ef6e3ee0", true);
    }

    public static Context getAppContext(){
        return mApplication;
    }
}
