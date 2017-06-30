package com.wangzh.vultr.ui;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.wangzh.vultr.R;
import com.wangzh.vultr.app.MainApplication;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.others.listener.OnBackPressedListsner;
import com.wangzh.vultr.others.utils.HttpResponseUtil;
import com.wangzh.vultr.presenter.BasePresenter;
import com.wangzh.vultr.presenter.MainPresenter;
import com.wangzh.vultr.presenter.RequestType;
import com.wangzh.vultr.presenter.i.MainViewI;
import com.wangzh.vultr.ui.base.BasePresenterActivity;
import com.wangzh.vultr.ui.dialog.AlertDialogBuilder;
import com.wangzh.vultr.ui.fragment.MineAppFragment;
import com.wangzh.vultr.ui.fragment.SupportedAppFragment;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;

/**
 * Created by WangZH on 2017/4/26.
 */

public abstract class BaseMainActivity extends BasePresenterActivity
        implements NavigationView.OnNavigationItemSelectedListener,MainViewI,RequestType{

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.fl_main_content_container)
    FrameLayout mFrameContainer;

    protected String API_KEY ="";

    protected AlertDialogBuilder mAlertDialogBuilder;
    protected AlertDialog mAlertDialog;
    protected MainPresenter mMainPresenter;
    protected AccountInfoDTO mAccountInfoDTO;
    protected AuthInfoDTO mAuthInfoDTO;
    protected Intent mIntent;
    protected Menu mMenu;

    protected SupportedAppFragment supportedAppFragment;
    protected MineAppFragment mineAppFragment;

    protected OnBackPressedListsner mOnBackPressedListsner;

    protected abstract void initContent();

    @Override
    protected void initView() {
        initContent();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MainPresenter(this,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.mMenu = menu;
        getMenuInflater().inflate(R.menu.menu_mineapp, menu);
        menu.clear();
        return true;
    }

    public MainPresenter getMainPresenter(){
        return mMainPresenter;
    }

    protected void setBackPressedListener(OnBackPressedListsner listener){
        this.mOnBackPressedListsner = listener;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_console:
                Intent intent = new Intent();
                intent.setClass(mActivity,BrowerActivity.class);
                intent.putExtra(ConstValues.BROWER_URL,mineAppFragment.getCurrentVo().getKvm_url());
                startActivity(intent);
                return true;
            case R.id.action_stop:
                mMainPresenter.stopMineVps(BaseMainActivity.this,mineAppFragment.getCurrentVo().getSUBID(),getApiKey());
                return true;
            case R.id.action_restart:
                mMainPresenter.restartMineVps(BaseMainActivity.this,mineAppFragment.getCurrentVo().getSUBID(),getApiKey());
                return true;
            case R.id.action_reinstall:
                new AlertDialogBuilder(new AlertDialogBuilder.AlertDialogOkClickListener() {
                    @Override
                    public void onOkBtnClicked(String value) {
                        mMainPresenter.reinstallMineVps(BaseMainActivity.this,mineAppFragment.getCurrentVo().getSUBID(),getApiKey());
                    }
                }).createDialogStyleB(mActivity,"Are Sure Reinstall OS?","").show();
                return true;
            case R.id.action_destroy:
                new AlertDialogBuilder(new AlertDialogBuilder.AlertDialogOkClickListener() {
                    @Override
                    public void onOkBtnClicked(String value) {
                        mMainPresenter.destroyMineVps(BaseMainActivity.this,mineAppFragment.getCurrentVo().getSUBID(),getApiKey());
                    }
                }).createDialogStyleB(mActivity,"Are Sure Destroy OS?","").show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void inflateMenuOption(int menuid){
        clearMenu();
        getMenuInflater().inflate(menuid,mMenu);
    }

    public void clearMenu(){
        mMenu.clear();
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.nav_account:
                String title = (String) toolbar.getTitle();
                toolbar.setTitle("Account");
                mIntent = new Intent(mActivity,AccountActivity.class);
                mIntent.putExtra("accountInfo",mAccountInfoDTO).putExtra("authInfo",mAuthInfoDTO);
                mIntent.putExtra("title",title);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivityForResult(mIntent,ConstValues.REQUEST_CODE_FROM_ACCOUNT);
                break;
            case  R.id.nav_supported:
                toolbar.setTitle("Supported App");
                supportedAppFragment = new SupportedAppFragment();
                showFragment(mFrameContainer.getId(),supportedAppFragment, ConstValues.FRAGMENT_SUPPORTEDAPP);
                mMainPresenter.getAppList();
                break;
            case  R.id.nav_mine:
                toolbar.setTitle("Mine App");
                mineAppFragment = new MineAppFragment();
                showFragment(mFrameContainer.getId(),mineAppFragment,ConstValues.FRAGMENT_MINEAPP);
                setBackPressedListener(mineAppFragment.getListsner());
                mMainPresenter.getMineVpsData(getApiKey());
                break;
            case  R.id.nav_manage:
                break;
            case  R.id.nav_feedback:
                break;
            case  R.id.nav_send:
                break;
            default:break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ConstValues.REQUEST_CODE_FROM_ACCOUNT && resultCode == ConstValues.RESULT_CODE_FROM_ACCOUNT){
            toolbar.setTitle(data.getStringExtra("title"));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public String getApiKey(){
        return  MainApplication.getSpUtils().getString(SPConst.SP_APIKEY,API_KEY);
    }

    @Override
    public void onBackPressed() {
        if (mOnBackPressedListsner!=null && !mOnBackPressedListsner.onBackButtonPressed()){

            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer == null) {
                super.onBackPressed();
            }
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
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
            case REQUESTTYPE_STOPSERVER:
                Toasty.error(this,"Stop Service error!",Toast.LENGTH_LONG,true).show();
                break;
            case REQUESTTYPE_RESTARTSERVER:
                Toasty.error(this,"Restart Service error!",Toast.LENGTH_LONG,true).show();
                break;
            case REQUESTTRPE_REINSTALLSERVER:
                Toasty.error(this,"Reinstall Service error!",Toast.LENGTH_LONG,true).show();
                break;
            case REQUESTTYPE_DESTROYSERVER:
                Toasty.error(this,"Destroy Service error!",Toast.LENGTH_LONG,true).show();
                break;
            case REQUESTTYPE_GETAUTHINFO:
                break;
        }
        Toasty.warning(this, failMsg.getMessage().contains(":")
                ?failMsg.getMessage().substring(failMsg.getMessage().indexOf(":")+1)
                :failMsg.getMessage(), Toast.LENGTH_LONG, true).show();
    }
}
