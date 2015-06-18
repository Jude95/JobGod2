package com.ant.jobgod.jobgod.model;

import com.ant.jobgod.jobgod.model.bean.LoginInfo;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/6/12.
 */
public class AccountModel extends AbsModel{
    public static final String ACCOUNTFILE = "account";
    public static AccountModel getInstance() {
        return getInstance(AccountModel.class);
    }
    public LoginInfo account;

    public LoginInfo getAccount() {
        if (account == null)
            account = (LoginInfo) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
        return account;
    }
    public void setAccount(LoginInfo account){
        this.account = account;
        Utils.writeObjectToFile(account,FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
    }

}
