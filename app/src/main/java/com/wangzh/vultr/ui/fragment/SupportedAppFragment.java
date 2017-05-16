package com.wangzh.vultr.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.utils.CommonItemDecoration;
import com.wangzh.vultr.others.widget.AnimatedSvgView.AnimatedSvgView;
import com.wangzh.vultr.ui.MainActivity;
import com.wangzh.vultr.ui.adapter.SupportedAppAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by WangZH on 2017/5/2.
 */

public class SupportedAppFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener,AnimatedSvgView.OnStateChangeListener{

    @BindView(R.id.recycl_supported_app)
    RecyclerView mRv;
    @BindView(R.id.srl_list)
    SwipeRefreshLayout mSrl;

    private AnimatedSvgView mAnimatedSvgView;

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
        GridLayoutManager manager = new GridLayoutManager(getActivity(),2);
        mRv.setLayoutManager(manager);
        mRv.setHasFixedSize(true);
        mRv.addItemDecoration(new CommonItemDecoration(20,40,20,0));
        mSupportedAppAdapter = new SupportedAppAdapter(null);
        mRv.setAdapter(mSupportedAppAdapter);
        mSupportedAppAdapter.setEmptyView(R.layout.layout_emptyview, (ViewGroup) mRv.getParent());
        try {
            mAnimatedSvgView = (AnimatedSvgView)mSupportedAppAdapter.getEmptyView().findViewById(R.id.svg_load);
            mAnimatedSvgView.start();
            mAnimatedSvgView.setOnStateChangeListener(this);
        }catch (Exception e){
            Log.e("AnimatedSvgVie",e.getMessage());
        }
    }

    @Override
    public void setError() {
        mSupportedAppAdapter.setEmptyView(R.layout.layout_errorview, (ViewGroup) mRv.getParent());
    }


    public void setData(List<SupportedAppVO> supportedLists){
        this.mSupportedAppVOs = supportedLists;
        //为了显示加载动画，延迟列表显时间
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSupportedAppAdapter.setNewData(mSupportedAppVOs);
                setRefreshFinished();
                mSupportedAppAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
            }
        },1000);
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

    @Override
    public void onStateChange(@AnimatedSvgView.State int state) {
        if (state == AnimatedSvgView.STATE_FINISHED && mAnimatedSvgView != null) {
            mAnimatedSvgView.start();
        }
    }
}
