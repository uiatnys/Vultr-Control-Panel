package com.wangzh.vultr.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.ui.adapter.SupportedAppAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WangZH on 2017/5/2.
 */

public class SupportedAppFragment extends BaseFragment {

    @BindView(R.id.recycl_supported_app)
    RecyclerView mRv;

    private View mRootView;
    private SupportedAppAdapter mSupportedAppAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_common_ll,null);
        ButterKnife.bind(mRootView,getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(manager);
        mRv.setAdapter(mSupportedAppAdapter);
        return mRootView;
    }

    public void setSupportedAppData(List<SupportedAppVO> data){
        mSupportedAppAdapter = new SupportedAppAdapter(R.layout.item_supportedapp,data);
    }
}
