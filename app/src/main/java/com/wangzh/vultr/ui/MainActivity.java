package com.wangzh.vultr.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.EditText;

import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.utils.HttpResponseUtil;
import com.wangzh.vultr.presenter.MainPresenter;
import com.wangzh.vultr.ui.dialog.AlertDialogBuilder;

import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends BaseMainActivity implements AlertDialogBuilder.AlertDialogOkClickListener{

    private String API_KEY ="";


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void initContent() {
        //activity跳转动画，存在bug
        //setTransition(android.R.transition.fade,android.R.transition.fade,android.R.transition.fade);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        mAlertDialogBuilder = new AlertDialogBuilder(this);
        presenter = createPresenter();
        mMainPresenter = (MainPresenter) presenter;
        if (getIntent().getFlags() == 0){
            mAlertDialog = mAlertDialogBuilder.createDialogStyleA(this,"ApiKey Check", R.layout.edt_dialoginput);
            mAlertDialog.show();
        }else {
            this.mAccountInfoDTO = (AccountInfoDTO) getIntent().getSerializableExtra("dto");
            getAuthInfo();
        }
    }

    @Override
    public void getDataFail(HttpErrorVo failMsg) {
        mSPUtils.put(SPConst.SP_APIKEY,"");
        if (HttpResponseUtil.getCodeMap().containsKey(failMsg.getCode())){
            ((EditText)mAlertDialog.findViewById(R.id.edt_input)).setError("StatusCode-"+failMsg.getCode()+"-Please Check Apikey Correct");
        }else {
            ((EditText)mAlertDialog.findViewById(R.id.edt_input)).setError("Maybe SomeThing Wrong,Please Feedback Me!");
        }
    }

    private void getAuthInfo(){
     mMainPresenter.getAuthInfo(mSPUtils.getString(SPConst.SP_APIKEY));
    }

    @Override
    public void onOkBtnClicked(String value) {
        this.API_KEY = value;
        mMainPresenter.getAccountInfoByKey(value);
    }

    @Override
    public void onCheckApiKeySuccess(AccountInfoDTO dto) {
        mAlertDialog.dismiss();
        mSPUtils.put(SPConst.SP_APIKEY,API_KEY);
        this.mAccountInfoDTO = dto;
        getAuthInfo();
    }

    @Override
    public void onGetAuthInfoSuccess(AuthInfoDTO dto) {
        this.mAuthInfoDTO = dto;
    }

    @Override
    public void onGetSupportedAppSuccess(List<SupportedAppVO> supportedLists) {

    }


}
