package com.ant.jobgod.jobgod.model;

import android.content.Context;

/**
 * Created by Mr.Jude on 2015/7/29.
 */
public class SocietyModel extends AbsModel   {

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
    }

    public static SocietyModel getInstance(){
        return getInstance(SocietyModel.class);
    }

}
