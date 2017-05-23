package com.wangzh.vultr.ui.fragment;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wangzh.vultr.R;
import com.wangzh.vultr.app.MainApplication;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.listener.OnBackPressedListsner;
import com.wangzh.vultr.others.utils.CommonItemDecoration;
import com.wangzh.vultr.others.utils.StringUtil;
import com.wangzh.vultr.others.widget.AnimatedSvgView.AnimatedSvgView;
import com.wangzh.vultr.ui.MainActivity;
import com.wangzh.vultr.ui.adapter.MineAppAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    private ViewStubHolder mViewStubHolder;

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
            mViewStubHolder = new ViewStubHolder(mRootView);
        }
        mScrollView = (ScrollView) mRootView.findViewById(R.id.ll_mineapp_container);
        mViewStub.setVisibility(View.VISIBLE);
        mScrollView.setVisibility(View.VISIBLE);
        mScrollView.scrollTo(0,0);
        mSrl.setVisibility(View.GONE);
        initDetailData(mMineVpsDataVOs.get(position));
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

    private void initDetailData(final MineVpsDataVO vo){
        mViewStubHolder.mTvLabel.setText(StringUtil.replaceNull(vo.getLabel()));
        mViewStubHolder.mTvTag.setText(StringUtil.replaceNull(vo.getTag()));
        mViewStubHolder.mTvLocation.setText(StringUtil.replaceNull(vo.getLocation()));
        mViewStubHolder.mTvOs.setText(StringUtil.replaceNull(vo.getOs()));
        mViewStubHolder.mTvRam.setText(StringUtil.replaceNull(vo.getRam()));
        mViewStubHolder.mTvDisk.setText(StringUtil.replaceNull(vo.getDisk()));
        mViewStubHolder.mTvCoreCount.setText(StringUtil.replaceNull(vo.getVcpu_count()));
        mViewStubHolder.mTvIp.setText(StringUtil.replaceNull(vo.getMain_ip()));
        mViewStubHolder.mTvSubnet.setText(StringUtil.replaceNull(vo.getNetmask_v4()));
        mViewStubHolder.mSwBackup.setChecked(vo.getAuto_backups().equals("disabled")?false:true);
        mViewStubHolder.mTvStatus.setText(StringUtil.replaceNull(vo.getStatus()));
        mViewStubHolder.mTvServerstate.setText(StringUtil.replaceNull(vo.getServer_state()));
        mViewStubHolder.mTvPowerstatus.setText(StringUtil.replaceNull(vo.getPower_status()));
        mViewStubHolder.mTvUsed.setText(StringUtil.replactNullToZero(vo.getCurrent_bandwidth_gb())+" gb");
        mViewStubHolder.mTvAllowed.setText(StringUtil.replactNullToZero(vo.getAllowed_bandwidth_gb())+" gb");
        mViewStubHolder.mTvCreatedate.setText(StringUtil.replaceNull(vo.getDate_created()));
        mViewStubHolder.mTvCharge.setText(StringUtil.replactNullToZero(vo.getPending_charges()));
        mViewStubHolder.mTvCostmonth.setText(StringUtil.replactNullToZero(vo.getCost_per_month()));
        mViewStubHolder.mTvPwd.setText(StringUtil.stringToStar(vo.getDefault_password()));
        mViewStubHolder.mIvShowPwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mViewStubHolder.mTvPwd.getText().toString().startsWith("*")
                        &&mViewStubHolder.mTvPwd.getText().toString().endsWith("*")){
                    mViewStubHolder.mTvPwd.setText(StringUtil.replaceNull(vo.getDefault_password()));
                }else {
                    mViewStubHolder.mTvPwd.setText(StringUtil.stringToStar(vo.getDefault_password()));
                }
            }
        });
        mViewStubHolder.mSwBackup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    class ViewStubHolder{
        @BindView(R.id.tv_detail_label)
        TextView mTvLabel;
        @BindView(R.id.tv_detail_tag)
        TextView mTvTag;
        @BindView(R.id.tv_detail_location)
        TextView mTvLocation;
        @BindView(R.id.tv_detail_os)
        TextView mTvOs;
        @BindView(R.id.tv_ram)
        TextView mTvRam;
        @BindView(R.id.tv_disk)
        TextView mTvDisk;
        @BindView(R.id.tv_cpucount)
        TextView mTvCoreCount;
        @BindView(R.id.tv_detail_ip)
        TextView mTvIp;
        @BindView(R.id.tv_subnet)
        TextView mTvSubnet;
        @BindView(R.id.switch_backup)
        Switch mSwBackup;
        @BindView(R.id.tv_detail_status)
        TextView mTvStatus;
        @BindView(R.id.tv_serverstate)
        TextView mTvServerstate;
        @BindView(R.id.tv_pwoerstatus)
        TextView mTvPowerstatus;
        @BindView(R.id.tv_used)
        TextView mTvUsed;
        @BindView(R.id.tv_allowed)
        TextView mTvAllowed;
        @BindView(R.id.tv_createddate)
        TextView mTvCreatedate;
        @BindView(R.id.tv_charge)
        TextView mTvCharge;
        @BindView(R.id.tv_monthcost)
        TextView mTvCostmonth;
        @BindView(R.id.tv_password)
        TextView mTvPwd;
        @BindView(R.id.iv_showpwd)
        ImageView mIvShowPwd;

        public ViewStubHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
