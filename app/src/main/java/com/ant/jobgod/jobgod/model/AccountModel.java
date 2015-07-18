package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.AccountInfo;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.tencent.open.utils.Util;

import java.util.HashMap;

/**
 * Created by Mr.Jude on 2015/6/12.
 * 关于账户的信息。主要用于账户类型：商家／用户，和权限管理。
 * 会发送AccountInfo事件
 */
public class AccountModel extends AbsModel{
    private static final String ACCOUNTFILE = "account";
    public static AccountModel getInstance() {
        return getInstance(AccountModel.class);
    }
    public AccountInfo account;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        Utils.Log(Utils.MD5("abc".getBytes()));
        account = (AccountInfo) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
        if (account!=null)
            applyToken(account.getTokenApp());
    }

    public boolean isUser(){
        return account == null?true:account.getType()==0;
    }

    public AccountInfo getAccount() {
        return account;
    }

    public void setAccount(AccountInfo account){
        this.account = account;
        Utils.writeObjectToFile(account,FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
        applyToken(account.getTokenApp());
        publicEvent(account);
    }

    private void applyToken(String token){
        HashMap<String, String> map = new HashMap();
        map.put("token", token);
        RequestManager.getInstance().setHeader(map);
    }


    public void userRegister(String name,String tel,String password,String verify,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("name",name);
        params.put("tel",tel);
        params.put("pass",Utils.MD5(password.getBytes()));
        params.put("code",verify);
        Utils.Log(verify);
        RequestManager.getInstance().post(API.URL.Register,params,callback);
    }

    public void isRegistered(String tel,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel",tel);
        RequestManager.getInstance().post(API.URL.IsRegistered,params,callback);
    }

    public void userLogin(String tel,String password,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel", tel);
        params.put("pass", Utils.MD5(password.getBytes()));
        RequestManager.getInstance().post(API.URL.Login, params, callback.add(new DataCallback<AccountInfo>() {
            @Override
            public void success(String info, AccountInfo data) {
                setAccount(data);
            }

            @Override
            public void error(String errorInfo) {

            }
        }));
    }

    public void modifyPassword(String tel,String password,String verify,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel",tel);
        params.put("pass",password);
        params.put("code",verify);
        RequestManager.getInstance().post(API.URL.ModifyPassword, params, callback);
    }


}
