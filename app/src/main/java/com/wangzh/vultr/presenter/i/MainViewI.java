package com.wangzh.vultr.presenter.i;

import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.SupportedAppVO;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface MainViewI extends BaseViewI {

    void onCheckApiKeySuccess(AccountInfoDTO dto);
    void onGetAuthInfoSuccess(AuthInfoDTO dto);
    void onGetSupportedAppSuccess(SupportedAppVO vo);
}
