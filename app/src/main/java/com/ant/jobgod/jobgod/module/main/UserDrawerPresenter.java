package com.ant.jobgod.jobgod.module.main;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.bean.AccountInfo;

import nucleus.manager.Presenter;

/**
 * Created by Mr.Jude on 2015/7/1.
 */
public class UserDrawerPresenter extends Presenter<UserDrawerFragment> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        AccountModel.getInstance().registerEvent(this);
        AccountInfo info = AccountModel.getInstance().getAccount();
        if (info != null){
            getView().setAccount(info);
        }
    }

    public void onEvent(AccountInfo info){
        if (info != null){
            getView().setAccount(info);
        }
    }

    public void startActivity(Class<?> clazz){
        getView().startActivity(new Intent(getView().getActivity(), clazz));
    }
}
