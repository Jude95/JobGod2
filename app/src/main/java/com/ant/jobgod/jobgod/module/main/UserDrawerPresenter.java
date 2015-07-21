package com.ant.jobgod.jobgod.module.main;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.RongYunModel;
import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;

import nucleus.manager.Presenter;

/**
 * Created by Mr.Jude on 2015/7/1.
 */
public class UserDrawerPresenter extends Presenter<UserDrawerFragment> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        AccountModel.getInstance().registerEvent(this);
        RongYunModel.getInstance().registerEvent(this);
        AccountData info = AccountModel.getInstance().getAccount();
        if (info != null){
            getView().setAccount(info);
        }

    }
    public void onEvent(Integer i){
        getView().setMessageCount(i);
    }

    public void onEvent(AccountData info){
        if (info != null) getView().setAccount(info);
    }


    public void startActivity(Class<?> clazz){
        if(AccountModel.getInstance().getAccount() == null){
            Utils.Toast("请先登录再使用这些功能");
            clazz = UserLoginActivity.class;
        }
        getView().startActivity(new Intent(getView().getActivity(), clazz));
    }
}
