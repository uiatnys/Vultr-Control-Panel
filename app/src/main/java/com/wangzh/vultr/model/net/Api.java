package com.wangzh.vultr.model.net;

import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface Api {

    //url前缀
    static final String URL_BASE = "https://api.vultr.com";

    /**
     * 获取账户费用信息
     * @return
     */
    @GET("v1/account/info")
    Observable<AccountInfoDTO> getAccountInfo(@Query("api_key") String apiKey);

    @GET("/v1/auth/info")
    Observable<AuthInfoDTO> getAuthInfo(@Query("api_key") String apikey);
}
