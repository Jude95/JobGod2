package com.ant.jobgod.jobgod.module.launch;

import android.app.Activity;
import android.content.Intent;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.jude.beam.bijection.Presenter;

/**
     * Created by Mr.Jude on 2015/6/6.
     */
    public class UserLoginPresenter extends Presenter<UserLoginActivity> {
        private static final int REGISTER = 1243;

        public void login(String tel,String password){
            getView().getExpansion().showProgressDialog("登录中");
            AccountModel.getInstance().userLogin(tel, password, new StatusCallback() {

                @Override
                public void result(int status, String info) {
                    getView().getExpansion().dismissProgressDialog();
                    switch (status) {
                        case 202:
                            getView().setNumberError();
                            break;
                        case 203:
                            getView().setPasswordError();
                            break;
                    }
                }

                @Override
                public void success(String info) {
                    getView().finish();
                }


            });
        }



        public void register(){
            getView().startActivityForResult(new Intent(getView(), UserRegisterActivity.class), REGISTER);
        }
        public void modifyPassword(){
            getView().startActivity(new Intent(getView(), ModifyPasswordActivity.class));
        }

        @Override
        protected void onResult(int requestCode, int resultCode, Intent data) {
            super.onResult(requestCode, resultCode, data);
            if (requestCode == REGISTER && resultCode == Activity.RESULT_OK){
                String number = data.getStringExtra("number");
                String password = data.getStringExtra("password");
                if (number!=null&&password!=null)
                    getView().setNumberAndPassword(number,password);
            }

        }



    }
