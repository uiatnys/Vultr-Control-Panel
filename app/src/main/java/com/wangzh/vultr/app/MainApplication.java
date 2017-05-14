package com.wangzh.vultr.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.tencent.bugly.crashreport.CrashReport;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.utils.CrashCat;


/**
 * Created by WangZH on 2017/4/26.
 */

public class MainApplication extends Application {

    private static MainApplication mApplication;
    private static SPUtils mSpUtils;
    private static MainApplication mMainApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        CrashCat.getInstance(getApplicationContext(), Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY,ConstValues.FILE_LOG);
        Utils.init(getApplicationContext());
        CrashReport.initCrashReport(getApplicationContext(), "f2ef6e3ee0", true);
        mSpUtils = new SPUtils(SPConst.SP_APIKEY);
        mMainApplication = MainApplication.this;
    }

    public static Context getAppContext(){
        return mApplication;
    }

    public static MainApplication getmMainApplication(){
        return mMainApplication;
    }

    public static SPUtils getSpUtils(){
        if(mSpUtils!=null){
            return mSpUtils;
        }else {
            return new SPUtils(SPConst.SP_APIKEY);
        }

    }
}
