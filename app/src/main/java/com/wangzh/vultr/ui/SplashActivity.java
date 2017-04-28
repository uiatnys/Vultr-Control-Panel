package com.wangzh.vultr.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import com.blankj.utilcode.util.StringUtils;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.presenter.BasePresenter;
import com.wangzh.vultr.presenter.SplashPresenter;
import com.wangzh.vultr.presenter.i.SplashViewI;
import com.wangzh.vultr.ui.base.BasePresenterActivity;

import butterknife.ButterKnife;

/**
 * Created by WangZH on 2017/4/24.
 */

public class SplashActivity extends BasePresenterActivity implements SplashViewI {

    private SplashPresenter mSplashPresenter;
    private Intent mIntent;
    private static final int FLAG_NEEDINPUTKEY = 0;
    private static final int FLAG_CHECKKEYSUCCESS = 1;
    private String API_KEY ="";


    @Override
    protected void initView() {
        //activity跳转动画，存在bug
        //setTransition(android.R.transition.fade,android.R.transition.fade,android.R.transition.fade);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        presenter = createPresenter();
        mSplashPresenter = (SplashPresenter) presenter;
    }

    @Override
    protected void onResume() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onResume();
        mIntent = new Intent(mActivity,MainActivity.class);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        API_KEY = mSPUtils.getString(SPConst.SP_APIKEY);
        if (!StringUtils.isEmpty(API_KEY)){
            mSplashPresenter.getAccountInfoByKey(API_KEY);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mIntent.setFlags(FLAG_NEEDINPUTKEY);
                    startActivityTransition(mIntent,mActivity);
                    finish();
                }
            },1000);
        }
    }

    @Override
    protected BasePresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public void getDataFail(HttpErrorVo failMsg) {
        mIntent.setFlags(FLAG_NEEDINPUTKEY);
        startActivityTransition(mIntent,mActivity);
        finish();
    }

    @Override
    public void onCheckApiKeySuccess(AccountInfoDTO dto) {
        mIntent.setFlags(FLAG_CHECKKEYSUCCESS).putExtra("dto",dto);
        startActivityTransition(mIntent,mActivity);
        finish();
    }
}
