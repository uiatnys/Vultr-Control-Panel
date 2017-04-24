package com.wangzh.vultr.presenter;

import com.wangzh.vultr.presenter.i.MainViewI;

/**
 * Created by WangZH on 2017/4/24.
 */

public class MainPresenter extends BasePresenter<MainViewI> {

    public MainPresenter(MainViewI mainViewI){
        attachView(mainViewI);
    }
}
