package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.UserAccountData;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/10.
 */
public class UserDataPresenter extends BasePresenter<UserDataActivity> {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        AccountModel.getInstance().registerEvent(this);
    }

    @Override
    protected void onCreateView(UserDataActivity view) {
        super.onCreateView(view);
        getView().setUserDetailData(AccountModel.getInstance().getUserAccount());
    }

    public void onEvent(UserAccountData info){
        getView().setUserDetailData(info);
    }

    public void startActivity(Class<?> ctx){
        intent=new Intent();
        intent.setClass(getView(),ctx);
        intent.putExtra("id",AccountModel.getInstance().getUserAccount().getId());
        getView().startActivity(intent);
    }

    public void editName(){
        new MaterialDialog.Builder(getView())
                .title("修改昵称")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .inputMaxLength(8)
                .input("昵称", "", (dialog, input) -> {
                    if (input.toString().trim().isEmpty()) {
                        Utils.Toast("昵称不能为空");
                        return;
                    } else {
                        getView().showProgress("修改中");
                        UserModel.getInstance().modifyName(input.toString(), new StatusCallback() {
                            @Override
                            public void success(String info) {
                                AccountModel.getInstance().updateAccountData();

                            }

                            @Override
                            public void result(int status, String info) {
                                super.result(status, info);
                                getView().dismissProgress();
                            }
                        });
                    }
                }).show();
    }

    public void editSign(){
        new MaterialDialog.Builder(getView())
                .title("修改签名")
                .inputType(InputType.TYPE_CLASS_TEXT)
                .inputMaxLength(32)
                .input("签名", "", (dialog, input) -> {
                    getView().showProgress("修改中");
                    UserModel.getInstance().modifySign(input.toString(), new StatusCallback() {
                        @Override
                        public void success(String info) {
                            AccountModel.getInstance().updateAccountData();
                        }

                        @Override
                        public void result(int status, String info) {
                            super.result(status, info);
                            getView().dismissProgress();
                        }
                    });

                }).show();
    }


    public void checkAuthentication(){
        if (AccountModel.getInstance().getUserAccount().getAuthenticationStatus()==0){
            startActivity(AuthenticationActivity.class);
        }else{
            Utils.Toast("您已完成验证");
        }
    }
}
