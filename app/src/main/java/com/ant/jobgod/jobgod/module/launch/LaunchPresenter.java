package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.module.main.UserMainActivity;

/**
 * Created by Mr.Jude on 2015/7/1.
 */
public class LaunchPresenter extends BasePresenter<LaunchActivity> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        getView().startActivity(new Intent(getView(), AccountModel.getInstance().isUser()?UserMainActivity.class:UserMainActivity.class));
        getView().finish();
    }
}
