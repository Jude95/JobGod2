package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.AccountInfo;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;

/**
 * Created by Mr.Jude on 2015/6/6.
 * 关于用户数据的处理。
 */
public class UserModel  extends AbsModel{
    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
    }

    public void register(String tel,String password,String verify,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel",tel);
        params.put("pass",password);
        params.put("code",verify);
        RequestManager.getInstance().post(API.URL.Register,params,callback);
    }

    public void isRegister(String tel,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel",tel);
        RequestManager.getInstance().post(API.URL.IsRegistered,params,callback);
    }


    public void login(String tel,String password,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel",tel);
        params.put("pass",password);
        RequestManager.getInstance().post(API.URL.Login,params,callback.add(new DataCallback<AccountInfo>() {
            @Override
            public void success(String info, AccountInfo data) {
                AccountModel.getInstance().setAccount(data);
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
        RequestManager.getInstance().post(API.URL.ModifyPassword,params,callback);
    }


}
