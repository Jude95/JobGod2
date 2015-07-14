package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;

/**
 * Created by alien on 2015/7/11.
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationActivity> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(AuthenticationActivity view) {
        super.onCreateView(view);
    }

    /**
     * 网络请求，响应getview的点击事件
     */
    public void authentication(){
    }
}
