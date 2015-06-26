package com.ant.jobgod.jobgod.module.launch;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/6/13.
 */
public class RegisterPresenter extends BasePresenter<RegisterActivity> {

    public void checkIsRegister(String tel){
        getView().showProgress("提交中");
        Utils.Log("checkIsRegister");
        UserModel.getInstance().isRegister(tel, new StatusCallback() {
            @Override
            public void result(int status, String info) {
                Utils.Log("end");
                getView().dismissProgress();
            }

            @Override
            public void success(String info) {
                getView().continueRegister();
            }

            @Override
            public void failure(String info) {
                getView().setNumberDuplicate();
            }
        });
    }

}
