package com.ant.jobgod.jobgod.module.launch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
public class UserLoginPresenter extends BasePresenter<UserLoginActivity> {
    private static final int REGISTER = 1243;

    UMSocialService mController;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        mController = UMServiceFactory.getUMSocialService("com.umeng.login");
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(getView(), "1104599783",
                "PeKfWv4imECnmEGn");
        qqSsoHandler.addToSocialSDK();
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
    }




    public void login(String tel,String pass){
        getView().showProgress("登录中");
        AccountModel.getInstance().userLogin(tel, pass, new StatusCallback() {

            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                switch (status){
                    case 202:
                        getView().setNumberError();
                        break;
                    case 203:
                        getView().setPasswordError();
                        break;
                }
            }

            @Override
            public void success(String info) {
                getView().finish();
            }


        });
    }



    public void loginByQQ(){
        mController.doOauthVerify(getView(), SHARE_MEDIA.QQ,new SocializeListeners.UMAuthListener() {
            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
            }
            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                if (value != null && !TextUtils.isEmpty(value.getString("uid"))) {
                    Utils.Toast("授权成功:"+value.getString("uid"));
                } else {
                    Utils.Toast("授权失败");
                }
            }
            @Override
            public void onCancel(SHARE_MEDIA platform) {
            }
            @Override
            public void onStart(SHARE_MEDIA platform) {
            }
        });
    }

    public void loginByWeiChat(){
        Utils.Toast("未集成");
    }
    public void loginBySina(){
        mController.doOauthVerify(getView(), SHARE_MEDIA.SINA, new SocializeListeners.UMAuthListener() {
            @Override
            public void onError(SocializeException e, SHARE_MEDIA platform) {
            }

            @Override
            public void onComplete(Bundle value, SHARE_MEDIA platform) {
                if (value != null && !TextUtils.isEmpty(value.getString("uid"))) {
                    Utils.Toast("授权成功:" + value.getString("uid"));
                } else {
                    Utils.Toast("授权失败");
                }
            }

            @Override
            public void onCancel(SHARE_MEDIA platform) {
            }

            @Override
            public void onStart(SHARE_MEDIA platform) {
            }
        });
    }
    public void gotoBiz(){
        Utils.Toast("未开发");
    }

    public void register(){
        getView().startActivityForResult(new Intent(getView(),UserRegisterActivity.class),REGISTER);
    }
    public void modifyPassword(){
        getView().startActivity(new Intent(getView(),ModifyPasswordActivity.class));
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        /**使用SSO授权必须添加如下代码 */
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
        if (requestCode == REGISTER && resultCode == Activity.RESULT_OK){
            String number = data.getStringExtra("number");
            String password = data.getStringExtra("password");
            if (number!=null&&password!=null)
                getView().setNumberAndPassword(number,password);
        }

    }
}
