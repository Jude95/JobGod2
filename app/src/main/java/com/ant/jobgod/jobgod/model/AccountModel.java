package com.ant.jobgod.jobgod.model;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.view.View;
import android.view.Window;

import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.model.bean.UserAccountData;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

import org.json.JSONException;
import org.json.JSONObject;

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

    public boolean isUser = true;
    public UserAccountData userAccountData;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        Activity a = new Activity();
        Window.Callback callback = a;
        View.OnCreateContextMenuListener  menuListener = a;
        ComponentCallbacks2 componentCallbacks = a;


        userAccountData = (UserAccountData) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object,ACCOUNTFILE));
        if (userAccountData !=null) {
            applyToken(userAccountData.getTokenApp());
        } else {
            applyToken("");
        }
        updateAccountData();
    }

    public boolean isUser(){
        return isUser;
    }

    /**
     *
     * @return return null if not login
     */
    public AccountData getAccount(){
        if (isUser)
            return userAccountData;
        else
            return null;
    }

    public UserAccountData getUserAccount() {
        return userAccountData;
    }


    public void updateAccountData(){
        RequestManager.getInstance().post(API.URL.GetUserData, null, new DataCallback<UserAccountData>() {
            @Override
            public void success(String info, UserAccountData data) {
                setUserAccountData(data);
            }
        });
    }

    public void setUserAccountData(UserAccountData userAccountData){
        isUser = true;
        this.userAccountData = userAccountData;
        saveAccount();
        applyToken(userAccountData.getTokenApp());
        publicEvent(userAccountData);
        Utils.Log("fuck");
    }

    public void UserLoginOut(){
        this.userAccountData = null;
        FileManager.getInstance().getChild(FileManager.Dir.Object, ACCOUNTFILE).delete();
        applyToken("");
        RongYunModel.getInstance().loginOut();
        publicEvent(new UserAccountData());
    }


    public void LoginOut(){
        if (isUser())UserLoginOut();
    }


    public void saveAccount(){
        if (isUser){
            Utils.writeObjectToFile(userAccountData,FileManager.getInstance().getChild(FileManager.Dir.Object, ACCOUNTFILE));
        }else{

        }
    }


    private void applyToken(String token){
        HashMap<String, String> map = new HashMap();
        JSONObject json = new JSONObject();
        try {
            json.put("token", token);
            json.put("type", "android");
            json.put("version",Utils.getAppVersionCode()+"");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        map.put("token", json.toString());
        RequestManager.getInstance().setHeader(map);
        Utils.Log("setToken:" + json.toString());
    }


    public void userRegister(String name,String tel,String password,String verify,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("name",name);
        params.put("tel",tel);
        params.put("pass",Utils.MD5(password.getBytes()));
        params.put("code", verify);
        RequestManager.getInstance().post(API.URL.Register, params, callback);
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
        RequestManager.getInstance().post(API.URL.Login, params, callback.add(new DataCallback<UserAccountData>() {
            @Override
            public void success(String info, UserAccountData data) {
                setUserAccountData(data);
            }

            @Override
            public void error(String errorInfo) {

            }
        }));
    }

    public void userLoginThroughQQ(String openId,String face,String name,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("type", "0");
        params.put("openId", openId);
        params.put("face", face);
        params.put("name", name);
        RequestManager.getInstance().post(API.URL.ThirdLogin, params, callback.add(new DataCallback<UserAccountData>() {
            @Override
            public void success(String info, UserAccountData data) {
                setUserAccountData(data);
            }

            @Override
            public void error(String errorInfo) {

            }
        }));
    }

    public void userLoginThroughSina(String openId,String face,String name,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("type", "1");
        params.put("openId", openId);
        params.put("face", face);
        params.put("name", name);
        RequestManager.getInstance().post(API.URL.ThirdLogin, params, callback.add(new DataCallback<UserAccountData>() {
            @Override
            public void success(String info, UserAccountData data) {
                setUserAccountData(data);
            }

            @Override
            public void error(String errorInfo) {

            }
        }));
    }

    public void userLoginThroughWeChat(String openId,String face,String name,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("type", "2");
        params.put("openId", openId);
        params.put("face", face);
        params.put("name", name);
        RequestManager.getInstance().post(API.URL.ThirdLogin, params, callback.add(new DataCallback<UserAccountData>() {
            @Override
            public void success(String info, UserAccountData data) {
                setUserAccountData(data);
            }

            @Override
            public void error(String errorInfo) {

            }
        }));
    }
    /**
     * 修改密码
     * @param tel
     * @param password
     * @param verify
     * @param callback
     */
    public void modifyPassword(String tel,String password,String verify,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("tel",tel);
        params.put("newP",Utils.MD5(password.getBytes()));
        params.put("code",verify);
        RequestManager.getInstance().post(API.URL.ModifyPassword, params, callback);
    }

    /**
     * 绑定手机
     * @param
     * @param code
     */
    public void boundTel(String oldTel,String newTel,String oldPassword,String newPassword,String code,StatusCallback callback){
        RequestMap params = new RequestMap();
        params.put("oldTel",oldTel);
        params.put("newTel",newTel);
        params.put("oldPassword",Utils.MD5(oldPassword.getBytes()));
        params.put("newPassword",Utils.MD5(newPassword.getBytes()));
        params.put("code",code);
        RequestManager.getInstance().post(API.URL.BindTel,params,callback);
    }


    /**
     * 商家登录
     * @param email
     * @param password
     * @param callback
     */
    public void bizLogin(String email,String password,DataCallback callback){
        RequestMap param=new RequestMap();
        param.put("email",email);
        param.put("pass",password);
        RequestManager.getInstance().post(API.URL.BizLogin,param,callback);
    }

}
