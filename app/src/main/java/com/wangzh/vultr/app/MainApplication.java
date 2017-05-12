package com.wangzh.vultr.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.utils.CrashCat;


/**
 * Created by WangZH on 2017/4/26.
 */

public class MainApplication extends Application {

    private static MainApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        CrashCat.getInstance(getApplicationContext(), Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY,ConstValues.FILE_LOG);
        Utils.init(getApplicationContext());
        CrashReport.initCrashReport(getApplicationContext(), "f2ef6e3ee0", true);
    }

    public static Context getAppContext(){
        return mApplication;
    }
}
