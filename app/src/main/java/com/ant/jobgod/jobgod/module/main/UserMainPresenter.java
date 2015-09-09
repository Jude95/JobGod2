package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.jude.beam.bijection.Presenter;
import com.umeng.update.UmengUpdateAgent;

/**
 * Created by zhuchenxi on 15/6/27.
 */
public class UserMainPresenter extends Presenter<UserMainActivity> {

    @Override
    protected void onCreate(UserMainActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        UmengUpdateAgent.update(getView());
    }
}
