package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.bean.UserDetail;

/**
 * Created by alien on 2015/7/10.
 */
public class UserDetailPresenter extends BasePresenter<UserDetailActivity> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(UserDetailActivity view) {
        super.onCreateView(view);
    }

    public void setUserDetailData(UserDetail data){
        getView().setUserDetailData(data);
    }
}