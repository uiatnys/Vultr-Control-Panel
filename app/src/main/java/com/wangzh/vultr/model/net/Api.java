package com.wangzh.vultr.model.net;

import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface Api {

    //url前缀
    String URL_BASE = "https://api.vultr.com";

    /**
     * 获取账户费用信息
     * @return
     */
    @GET("v1/account/info")
    Observable<AccountInfoDTO>  getAccountInfo(@Query("api_key") String apiKey);

    @GET("/v1/auth/info")
    Observable<AuthInfoDTO> getAuthInfo(@Query("api_key") String apikey);

    @GET("/v1/app/list")
    Observable<Object> getAppList();

    @GET("/v1/server/list")
    Observable<Object> getMineVpsData(@Query("api_key") String apikey);

    @FormUrlEncoded
    @POST("/v1/server/backup_enable")
    Observable<Object> enableBackup(@Field("SUBID") String subid,@Header("API-Key") String apikey);

    @FormUrlEncoded
    @POST("/v1/server/backup_disable")
    Observable<Object> disableBackup(@Field("SUBID") String subid,@Header("API-Key") String apikey);

    @FormUrlEncoded
    @POST("/v1/server/halt")
    Observable<Object> stopServer(@Field("SUBID") String subid,@Header("API-Key") String apiley);
}
