package com.wangzh.vultr.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import com.wangzh.vultr.R;

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
        mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return false;
            }
        });
    }
}
