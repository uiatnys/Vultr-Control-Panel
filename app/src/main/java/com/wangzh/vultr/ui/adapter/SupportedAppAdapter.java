package com.wangzh.vultr.ui.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.others.utils.StringUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WangZH on 2017/5/2.
 */

public class SupportedAppAdapter extends BaseQuickAdapter<SupportedAppVO,SupportedAppAdapter.MyViewHolder> {

private List<SupportedAppVO> dataList;

    public SupportedAppAdapter(@LayoutRes int layoutResId, @Nullable List<SupportedAppVO> data) {
        super(layoutResId, data);
    }


    public void setDataList(List<SupportedAppVO> data){
        this.dataList = data;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(MyViewHolder helper, SupportedAppVO item) {
        helper.mTvName.setText(StringUtil.replaceNull(item.getName()));
        helper.mTvSurcharge.setText(StringUtil.replaceNull(item.getSurcharge()));
    }

    class MyViewHolder extends BaseViewHolder{

        @BindView(R.id.tv_name)
        TextView mTvName;
        @BindView(R.id.tv_surcharge)
        TextView mTvSurcharge;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(view);
        }
    }
}
