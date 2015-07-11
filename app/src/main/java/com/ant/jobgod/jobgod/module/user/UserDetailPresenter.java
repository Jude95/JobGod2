package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;

/**
 * Created by alien on 2015/7/10.
 */
public class UserDetailPresenter extends BasePresenter<UserDetailActivity> {

    private Intent intent;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(UserDetailActivity view) {
        super.onCreateView(view);
    }


    public void startAcitivity(Class<?> ctx){
        intent=new Intent();
        intent.setClass(getView(),ctx);
        getView().startActivity(intent);
    }
}
