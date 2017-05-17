package com.wangzh.vultr.ui.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
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
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        this.mActivity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

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
    public abstract void setError();
}
