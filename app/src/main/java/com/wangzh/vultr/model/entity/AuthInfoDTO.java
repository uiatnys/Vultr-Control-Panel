package com.wangzh.vultr.model.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WangZH on 2017/4/27.
 */

public class AuthInfoDTO implements Serializable{

    private static final long serialVersionUID = 7526471295624376147L;
    /**
     * acls : ["root","manage_users","subscriptions","billing","support","provisioning","dns","abuse","upgrade","firewall"]
     * email : wangzhenhaois@gmail.com
     * name : 祯豪 王
     */

    private String email;
    private String name;
    private List<String> acls;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAcls() {
        return acls;
    }

    public void setAcls(List<String> acls) {
        this.acls = acls;
    }
}
