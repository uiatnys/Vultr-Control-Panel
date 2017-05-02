package com.wangzh.vultr.presenter;

import com.alibaba.fastjson.JSONObject;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.model.net.CallBack;
import com.wangzh.vultr.presenter.i.MainViewI;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                JSONObject jsonObject = (JSONObject) o;
                List<SupportedAppVO> supportedList = new ArrayList<SupportedAppVO>();
                for (Map.Entry entry : jsonObject.entrySet()){
                    Map entryMap = (Map)entry.getValue();
                    SupportedAppVO vo = new SupportedAppVO();
                    vo.setAPPID((String) entryMap.get(SupportedAppVO.VALUE_APPID));
                    vo.setName((String) entryMap.get(SupportedAppVO.VALUE_NAME));
                    vo.setDeploy_name((String) entryMap.get(SupportedAppVO.VALUE_DEPLOY_NAME));
                    vo.setShort_name((String) entryMap.get(SupportedAppVO.VALUE_SHORT_NAME));
                    vo.setSurcharge(entryMap.get(SupportedAppVO.VALUE_SURCHARGE)+"");
                    supportedList.add(vo);
                }
                view.onGetSupportedAppSuccess(supportedList);
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
