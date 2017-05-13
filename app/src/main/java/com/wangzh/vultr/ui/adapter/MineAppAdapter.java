package com.wangzh.vultr.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangzh.vultr.model.entity.MineVpsDataVO;

import java.util.List;

/**
 * Created by 99210 on 2017/5/13.
 */

public class MineAppAdapter extends BaseQuickAdapter<MineVpsDataVO,BaseViewHolder> {
    public MineAppAdapter(@Nullable List<MineVpsDataVO> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MineVpsDataVO item) {

    }
}
