package com.wangzh.vultr.ui;

import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;

import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.AccountInfoDTO;
import com.wangzh.vultr.presenter.MainPresenter;
import com.wangzh.vultr.ui.dialog.AlertDialogBuilder;

import butterknife.ButterKnife;

public class MainActivity extends BaseMainActivity implements AlertDialogBuilder.AlertDialogOkClickListener{

    @Override
    protected void initContent() {
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
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMainPresenter = (MainPresenter) presenter;
        mAlertDialogBuilder.createDialogStyleA(this,"ApiKey Check", R.layout.edt_dialoginput).show();
    }

    @Override
    public void getDataFail(String failMsg) {

    }

    @Override
    public void onOkBtnClicked(String value) {
        mMainPresenter.getAccountInfoByKey(value);
    }

    @Override
    public void onCheckApiKeySuccess(AccountInfoDTO dto) {

    }
}
