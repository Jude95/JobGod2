package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;

import nucleus.manager.Presenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
public class ModifyPasswordPresenter extends Presenter<ModifyPasswordActivity> {
    private String mNumber;
    private String mPassword;

    public void checkIsRegister(String number,String password){
        this.mNumber = number;
        this.mPassword = password;
        getView().showProgress("提交中");
        UserModel.getInstance().isRegister(number, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                if (status == 201)verifyModify();
                else if (status == 200)getView().setNumberNoExist();
            }

            @Override
            public void success(String info) {

            }
        });
    }
    public void verifyModify() {
        Intent intent = new Intent(getView(), VerifyActivity.class);
        intent.putExtra("number", mNumber);
        intent.putExtra("password", mPassword);
        intent.putExtra("type", VerifyPresenter.Type_ModifyPassword);
        getView().startActivityForResult(intent, 1);
    }
}
