package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

import cn.smssdk.gui.SMSManager;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyPhonePresenter extends BasePresenter<ModifyPhoneActivity> {

    private String number;

    @Override
    protected void onCreate(Bundle savedState) {

    }

    @Override
    protected void onCreateView(ModifyPhoneActivity view) {
        super.onCreateView(view);
    }


    public void checkIsRegister(String number){
        this.number = number;
        getView().showProgress("提交中");
        AccountModel.getInstance().isRegistered(number, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                if (status == 201) {
                    getView().showCodeCard();
                    Utils.Toast("已发送短信，请查收");
                    SMSManager.getInstance().sendMessage(getView(), number);
                } else if (status == 200) getView().setNumberNoExist();
            }

            @Override
            public void success(String info) {

            }
        });
    }


    public void sendModify(String number,String password,String code) {
        AccountModel.getInstance().modifyPassword(number, password, code, new StatusCallback() {
            @Override
            public void success(String info) {

            }
        });
    }

    public void retry(){
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
    }


}
