package com.wangzh.vultr.ui.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by WangZH on 2017/5/2.
 */

public abstract class BaseFragment extends Fragment {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getRootView(),null);
        ButterKnife.bind(this,mRootView);
        initView();
        return mRootView;
    }

    abstract int getRootView();
    abstract void initView();
}
