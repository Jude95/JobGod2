package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

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

    public void getUserDetail(DataCallback<UserDetail> callback){
        
    }


    public void getCollection(DataCallback<JobBrief[]> callback){

    }

    public void getJoin(DataCallback<JobBrief[]> callback){

    }
}
