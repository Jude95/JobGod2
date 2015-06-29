package com.ant.jobgod.jobgod.module.launch;

import android.app.Activity;
import android.os.Handler;

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
public class UserRegisterPresenter extends BasePresenter<UserRegisterActivity> {
    private String number;

    public void checkIsRegister(String number){
        this.number = number;
        getView().showProgress("提交中");
        UserModel.getInstance().isRegister(number, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                if (status == 200) {
                    getView().showCodeCard();
                    SMSSDK.getVerificationCode("86", number);
                    startTimer();
                } else if (status == 201) getView().setNumberDuplicate();
            }

            @Override
            public void success(String info) {

            }

        });
    }
    private Timer timer;
    private long beginTime = 0;
    private Handler handler = new Handler();
    private void startTimer(){
        timer = new Timer();
        beginTime = System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                long curTime = System.currentTimeMillis();
                int delta = (int) ((curTime - beginTime) / 1000);
                handler.post(()->getView().setRetryTime(60 - delta));
                if (60 - delta < 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
    public void retry(){
        SMSSDK.getVerificationCode("86", number);
        startTimer();
    }

    public void register(String name,String tel,String pass,String code){
        UserModel.getInstance().register(name,tel, pass, code, new StatusCallback() {
            @Override
            public void success(String info) {
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




}
