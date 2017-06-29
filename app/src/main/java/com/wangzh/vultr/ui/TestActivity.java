package com.wangzh.vultr.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

import com.wangzh.vultr.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by WangZH on 2017/5/17.
 */

public class TestActivity extends Activity {

    @BindView(R.id.edt_input)
    EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);
        ButterKnife.bind(this);
    }



}
