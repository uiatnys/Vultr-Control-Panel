package com.wangzh.vultr.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.utils.CommonItemDecoration;
import com.wangzh.vultr.ui.MainActivity;
import com.wangzh.vultr.ui.adapter.SupportedAppAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WangZH on 2017/5/2.
 */

public class SupportedAppFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.recycl_supported_app)
    RecyclerView mRv;
    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrl;

    private SupportedAppAdapter mSupportedAppAdapter;
    private List<SupportedAppVO> mSupportedAppVOs;

    @Override
    int getRootView() {
        return R.layout.fragment_supported_app;
    }

    @Override
    void initView() {
        mSrl.setOnRefreshListener(this);
        mSrl.setColorSchemeColors(ConstValues.COLOR_SWIPEREFRESH_1,ConstValues.COLOR_SWIPEREFRESH_2,ConstValues.COLOR_SWIPEREFRESH_3);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(manager);
        mRv.setHasFixedSize(true);
        mRv.addItemDecoration(new CommonItemDecoration(20,40,20,0));
        mSupportedAppAdapter = new SupportedAppAdapter(null);
        mRv.setAdapter(mSupportedAppAdapter);
        mSupportedAppAdapter.setEmptyView(R.layout.layout_emptyview, (ViewGroup) mRv.getParent());
    }

    @Override
    public void setError() {
        mSupportedAppAdapter.setEmptyView(R.layout.layout_errorview, (ViewGroup) mRv.getParent());
    }


    public void setData(List<SupportedAppVO> supportedLists){
        this.mSupportedAppVOs = supportedLists;
        mSupportedAppAdapter.setNewData(mSupportedAppVOs);
        setRefreshFinished();
        mSupportedAppAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }

    public void setRefreshFinished(){
        if (mSrl.isRefreshing()){
            mSrl.setRefreshing(false);
        }
    }

    @Override
    public void onRefresh() {
        ((MainActivity)getActivity()).getMainPresenter().getAppList();
    }
}
