package com.wangzh.vultr.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wangzh.vultr.R;
import com.wangzh.vultr.app.MainApplication;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.model.entity.MineVpsDataVO;
import com.wangzh.vultr.model.entity.SupportedAppVO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.utils.HttpResponseUtil;
import com.wangzh.vultr.others.utils.StringUtil;
import com.wangzh.vultr.presenter.MainPresenter;
import com.wangzh.vultr.ui.dialog.AlertDialogBuilder;
import com.wangzh.vultr.ui.fragment.MineAppFragment;

import java.util.List;

import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

public class MainActivity extends BaseMainActivity implements AlertDialogBuilder.AlertDialogOkClickListener{

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
        mineAppFragment = new MineAppFragment();
        showFragment(mFrameContainer.getId(),mineAppFragment,ConstValues.FRAGMENT_MINEAPP);
        setBackPressedListener(mineAppFragment.getListsner());
        if (getIntent().getFlags() == 0){
            mAlertDialog = mAlertDialogBuilder.createDialogStyleA(this,"ApiKey Check", R.layout.edt_dialoginput);
            mAlertDialog.show();
        }else {
            this.mAccountInfoDTO = (AccountInfoDTO) getIntent().getSerializableExtra("dto");
            getMineVps();
            getAuthInfo();
        }
    }

    @Override
    public void getDataFail(HttpErrorVo failMsg) {
        switch (failMsg.getType()){
            case REQUESTTYPE_GETACCOUNTINFOBYKEY:
                MainApplication.getSpUtils().put(SPConst.SP_APIKEY,"");
                if (HttpResponseUtil.getCodeMap().containsKey(failMsg.getCode())){
                    ((EditText)mAlertDialog.findViewById(R.id.edt_input)).setError("StatusCode-"+failMsg.getCode()+"-Please Check Apikey Correct");
                }else {
                    ((EditText)mAlertDialog.findViewById(R.id.edt_input)).setError("Some Wrong,Please Check Your Key Or Net Connection!");
                }
                break;
            case REQUESTTYPE_GETAPPLIST:
                supportedAppFragment.setError();
                break;
            case REQUESTTYPE_GETMINEVPSDATA:
                break;
            case REQUESTTYPE_OPERATEBACKUP:
                Toasty.error(this, "Operate Backup Failed!", Toast.LENGTH_LONG, true).show();
                onOperateBackupSuccess(false);
                return;
        }
        Toasty.warning(this, failMsg.getMessage().contains(":")
                ?failMsg.getMessage().substring(failMsg.getMessage().indexOf(":")+1)
                :failMsg.getMessage(), Toast.LENGTH_LONG, true).show();
    }

    private void getAuthInfo(){
     mMainPresenter.getAuthInfo(MainApplication.getSpUtils().getString(SPConst.SP_APIKEY));
    }

    private void getMineVps(){
        mMainPresenter.getMineVpsData(MainApplication.getSpUtils().getString(SPConst.SP_APIKEY));
    }

    @Override
    public void onOkBtnClicked(String value) {
        mMainPresenter.getAccountInfoByKey(value);
    }

    @Override
    public void onCheckApiKeySuccess(AccountInfoDTO dto) {
        mAlertDialog.dismiss();
        MainApplication.getSpUtils().put(SPConst.SP_APIKEY,API_KEY);
        this.mAccountInfoDTO = dto;
        getMineVps();
        getAuthInfo();
    }

    @Override
    public void onGetAuthInfoSuccess(AuthInfoDTO dto) {
        this.mAuthInfoDTO = dto;
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_header_name)).setText(StringUtil.replaceNull(mAuthInfoDTO.getName()));
        ((TextView)navigationView.getHeaderView(0).findViewById(R.id.tv_header_email)).setText(StringUtil.replaceNull(mAuthInfoDTO.getEmail()));
    }

    @Override
    public void onGetSupportedAppSuccess(List<SupportedAppVO> supportedLists) {
        supportedAppFragment.setData(supportedLists);
    }

    @Override
    public void onGetMineVpsDataSuccess(List<MineVpsDataVO> mineVpsDataVOList) {
        mineAppFragment.setData(mineVpsDataVOList);
    }

    @Override
    public void onOperateBackupSuccess(boolean result) {
        //TODO 应该是vultr接口原因，启用与禁用备份均返回403
        if (mineAppFragment.isVisible() && !result){
            mineAppFragment.resetBackupSwitch();
        }
    }
}
