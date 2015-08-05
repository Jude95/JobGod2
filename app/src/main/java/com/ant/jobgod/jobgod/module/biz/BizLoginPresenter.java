package com.ant.jobgod.jobgod.module.biz;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/8/4.
 */
public class BizLoginPresenter extends BasePresenter<BizLoginActivity> {


    public void gotoBiz(String email, String password) {
        getView().showProgress("登录中");
        AccountModel.getInstance().bizLogin(email, password, new DataCallback() {
            @Override
            public void success(String info, Object data) {

            }

            @Override
            public void result(int status, String info) {
                getView().dismissProgress();
                switch (status) {
                    case 202:
                        getView().setEmailError();
                        break;
                    case 203:
                        getView().setPasswordError();
                        break;
                }
            }
        });

    }
}