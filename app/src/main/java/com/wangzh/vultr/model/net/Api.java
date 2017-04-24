package com.wangzh.vultr.model.net;

import com.wangzh.vultr.model.entity.AccountInfo;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface Api {

    /**
     * 获取账户费用信息
     * @return
     */
    @GET("v1/account/info")
    Observable<AccountInfo> getAccountInfo(@Query("api_key") String apiKey);

    @GET(".")
    Observable<Response> checkVlutrServer();
}
