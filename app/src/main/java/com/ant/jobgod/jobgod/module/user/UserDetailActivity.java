package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(UserDetailPresenter.class)
public class UserDetailActivity extends BaseActivity<UserDetailPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_user_detail);
    }

}
