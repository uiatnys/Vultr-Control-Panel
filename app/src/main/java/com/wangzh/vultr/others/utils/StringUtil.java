package com.wangzh.vultr.others.utils;

import android.text.TextUtils;

/**
 * Created by WangZH on 2017/4/24.
 */

public class StringUtil {

    public static String replaceNull(String value){
        if (TextUtils.isEmpty(value)){
            return "";
        }
        return value;
    }
}
