package com.ant.jobgod.jobgod.module.setting;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyPasswordPresenter.class)
public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_modify_password);
    }

}
