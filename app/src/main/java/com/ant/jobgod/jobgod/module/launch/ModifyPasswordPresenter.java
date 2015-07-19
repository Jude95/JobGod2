package com.ant.jobgod.jobgod.module.launch;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

import cn.smssdk.gui.SMSManager;
import cn.smssdk.gui.TimeListener;
import nucleus.manager.Presenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
public class ModifyPasswordPresenter extends Presenter<ModifyPasswordActivity> implements TimeListener{
    private String number;

    @Override
    protected void onCreateView(ModifyPasswordActivity view) {
        super.onCreateView(view);
        SMSManager.getInstance().registerTimeListenre(this);
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


    public void retry(){
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
    }

    public void sendModify(String number,String password,String code) {
        AccountModel.getInstance().modifyPassword(number, password, code, new StatusCallback() {
            @Override
            public void success(String info) {
                Utils.Toast("密码修改成功");
                getView().finish();
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
