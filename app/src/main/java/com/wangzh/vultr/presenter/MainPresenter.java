package com.wangzh.vultr.presenter;

        import com.wangzh.vultr.model.entity.AccountInfoDTO;
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
        addSubscribe(mApi.getAccountInfo(apiKey), new CallBack<AccountInfoDTO>() {
            @Override
            public void onSuccess(AccountInfoDTO o) {
                view.onCheckApiKeySuccess(o);
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
