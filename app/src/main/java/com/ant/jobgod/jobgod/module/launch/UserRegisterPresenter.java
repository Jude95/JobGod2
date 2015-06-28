package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/6/13.
 */
public class UserRegisterPresenter extends BasePresenter<UserRegisterActivity> {

    private String mNumber;
    private String mPassword;
    private String mName;

    public void checkIsRegister(String name,String number,String password){
        this.mName = name;
        this.mNumber = number;
        this.mPassword = password;
        getView().showProgress("提交中");
        UserModel.getInstance().isRegister(number, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                if (status == 200)verifyRegister();
                else if (status == 201)getView().setNumberDuplicate();
            }

            @Override
            public void success(String info) {

            }

        });
    }

    public void verifyRegister() {
        Intent intent = new Intent(getView(), VerifyActivity.class);
        intent.putExtra("name", mName);
        intent.putExtra("number", mNumber);
        intent.putExtra("password", mPassword);
        intent.putExtra("type", VerifyPresenter.Type_Register);
        getView().startActivityForResult(intent, 1);
    }
}
