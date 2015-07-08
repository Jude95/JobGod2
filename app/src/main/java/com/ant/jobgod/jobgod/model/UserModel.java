package com.ant.jobgod.jobgod.model;

import android.content.Context;

/**
 * Created by Mr.Jude on 2015/6/6.
 * 关于用户数据的处理。
 */
public class UserModel  extends AbsModel{
    public static UserModel getInstance() {
        return getInstance(UserModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
    }


}
