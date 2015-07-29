package com.ant.jobgod.jobgod.module.user;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

import cn.smssdk.gui.SMSManager;
import cn.smssdk.gui.TimeListener;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyTelPresenter extends BasePresenter<ModifyTelActivity> implements TimeListener {

    private String number;

    @Override
    protected void onCreateView(ModifyTelActivity view) {
        super.onCreateView(view);
        SMSManager.getInstance().registerTimeListenre(this);
    }

    public void sendCode(String number){
        this.number = number;
        getView().showProgress("提交中");
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
        getView().dismissProgress();
    }


    /**
     * 绑定手机号
     * @param oldPhone
     * @param newPhone
     * @param oPassword
     * @param nPassword
     * @param code
     */
    public void boundTel(String oldPhone,String newPhone,String oPassword,String nPassword,String code) {
        AccountModel.getInstance().boundTel(oldPhone,newPhone, oPassword,nPassword, code, new StatusCallback() {
            @Override
            public void success(String info) {
                Utils.Toast("绑定成功");
                getView().finish();
            }
        });
    }

    public void retry(){
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
    }

    @Override
    protected void onDropView() {
        super.onDropView();
        SMSManager.getInstance().unRegisterTimeListenre(this);
    }

    @Override
    public void onLastTimeNotify(int lastSecond) {
        Utils.Log("lastSecond:"+lastSecond);
        getView().setRetryTime(lastSecond);
    }

    @Override
    public void onAbleNotify(boolean valuable) {
        getView().setRetryEnable(valuable);
    }

}
