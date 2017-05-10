package com.wangzh.vultr.ui;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.others.utils.StringUtil;
import com.wangzh.vultr.presenter.BasePresenter;
import com.wangzh.vultr.ui.base.BasePresenterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by WangZH on 2017/4/28.
 */

public class AccountActivity extends BasePresenterActivity {

    @BindView(R.id.ll_root)
    LinearLayout mLlRoot;
    @BindView(R.id.ll_container)
    LinearLayout mLlContent;

    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_balance)
    TextView mTvBalance;
    @BindView(R.id.tv_pending_charges)
    TextView mTvPendingCharges;
    @BindView(R.id.tv_lastpay_date)
    TextView mTvLastpayDate;
    @BindView(R.id.tv_lastpay_amount)
    TextView mTvLastpayAmount;

    private AccountInfoDTO mAccountInfoDTO;
    private AuthInfoDTO mAuthInfoDTO;

    @OnClick({R.id.ibtn_close})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ibtn_close:
                startAlphaAnimation(false);
                finish();
                overridePendingTransition(0,0);
                break;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        startAlphaAnimation(true);
        mAccountInfoDTO = (AccountInfoDTO) getIntent().getSerializableExtra("accountInfo");
        mAuthInfoDTO = (AuthInfoDTO) getIntent().getSerializableExtra("authInfo");
        initData();
    }

    private void startAlphaAnimation(boolean isEnter){
        ValueAnimator valueAnimator;
        if (isEnter){
           valueAnimator = ValueAnimator.ofInt(150,80);
        }else {
            valueAnimator = ValueAnimator.ofInt(80,0);
        }
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                mLlRoot.setBackgroundColor(Color.argb((Integer) valueAnimator.getAnimatedValue(),0,0,0));
            }
        });
        valueAnimator.start();
    }

    private void initData(){
        mTvName.setText(StringUtil.replaceNull(mAuthInfoDTO.getName()));
        mTvEmail.setText(StringUtil.replaceNull(mAuthInfoDTO.getEmail()));
        mTvBalance.setText(StringUtil.replaceNull(mAccountInfoDTO.getBalance())+"$");
        mTvLastpayAmount.setText(StringUtil.replaceNull(mAccountInfoDTO.getLast_payment_amount())+"$");
        mTvLastpayDate.setText(StringUtil.replaceNull(mAccountInfoDTO.getLast_payment_date()));
        mTvPendingCharges.setText(StringUtil.replaceNull(mAccountInfoDTO.getPending_charges())+"$");
    }

    @Override
    protected BasePresenter createPresenter() {
        return null;
    }

    @Override
    public void getDataFail(HttpErrorVo failMsg) {

    }
}
