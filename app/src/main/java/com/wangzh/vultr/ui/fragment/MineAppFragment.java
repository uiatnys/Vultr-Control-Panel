package com.wangzh.vultr.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzh.vultr.R;
import com.wangzh.vultr.app.MainApplication;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.utils.CommonItemDecoration;
import com.wangzh.vultr.ui.MainActivity;
import com.wangzh.vultr.ui.adapter.MineAppAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 99210 on 2017/5/13.
 */

public class MineAppFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.recycl_mine_app)
    RecyclerView mRvMineApp;

    private MineAppAdapter mMineAppAdapter;
    private List<MineVpsDataVO> mMineVpsDataVOs;

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
        mMineAppAdapter = new MineAppAdapter(null);
        mRvMineApp.setAdapter(mMineAppAdapter);
        mMineAppAdapter.setEmptyView(R.layout.layout_emptyview, (ViewGroup) mRvMineApp.getParent());
    }

    @Override
    public void setError() {
        mMineAppAdapter.setEmptyView(R.layout.layout_errorview, (ViewGroup) mRvMineApp.getParent());
    }

    public void setRefreshFinished(){
        if (mSrl.isRefreshing()){
            mSrl.setRefreshing(false);
        }
    }

    public void setData(List<MineVpsDataVO> mineAppLists){
        this.mMineVpsDataVOs = mineAppLists;
        mMineAppAdapter.setNewData(mineAppLists);
        setRefreshFinished();
        mMineAppAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
    }

    @Override
    public void onRefresh() {
        ((MainActivity)getActivity()).getMainPresenter().getMineVpsData(MainApplication.getSpUtils().getString(SPConst.SP_APIKEY));
    }
}
