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

    public static String replactNullToZero(String value){
        if (TextUtils.isEmpty(value)){
            return "0";
        }
        return value;
    }

    public static String stringToStar(String value){
        if (TextUtils.isEmpty(value)){
            return "";
        }
        int length = value.length();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i=0;i<length;i++){
            stringBuilder.append("*");
        }
        return stringBuilder.toString();
    }
}
