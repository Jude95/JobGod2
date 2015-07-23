package com.ant.jobgod.jobgod.model.bean;

import java.io.Serializable;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class UserAccountData extends AccountData implements Serializable{
    private String tel;
    private String realname;
    private UserDetail detail;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }


}
