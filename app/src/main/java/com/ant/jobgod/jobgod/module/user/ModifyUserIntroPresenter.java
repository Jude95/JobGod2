package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyUserIntroPresenter extends BasePresenter<ModifyUserIntroActivity> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(ModifyUserIntroActivity view) {
        super.onCreateView(view);
    }

    public void postNet(DataCallback callback){
        RequestMap param=getView().setParam();
        /**
         * 去请求网络，回调返回结果
         */
    }
}
