package com.wangzh.vultr.presenter.i;

import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.model.entity.SupportedAppVO;

import java.util.List;

/**
 * Created by WangZH on 2017/4/24.
 */

public interface MainViewI extends BaseViewI {

    void onCheckApiKeySuccess(AccountInfoDTO dto);
    void onGetAuthInfoSuccess(AuthInfoDTO dto);
    void onGetSupportedAppSuccess(List<SupportedAppVO> supportedLists);
    void onGetMineVpsDataSuccess(List<MineVpsDataVO> mineVpsDataVOList);
}
