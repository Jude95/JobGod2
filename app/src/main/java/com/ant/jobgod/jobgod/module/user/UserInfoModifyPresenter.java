package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.app.BasePresenter;

/**
 * Created by alien on 2015/7/10.
 */
public class UserInfoModifyPresenter extends BasePresenter<UserInfoModifyActivity> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(UserInfoModifyActivity view) {
        super.onCreateView(view);
    }

    public void submitInfo(RequestMap param){
        param.put("id",readStandVar("id",0)+"");
        /**
         * 接下来去post到服务器
         */
    }

}
