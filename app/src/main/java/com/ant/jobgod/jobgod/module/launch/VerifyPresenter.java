package com.ant.jobgod.jobgod.module.launch;

import android.app.Activity;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.SMSSDK;

/**
 * Created by Mr.Jude on 2015/6/13.
 */
public class VerifyPresenter extends BasePresenter<VerifyActivity> {
    private String number;
    private String password;
    private int type;

    // 填写从短信SDK应用后台注册得到的APPKEY
    private static final String APPKEY = "587c0a5919c6";
    // 填写从短信SDK应用后台注册得到的APPSECRET
    private static final String APPSECRET = "f8652efb1c8a638223bb22d5b02a4279";

    public static final int Type_Register = 1;
    public static final int Type_FindPassword= 2;


    private static long beginTime = 0;
    private Timer timer = new Timer();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        SMSSDK.initSDK(getView(), APPKEY, APPSECRET);
        number = getView().getIntent().getStringExtra("number");
        password = getView().getIntent().getStringExtra("password");
        type = getView().getIntent().getIntExtra("type", Type_Register);

        if (beginTime == 0){
            beginTime = System.currentTimeMillis();
            SMSSDK.getVerificationCode("86",number);
        }
        dealTime();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                dealTime();
            }
        },0,1000);
    }

    public String getTel(){
        return number;
    }


    public void dealTime(){
        long curTime = System.currentTimeMillis();
        int delta = (int) ((curTime-beginTime)/1000);
        getView().updateLastTime(60 - delta);
    }

    public void retry(){
        SMSSDK.getVerificationCode("86",number);
        beginTime = System.currentTimeMillis();
    }



    public void upload(String code){
        getView().showProgress("提交中");
        switch (type){
            case Type_Register:
                register(number,password,code);
                break;
            case Type_FindPassword:
                modifyPass(number,password, code);
                break;
        }
    }

    public void register(String tel,String pass,String code){
        UserModel.getInstance().register(tel, pass, code, new StatusCallback() {
            @Override
            public void success(int status, String info) {
                getView().dismissProgress();
                Utils.Toast("注册成功，诚客兼职欢迎您");
                getView().setResult(Activity.RESULT_OK);
                getView().finish();
            }

            @Override
            public void error(String errorInfo) {
                getView().dismissProgress();
                Utils.Toast(errorInfo);
            }
        });
    }
    public void modifyPass(String tel,String pass,String code){
        UserModel.getInstance().register(tel, pass, code, new StatusCallback() {
            @Override
            public void success(int status, String info) {
                getView().dismissProgress();
                Utils.Toast("密码修改成功");
                getView().setResult(Activity.RESULT_OK);
                getView().finish();
            }

            @Override
            public void error(String errorInfo) {
                getView().dismissProgress();
                Utils.Toast(errorInfo);
            }
        });
    }
}
