package com.wangzh.vultr.app;

import android.app.Application;
import android.content.Context;

import com.blankj.utilcode.util.Utils;


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
    }

    public static Context getAppContext(){
        return mApplication;
    }
}
