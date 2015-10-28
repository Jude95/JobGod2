package com.ant.jobgod.jobgod.module.user;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.bijection.Presenter;

import cn.smssdk.gui.SMSManager;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyTelPresenter extends Presenter<ModifyTelActivity> {

    private String number;

    @Override
    protected void onCreateView(ModifyTelActivity view) {
        super.onCreateView(view);
        SMSManager.getInstance().registerTimeListener(getView());
    }

    public void sendCode(String number){
        this.number = number;
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
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
        getView().getExpansion().showProgressDialog("提交中");
        AccountModel.getInstance().boundTel(oldPhone, newPhone, oPassword, nPassword, code, new StatusCallback() {
            @Override
            public void success(String info) {
                Utils.Toast("绑定成功");
                getView().getExpansion().dismissProgressDialog();
                getView().finish();
            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                getView().getExpansion().dismissProgressDialog();
                if (status == 201) {
                    getView().setTelRepeat();
                } else if (status == 202) {
                    getView().setOldPasswordError();
                } else if (status >= 203) {
                    getView().setCodeError();
                }
            }
        });
    }

    public void retry(){
        Utils.Toast("已发送短信，请查收");
        SMSManager.getInstance().sendMessage(getView(), number);
    }


    @Override
    protected void onDestroyView() {
        super.onDestroyView();
        SMSManager.getInstance().unRegisterTimeListener(getView());
    }
}
