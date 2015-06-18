package com.ant.jobgod.jobgod.model.callback;

import com.ant.jobgod.jobgod.config.API;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Mr.Jude on 2015/5/25.
 */
public abstract class StatusCallback extends LinkCallback {
    @Override
    public void onRequest() {
        super.onRequest();
    }

    @Override
    public void onSuccess(String s) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(s);
            int status = jsonObject.getInt(API.KEY.STATUS);
            String info = jsonObject.getString(API.KEY.INFO);
            if (status == API.CODE.Failure){
                error(info);
            }else if (status == API.CODE.PERMISSION_DENIED){
                authorizationFailure();
            }else{
                success(status,info);
            }
        } catch (JSONException e) {
            error("数据解析错误");
        }
        super.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        super.onError(s);
    }

    public abstract void success(int status,String info);
    public void authorizationFailure(){}
    public abstract void error(String errorInfo);
}
