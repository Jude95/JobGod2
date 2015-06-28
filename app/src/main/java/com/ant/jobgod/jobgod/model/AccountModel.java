package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.ant.jobgod.jobgod.model.bean.AccountInfo;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by Mr.Jude on 2015/6/12.
 * 关于账户的信息。主要用于账户类型：商家／用户，和权限管理。
 */
public class AccountModel extends AbsModel{
    public static final String ACCOUNTFILE = "account";
    public static AccountModel getInstance() {
        return getInstance(AccountModel.class);
    }
    public AccountInfo account;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        account = (AccountInfo) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
        if (account!=null)
            applyToken(account.getTokenApp());
    }

    public boolean isUser(){
        return account.getType()==0;
    }

    public void test(){
        publicEvent("oh fuck");
    }

    public void test2(){
        publicEvent(123);
    }

    public AccountInfo getAccount() {
        return account;
    }
    public void setAccount(AccountInfo account){
        this.account = account;
        Utils.writeObjectToFile(account,FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
        applyToken(account.getTokenApp());
    }

    public void applyToken(String token){
        HashMap<String, String> map = new HashMap();
        map.put("token", token);
        RequestManager.getInstance().setHeader(map);
    }
}
