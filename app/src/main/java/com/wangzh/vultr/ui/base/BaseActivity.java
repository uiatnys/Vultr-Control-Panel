package com.wangzh.vultr.ui.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.KeyEvent;
import android.view.Window;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.presenter.i.BaseViewI;

import es.dmoral.toasty.Toasty;

/**
 * Created by 99210 on 2017/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViewI{

    public Activity mActivity;
    protected static SPUtils mSPUtils;
    //上次按下返回键的系统时间
    private long lastBackTime = 0;
    //当前按下返回键的系统时间
    private long currentBackTime = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mActivity = this;
        mSPUtils = new SPUtils(SPConst.SHAREDPREFERENCE_SYSNAME);
    }

    protected abstract void init();

    @Override
    public void onFinish() {
        //TODO
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    protected void setTransition(int enterTransition, int exitTransition, int reenterTransition){
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        Transition enter = TransitionInflater.from(this).inflateTransition(enterTransition);
        Transition exit = TransitionInflater.from(this).inflateTransition(exitTransition);
        Transition reenter = TransitionInflater.from(this).inflateTransition(reenterTransition);
        getWindow().setEnterTransition(enter);
        getWindow().setExitTransition(exit);
        getWindow().setReenterTransition(reenter);
    }

    protected void startActivityTransition(Intent intent,Activity activity){
      /*  if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        }else {*/
            startActivity(intent);
        //}
    }

    protected void showFragment(int layout,Fragment fragment){
        showFragment(layout,fragment,null);
    }

    protected void showFragment(int layout,Fragment fragment,String tag){
        getFragmentManager().beginTransaction().replace(layout,fragment,tag).commitAllowingStateLoss();
    }

    protected Fragment getFragmentByTag(String tag){
        try {
            return getFragmentManager().findFragmentByTag(tag);
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //获取当前系统时间的毫秒数
            currentBackTime = System.currentTimeMillis();
            //比较上次按下返回键和当前按下返回键的时间差，如果大于2秒，则提示再按一次退出
            if(currentBackTime - lastBackTime > 2 * 1000){
               Toasty.info(this,"Press the Back button again to exit!", Toast.LENGTH_SHORT).show();
                lastBackTime = currentBackTime;
            }else{ //如果两次按下的时间差小于2秒，则退出程序
                onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
