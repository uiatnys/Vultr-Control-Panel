package com.wangzh.vultr.presenter;

import android.util.Log;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.model.net.CallBack;
import com.wangzh.vultr.presenter.i.MainViewI;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZH on 2017/4/24.
 */

public class MainPresenter extends BasePresenter<MainViewI> implements RequestType{

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
                msg.setType(REQUESTTYPE_GETACCOUNTINFOBYKEY);
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
                msg.setType(REQUESTTYPE_GETAUTHINFO);
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
                msg.setType(REQUESTTYPE_GETAPPLIST);
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }

    public void getMineVpsData(String apiKey){
        addSubscribe(mApiWithJson.getMineVpsData(apiKey), new CallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                JSONObject jsonObject = (JSONObject) o;
                List<MineVpsDataVO> mineVpsDataList = new ArrayList<MineVpsDataVO>();
                for (Map.Entry entry : jsonObject.entrySet()){
                    Map entryMap = (Map)entry.getValue();
                    MineVpsDataVO vo = new MineVpsDataVO();
                    vo.setSUBID((String) entryMap.get(MineVpsDataVO.VALUE_SUBID));
                    vo.setOs((String) entryMap.get(MineVpsDataVO.VALUE_OS));
                    vo.setRam((String) entryMap.get(MineVpsDataVO.VALUE_RAM));
                    vo.setDisk((String) entryMap.get(MineVpsDataVO.VALUE_DISK));
                    vo.setMain_ip((String) entryMap.get(MineVpsDataVO.VALUE_MAINIP));
                    vo.setVcpu_count((String) entryMap.get(MineVpsDataVO.VALUE_VCPUCOUNT));
                    vo.setLocation((String) entryMap.get(MineVpsDataVO.VALUE_LOCATION));
                    vo.setDCID((String) entryMap.get(MineVpsDataVO.VALUE_DCID));
                    vo.setDefault_password((String) entryMap.get(MineVpsDataVO.VALUE_DEFAULTPASSWORD));
                    vo.setDate_created((String) entryMap.get(MineVpsDataVO.VALUE_DATECREATED));
                    vo.setPending_charges((String) entryMap.get(MineVpsDataVO.VALUE_PENDINGCHARGES));
                    vo.setStatus((String) entryMap.get(MineVpsDataVO.VALUE_STATUS));
                    vo.setCost_per_month((String) entryMap.get(MineVpsDataVO.VALUE_COSTPERMONTH));
                    BigDecimal bigDecimal = new BigDecimal(entryMap.get(MineVpsDataVO.VALUE_CURRENTBANDWITHGB).toString());
                    vo.setCurrent_bandwidth_gb(bigDecimal.toString());
                    bigDecimal = new BigDecimal(entryMap.get(MineVpsDataVO.VALUE_ALLOWEDBANDWIDTHGB).toString());
                    vo.setAllowed_bandwidth_gb(bigDecimal.toString());
                    vo.setNetmask_v4((String) entryMap.get(MineVpsDataVO.VALUE_NETMASKV4));
                    vo.setGateway_v4((String) entryMap.get(MineVpsDataVO.VALUE_GATEWAYV4));
                    vo.setPower_status((String) entryMap.get(MineVpsDataVO.VALUE_POWERSTATUS));
                    vo.setServer_state((String) entryMap.get(MineVpsDataVO.VALUE_SERVERSTATE));
                    vo.setVPSPLANID((String) entryMap.get(MineVpsDataVO.VALUE_VPSPLANID));
                    vo.setV6_main_ip((String) entryMap.get(MineVpsDataVO.VALUE_MAINIPV6));
                    vo.setV6_network_size((String) entryMap.get(MineVpsDataVO.VALUE_NETWORKSIZEV6));
                    vo.setV6_network((String) entryMap.get(MineVpsDataVO.VALUE_NETWORKV6));
                    vo.setLabel((String) entryMap.get(MineVpsDataVO.VALUE_LABEL));
                    vo.setInternal_ip((String) entryMap.get(MineVpsDataVO.VALUE_INTERNALIP));
                    vo.setKvm_url((String) entryMap.get(MineVpsDataVO.VALUE_KVMURL));
                    vo.setAuto_backups((String) entryMap.get(MineVpsDataVO.VALUE_AUTOBACKUPS));
                    vo.setTag((String) entryMap.get(MineVpsDataVO.VALUE_TAG));
                    vo.setOSID((String) entryMap.get(MineVpsDataVO.VALUE_OSID));
                    vo.setAPPID((String) entryMap.get(MineVpsDataVO.VALUE_APPID));
                    vo.setFIREWALLGROUPID((String) entryMap.get(MineVpsDataVO.VALUE_FIREWALLGROUP));
                    JSONArray array = (JSONArray) entryMap.get(MineVpsDataVO.VALUE_NETWORKS_V6);
                    List<MineVpsDataVO.V6NetworksBean> v6List =  new ArrayList<MineVpsDataVO.V6NetworksBean>();
                    for (int i=0;i<array.size();i++){
                        Map entryMap1 = (Map) array.get(i);
                        MineVpsDataVO.V6NetworksBean vo1 = new MineVpsDataVO.V6NetworksBean();
                        vo1.setV6_main_ip((String) entryMap1.get(MineVpsDataVO.VALUE_MAINIP_V6));
                        vo1.setV6_network_size((String) entryMap1.get(MineVpsDataVO.VALUE_NETWORKSIZE_V6));
                        vo1.setV6_network((String) entryMap1.get(MineVpsDataVO.VALUE_NETWORK_V6));
                        v6List.add(vo1);
                    }
                    vo.setV6_networks(v6List);
                    mineVpsDataList.add(vo);
                }
                view.onGetMineVpsDataSuccess(mineVpsDataList);
            }

            @Override
            public void onFail(HttpErrorVo msg) {
                msg.setType(REQUESTTYPE_GETMINEVPSDATA);
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }

    public void enableBackup(String subid,String apikey,boolean isEnable){

        addSubscribe(isEnable?mApiWithJson.enableBackup(subid, apikey)
                :mApiWithJson.disableBackup(subid, apikey)
                ,new CallBack<Object>() {
            @Override
            public void onSuccess(Object o) {
                Log.e("",o.toString());
            }

            @Override
            public void onFail(HttpErrorVo msg) {
                msg.setType(REQUESTTYPE_OPERATEBACKUP);
                view.getDataFail(msg);
            }

            @Override
            public void onFinish() {
                view.onFinish();
            }
        });
    }
}
