package com.wangzh.vultr.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wangzh.vultr.R;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.utils.CommonItemDecoration;

import butterknife.BindView;

/**
 * Created by 99210 on 2017/5/13.
 */

public class MineAppFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.recycl_mine_app)
    RecyclerView mRvMineApp;

    @Override
    int getRootView() {
        return R.layout.fragment_mineapp;
    }

    @Override
    void initView() {
        mSrl.setOnRefreshListener(this);
        mSrl.setColorSchemeColors(ConstValues.COLOR_SWIPEREFRESH_1,ConstValues.COLOR_SWIPEREFRESH_2,ConstValues.COLOR_SWIPEREFRESH_3);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRvMineApp.setLayoutManager(manager);
        mRvMineApp.setHasFixedSize(true);
        mRvMineApp.addItemDecoration(new CommonItemDecoration(20,40,20,0));
    }

    @Override
    public void onRefresh() {

    }
}
