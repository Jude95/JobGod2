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
        UserModel.getInstance().isRegister(tel, new StatusCallback() {


            @Override
            public void success(int status, String info) {
                if (status == 200){
                    getView().continueRegister();
                }else{
                    Utils.Toast(info);
                }
            }

            @Override
            public void error(String errorInfo) {
                Utils.Toast(errorInfo);
            }
        });
    }

}
