package com.wangzh.vultr.presenter.i;

import com.wangzh.vultr.model.entity.AccountInfoDTO;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface MainViewI extends BaseViewI {

    void onCheckApiKeySuccess(AccountInfoDTO dto);
}
