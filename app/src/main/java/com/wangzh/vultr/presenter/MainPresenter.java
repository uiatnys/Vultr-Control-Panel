package com.wangzh.vultr.presenter;

import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.model.net.CallBack;
import com.wangzh.vultr.presenter.i.MainViewI;

/**
 * Created by WangZH on 2017/4/24.
 */

public class MainPresenter extends BasePresenter<MainViewI> {

    public MainPresenter(MainViewI mainViewI){
        attachView(mainViewI);
    }

    public void getAccountInfoByKey(String apiKey){
        addSubscribe(mApiWithJson.getAccountInfo(apiKey), new CallBack<AccountInfoDTO>() {
            @Override
            public void onSuccess(AccountInfoDTO o) {
                view.onCheckApiKeySuccess(o);
            }

            @Override
            public void onFail(HttpErrorVo msg) {
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }

    public void getAuthInfo(String apiKey){
        addSubscribe(mApiWithJson.getAuthInfo(apiKey), new CallBack<AuthInfoDTO>() {
            @Override
            public void onSuccess(AuthInfoDTO o) {
                view.onGetAuthInfoSuccess(o);
            }

            @Override
            public void onFail(HttpErrorVo msg) {
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }

    public void getAppList(){
        addSubscribe(mApiWithJson.getAppList(),new CallBack<Object>(){

            @Override
            public void onSuccess(Object o) {
                view.onGetSupportedAppSuccess(null);
            }

            @Override
            public void onFail(HttpErrorVo msg) {
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }
}
