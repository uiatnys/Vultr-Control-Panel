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
import android.view.Window;

import com.blankj.utilcode.util.SPUtils;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.presenter.i.BaseViewI;

/**
 * Created by 99210 on 2017/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViewI{

    public Activity mActivity;
    protected static SPUtils mSPUtils;

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
}
