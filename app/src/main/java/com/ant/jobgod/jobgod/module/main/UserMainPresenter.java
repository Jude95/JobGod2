package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;

/**
 * Created by zhuchenxi on 15/6/27.
 */
public class UserMainPresenter extends BasePresenter<UserMainActivity>{

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        PushAgent.getInstance(getView()).enable();
        UmengUpdateAgent.update(getView());
    }

}
