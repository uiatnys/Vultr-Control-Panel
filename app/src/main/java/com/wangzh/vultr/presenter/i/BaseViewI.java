package com.wangzh.vultr.presenter.i;

import com.wangzh.vultr.model.entity.HttpErrorVo;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface BaseViewI {

    void getDataFail(HttpErrorVo failMsg);
    void onFinish();
}
