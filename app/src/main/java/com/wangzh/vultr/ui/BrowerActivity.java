package com.wangzh.vultr.ui;

import android.view.WindowManager;

import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;
import com.wangzh.vultr.R;
import com.wangzh.vultr.model.entity.HttpErrorVo;
import com.wangzh.vultr.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.wangzh.vultr.others.constants.ConstValues.BROWER_URL;

/**
 * Created by WangZH on 2017/6/27.
 */

public class BrowerActivity extends BaseActivity {

    @BindView(R.id.webview)
    WebView mWebView;

    String url = "";

    @Override
    protected void init() {
        getSupportActionBar().hide();
        setContentView(R.layout.activity_brower);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(BROWER_URL);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        mWebView.loadUrl(url);
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings webSettings=mWebView.getSettings();
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(false);
        webSettings.setAppCacheEnabled(true);
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void getDataFail(HttpErrorVo failMsg) {

    }
}
