package com.wangzh.vultr.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.view.View;

import com.wangzh.vultr.R;
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

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        getSupportActionBar().hide();
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        if (Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        },1000);
    }

    @Override
    protected BasePresenter createPresenter() {
        return new SplashPresenter(this);
    }

    @Override
    public void connectVultrSuccess() {
    }

    @Override
    public void getDataFail(String failMsg) {

    }
}
