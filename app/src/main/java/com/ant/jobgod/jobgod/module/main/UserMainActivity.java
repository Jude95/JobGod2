package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.umeng.message.PushAgent;

import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(UserMainPresenter.class)
public class UserMainActivity extends BaseActivity<UserMainPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_usermain);

    }
}
