package com.ant.jobgod.jobgod.module.launch;

import android.os.Handler;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;

import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.SMSSDK;
import nucleus.manager.Presenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
public class ModifyPasswordPresenter extends Presenter<ModifyPasswordActivity> {
    private String mNumber;

    public void checkIsRegister(String number){
        this.mNumber = number;
        getView().showProgress("提交中");
        UserModel.getInstance().isRegister(number, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                if (status == 201) {
                    getView().showCodeCard();
                    SMSSDK.getVerificationCode("86", number);
                    startTimer();
                } else if (status == 200)getView().setNumberNoExist();
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
                handler.post(() -> getView().setRetryTime(60 - delta));
                if (60 - delta < 0) {
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }
    public void retry(){
        SMSSDK.getVerificationCode("86", mNumber);
        startTimer();
    }

    public void sendModify(String number,String password,String code) {
        UserModel.getInstance().modifyPassword(number, password, code, new StatusCallback() {
            @Override
            public void success(String info) {

            }
        });
    }


}
