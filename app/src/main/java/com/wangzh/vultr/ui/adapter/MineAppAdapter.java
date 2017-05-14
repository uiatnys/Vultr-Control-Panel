package com.wangzh.vultr.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.others.utils.StringUtil;

import java.util.List;

/**
 * Created by 99210 on 2017/5/13.
 */

public class MineAppAdapter extends BaseQuickAdapter<MineVpsDataVO,BaseViewHolder> {
    public MineAppAdapter(@Nullable List<MineVpsDataVO> data) {
        super(R.layout.item_mineapp,data);
    }

    @Override
    public void setNewData(@Nullable List<MineVpsDataVO> data) {
        super.setNewData(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineVpsDataVO item) {
        helper.setText(R.id.tv_label, StringUtil.replaceNull(item.getLabel()))
                .setText(R.id.tv_location,StringUtil.replaceNull(item.getLocation()))
                .setText(R.id.tv_os,StringUtil.replaceNull(item.getOs()))
                .setText(R.id.tv_ip,StringUtil.replaceNull(item.getMain_ip()));
    }
}
