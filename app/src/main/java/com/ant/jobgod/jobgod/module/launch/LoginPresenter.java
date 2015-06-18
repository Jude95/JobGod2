package com.ant.jobgod.jobgod.module.launch;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void login(String tel,String pass){
        UserModel.getInstance().login(tel, pass, new StatusCallback() {
            @Override
            public void success(int status, String info) {

            }

            @Override
            public void error(String errorInfo) {

            }
        });
    }
}
