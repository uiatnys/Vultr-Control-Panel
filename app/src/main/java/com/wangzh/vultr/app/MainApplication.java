package com.wangzh.vultr.app;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.smtt.sdk.QbSdk;
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
        LeakCanary.install(this);
        CrashCat.getInstance(getApplicationContext(), Environment.getExternalStorageDirectory().getPath()+ ConstValues.FILE_ROOT_DIRECTORY,ConstValues.FILE_LOG);
        Utils.init(getApplicationContext());
        CrashReport.initCrashReport(getApplicationContext(), "f2ef6e3ee0", true);
        mSpUtils = new SPUtils(SPConst.SP_APIKEY);
        mMainApplication = MainApplication.this;
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.v("X5WebView Load Result:" , arg0?"success":"fail");
            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(),  cb);
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
