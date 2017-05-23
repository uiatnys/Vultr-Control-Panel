package com.wangzh.vultr.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ScrollView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzh.vultr.R;
import com.wangzh.vultr.app.MainApplication;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.listener.OnBackPressedListsner;
import com.wangzh.vultr.others.utils.CommonItemDecoration;
import com.wangzh.vultr.others.widget.AnimatedSvgView.AnimatedSvgView;
import com.wangzh.vultr.ui.MainActivity;
import com.wangzh.vultr.ui.adapter.MineAppAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by 99210 on 2017/5/13.
 */

public class MineAppFragment extends BaseFragment
        implements SwipeRefreshLayout.OnRefreshListener
        ,AnimatedSvgView.OnStateChangeListener
        ,BaseQuickAdapter.OnItemClickListener,OnBackPressedListsner {

    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrl;
    @BindView(R.id.recycl_mine_app)
    RecyclerView mRvMineApp;
    @BindView(R.id.vs_detail)
    ViewStub mViewStub;
    ScrollView mScrollView;

    private AnimatedSvgView mAnimatedSvgView;
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
        RecyclerView.LayoutManager manager = new LinearLayoutManager(mActivity);
        mRvMineApp.setLayoutManager(manager);
        mRvMineApp.setHasFixedSize(true);
        mRvMineApp.addItemDecoration(new CommonItemDecoration(20,40,20,0));
        mMineAppAdapter = new MineAppAdapter(null);
        mRvMineApp.setAdapter(mMineAppAdapter);
        mMineAppAdapter.setOnItemClickListener(this);
        mMineAppAdapter.setEmptyView(R.layout.layout_emptyview, (ViewGroup) mRvMineApp.getParent());
        try {
            mAnimatedSvgView = (AnimatedSvgView)mMineAppAdapter.getEmptyView().findViewById(R.id.svg_load);
            mAnimatedSvgView.start();
            mAnimatedSvgView.setOnStateChangeListener(this);
        }catch (Exception e){
            Log.e("AnimatedSvgVie",e.getMessage());
        }
    }

    public OnBackPressedListsner getListsner(){
        return this;
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
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mMineAppAdapter.setNewData(mMineVpsDataVOs);
                setRefreshFinished();
                mMineAppAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            }
        },ConstValues.REQUEST_NET_DELAY);
    }

    @Override
    public void onRefresh() {
        ((MainActivity)mActivity).getMainPresenter().getMineVpsData(MainApplication.getSpUtils().getString(SPConst.SP_APIKEY));
    }

    @Override
    public void onStateChange(@AnimatedSvgView.State int state) {
        if (state == AnimatedSvgView.STATE_FINISHED && mAnimatedSvgView != null) {
            mAnimatedSvgView.start();
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mViewStub.getParent() != null){
            mViewStub.inflate();
        }
        mViewStub.setVisibility(View.VISIBLE);
        mScrollView = (ScrollView) mRootView.findViewById(R.id.ll_mineapp_container);
        mScrollView.setVisibility(View.VISIBLE);
        mScrollView.scrollTo(0,0);
        mSrl.setVisibility(View.GONE);
    }

    @Override
    public boolean onBackButtonPressed() {
        if(mScrollView == null){
            return false;
        }
        if (mScrollView.getVisibility() == View.VISIBLE){
            mViewStub.setVisibility(View.GONE);
            mSrl.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }
}
