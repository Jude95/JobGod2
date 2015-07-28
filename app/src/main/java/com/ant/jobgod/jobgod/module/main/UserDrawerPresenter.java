package com.ant.jobgod.jobgod.module.main;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.RongYunModel;
import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;

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
        getView().setAccount(info);
    }


    public boolean checkLogin(){
        if(AccountModel.getInstance().getAccount() == null){
            getView().startActivity(new Intent(getView().getActivity(), UserLoginActivity.class));
            return false;
        }
        return true;
    }

    public void startActivity(Class<?> clazz){
        if (checkLogin())
        getView().startActivity(new Intent(getView().getActivity(), clazz));
    }

    public void startChatList(){
        if (checkLogin())
        RongYunModel.getInstance().chatList(getView().getActivity());
    }
}
