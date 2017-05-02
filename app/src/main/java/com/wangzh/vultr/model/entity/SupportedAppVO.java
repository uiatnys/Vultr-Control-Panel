package com.wangzh.vultr.model.entity;

/**
 * Created by WangZH on 2017/4/28.
 */

public class SupportedAppVO {

    public static final String VALUE_APPID = "APPID";
    public static final String VALUE_NAME = "name";
    public static final String VALUE_SHORT_NAME = "short_name";
    public static final String VALUE_DEPLOY_NAME = "deploy_name";
    public static final String VALUE_SURCHARGE = "surcharge";

    /**
     * APPID : 1
     * name : LEMP
     * short_name : lemp
     * deploy_name : LEMP on CentOS 6 x64
     * surcharge : 0  附加费
     */

    private String APPID;
    private String name;
    private String short_name;
    private String deploy_name;
    private String surcharge;

    public String getAPPID() {
        return APPID;
    }

    public void setAPPID(String APPID) {
        this.APPID = APPID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getDeploy_name() {
        return deploy_name;
    }

    public void setDeploy_name(String deploy_name) {
        this.deploy_name = deploy_name;
    }

    public String getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(String surcharge) {
        this.surcharge = surcharge;
    }
}
