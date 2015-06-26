package com.ant.jobgod.jobgod.model.callback;

import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.util.Utils;

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
            result(status, info);
            if(status == API.CODE.SUCCEED){
                success(info);
            }else if (status == API.CODE.Failure){
                failure(info);
            }else if (status == API.CODE.PERMISSION_DENIED){
                authorizationFailure();
            }
        } catch (JSONException e) {
            error("数据解析错误");
        }
        super.onSuccess(s);
    }

    @Override
    public void onError(String s) {
        result(-1,"网络错误");
        error("网络错误");
        super.onError(s);
    }

    public void result(int status, String info){}
    public abstract void success(String info);
    public void failure(String info){
        Utils.Toast(info);
    }
    public void authorizationFailure(){}
    public void error(String errorInfo){
        Utils.Toast(errorInfo);
    }
}
