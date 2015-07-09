package com.ant.jobgod.jobgod.module.launch;

import android.app.Activity;
import android.content.Intent;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

import cn.smssdk.gui.SMSManager;
import cn.smssdk.gui.TimeListener;

/**
 * Created by Mr.Jude on 2015/6/13.
 */
public class UserRegisterPresenter extends BasePresenter<UserRegisterActivity> implements TimeListener{
    private String number;

    @Override
    protected void onTakeView(UserRegisterActivity view) {
        super.onTakeView(view);
        SMSManager.getInstance().registerTimeListenre(this);
    }

    public void checkIsRegister(String number){
        this.number = number;
        getView().showProgress("提交中");
        AccountModel.getInstance().isRegistered(number, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                if (status == 200) {
                    getView().showCodeCard();
                    Utils.Toast("已发送短信，请查收");
                    SMSManager.getInstance().sendMessage(getView(), number);
                } else if (status == 201) getView().setNumberDuplicate();
            }

            @Override
            public void success(String info) {

            }

        });
    }


    public void retry(){
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
    }

    public void register(String name,String tel,String pass,String code){
        getView().showProgress("提交中");
        AccountModel.getInstance().userRegister(name, tel, pass, code, new StatusCallback() {
            @Override
            public void success(String info) {
                getView().dismissProgress();
                Utils.Toast("注册成功，诚客兼职欢迎您");
                Intent i = new Intent();
                i.putExtra("number",tel);
                i.putExtra("password",tel);
                getView().setResult(Activity.RESULT_OK,i);
                getView().finish();
            }

            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
            }
        });
    }


    @Override
    protected void onDropView() {
        super.onDropView();
        SMSManager.getInstance().unRegisterTimeListenre(this);
    }

    @Override
    public void onLastTimeNotify(int lastSecond) {
        getView().setRetryTime(lastSecond);
    }

    @Override
    public void onAbleNotify(boolean valuable) {
        getView().setRetryEnable(valuable);
    }
}
