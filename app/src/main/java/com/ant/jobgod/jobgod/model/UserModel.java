package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;

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

    public void modifyName(String name,StatusCallback callback){
        RequestManager.getInstance().post(API.URL.ModifyName,new RequestMap("name",name),callback);
    }
    public void modifySign(String sign,StatusCallback callback){
        RequestManager.getInstance().post(API.URL.ModifySign,new RequestMap("sign",sign),callback);
    }
    public void modifyFace(String face,StatusCallback callback){
        RequestManager.getInstance().post(API.URL.ModifyFace,new RequestMap("face",face),callback);
    }


    public void getCollection(DataCallback<JobBrief[]> callback){

    }

    public void getJoin(DataCallback<JobBrief[]> callback){

    }
}
