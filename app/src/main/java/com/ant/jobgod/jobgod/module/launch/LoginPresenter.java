package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void login(String tel,String pass){

    }

    public void register(){
        getView().startActivity(new Intent(getView(),RegisterActivity.class));
    }
    public void modifyPassword(){
        //getView().startActivity(new Intent(getView(),RegisterActivity.class));
    }
}
