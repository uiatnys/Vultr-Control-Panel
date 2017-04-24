package com.wangzh.vultr.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.wangzh.vultr.presenter.i.BaseViewI;

/**
 * Created by 99210 on 2017/4/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseViewI{

    public Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        mActivity = this;
    }

    protected abstract void init();

    @Override
    public void onFinish() {
        //TODO
    }
}
