package com.wangzh.vultr.presenter;

import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.model.net.CallBack;
import com.wangzh.vultr.presenter.i.SplashViewI;

/**
 * Created by WangZH on 2017/4/24.
 */

public class SplashPresenter extends BasePresenter<SplashViewI> {

    public SplashPresenter(SplashViewI splashViewI){
        attachView(splashViewI);
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
}
