package com.wangzh.vultr.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.wangzh.vultr.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WangZH on 2017/5/2.
 */

public class SupportedAppFragment extends Fragment {

    @BindView(R.id.ll_frag_container)
    LinearLayout mLlContainer;

    private View mRootView;
    private RecyclerView mRv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_common_ll,null);
        ButterKnife.bind(mRootView);
        mRv = (RecyclerView) getResources().getLayout(R.layout.recyclerview_supportedapp);
        mLlContainer.addView(mRv);
        return mRootView;
    }
}
