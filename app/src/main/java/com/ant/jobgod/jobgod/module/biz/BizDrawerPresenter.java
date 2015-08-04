package com.ant.jobgod.jobgod.module.biz;

import android.content.Intent;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;

import nucleus.manager.Presenter;

/**
 * Created by alien on 2015/8/4.
 */
public class BizDrawerPresenter extends Presenter<BizDrawerFragment> {



    public boolean checkLogin(){
        if(AccountModel.getInstance().getAccount() == null){
            getView().startActivity(new Intent(getView().getActivity(), UserLoginActivity.class));
            return false;
        }
        return true;
    }

    public void startActivity(Class clazz){
        if(!checkLogin()){
            clazz= UserLoginActivity.class;
        }
        getView().startActivity(new Intent(getView().getActivity(),clazz));
    }
}
