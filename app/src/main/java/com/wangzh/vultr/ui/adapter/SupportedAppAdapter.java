package com.wangzh.vultr.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.others.utils.StringUtil;

import java.util.List;

/**
 * Created by WangZH on 2017/5/2.
 */

public class SupportedAppAdapter extends BaseQuickAdapter<SupportedAppVO, BaseViewHolder> {

    public SupportedAppAdapter(@Nullable List<SupportedAppVO> data) {
        super(R.layout.item_supportedapp,data);
    }

    @Override
    public void setNewData(@Nullable List<SupportedAppVO> data) {
        super.setNewData(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SupportedAppVO item) {
        helper.setText(R.id.tv_name,StringUtil.replaceNull(item.getName()))
                .setText(R.id.tv_surcharge,StringUtil.replaceNull(item.getSurcharge()));
    }
}
