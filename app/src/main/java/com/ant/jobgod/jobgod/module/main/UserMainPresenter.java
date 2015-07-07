package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.util.Utils;
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

    @Override
    protected void onCreateView(UserMainActivity view) {
        super.onCreateView(view);
        getView().setTradeData(JobModel.getInstance().getTrade());
        Utils.Log("length:" + JobModel.getInstance().getTrade().length);
        Utils.Log(JobModel.getInstance().getTrade()[0].getName());
    }

    public void getAdData(){

    }

}
