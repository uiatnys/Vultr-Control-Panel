package com.wangzh.vultr.ui.base;

import com.wangzh.vultr.presenter.BasePresenter;

/**
 * Created by WangZH on 2017/4/24.
 */

public abstract class BasePresenterActivity<P extends BasePresenter> extends BaseActivity {


    protected abstract void initView();
    protected abstract P createPresenter();
    protected P presenter;

    @Override
    protected void init() {
        initView();
    }

    @Override
    protected void onDestroy() {
        if (presenter !=null){
            presenter.detachView();
        }
        super.onDestroy();
    }
}
