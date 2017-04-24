package com.wangzh.vultr.presenter;

import com.wangzh.vultr.model.net.CallBack;
import com.wangzh.vultr.presenter.i.SplashViewI;

/**
 * Created by WangZH on 2017/4/24.
 */

public class SplashPresenter extends BasePresenter<SplashViewI> {

    public SplashPresenter(SplashViewI splashViewI){
        attachView(splashViewI);
    }

    public void checkVultrServer(){
        addSubscribe(mApi.checkVlutrServer(), new CallBack() {
            @Override
            public void onSuccess(Object o) {
                view.connectVultrSuccess();
            }

            @Override
            public void onFail(String msg) {
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }
}
