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
import android.widget.FrameLayout;
import android.widget.TextView;

import com.wangzh.vultr.R;
import com.wangzh.vultr.app.MainApplication;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.model.entity.AuthInfoDTO;
import com.wangzh.vultr.others.constants.ConstValues;
import com.wangzh.vultr.others.constants.SPConst;
import com.wangzh.vultr.presenter.BasePresenter;
import com.wangzh.vultr.presenter.MainPresenter;
import com.wangzh.vultr.presenter.RequestType;
import com.wangzh.vultr.presenter.i.MainViewI;
import com.wangzh.vultr.ui.base.BasePresenterActivity;
import com.wangzh.vultr.ui.dialog.AlertDialogBuilder;
import com.wangzh.vultr.ui.fragment.MineAppFragment;
import com.wangzh.vultr.ui.fragment.SupportedAppFragment;

import butterknife.BindView;

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

    protected AlertDialogBuilder mAlertDialogBuilder;
    protected AlertDialog mAlertDialog;
    protected MainPresenter mMainPresenter;
    protected AccountInfoDTO mAccountInfoDTO;
    protected AuthInfoDTO mAuthInfoDTO;
    protected Intent mIntent;

    protected SupportedAppFragment supportedAppFragment;
    protected MineAppFragment mineAppFragment;


    protected abstract void initContent();

    @Override
    protected void initView() {
        initContent();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public MainPresenter getMainPresenter(){
        return mMainPresenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case  R.id.nav_account:
                mIntent = new Intent(mActivity,AccountActivity.class);
                mIntent.putExtra("accountInfo",mAccountInfoDTO).putExtra("authInfo",mAuthInfoDTO);
                mIntent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(mIntent);
                break;
            case  R.id.nav_supported:
                supportedAppFragment = new SupportedAppFragment();
                showFragment(mFrameContainer.getId(),supportedAppFragment, ConstValues.FRAGMENT_SUPPORTEDAPP);
                mMainPresenter.getAppList();
                break;
            case  R.id.nav_mine:
                mineAppFragment = new MineAppFragment();
                showFragment(mFrameContainer.getId(),mineAppFragment,ConstValues.FRAGMENT_MINEAPP);
                mMainPresenter.getMineVpsData(MainApplication.getSpUtils().getString(SPConst.SP_APIKEY));
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
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer == null){
            super.onBackPressed();
        }
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
