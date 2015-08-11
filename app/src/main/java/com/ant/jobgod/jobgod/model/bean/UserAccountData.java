package com.ant.jobgod.jobgod.model.bean;

import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class UserAccountData extends AccountData implements Serializable{

    private String tel;
    private String realName;
    private UserDetail detail;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }


}
