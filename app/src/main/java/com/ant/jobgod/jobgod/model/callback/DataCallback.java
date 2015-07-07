package com.ant.jobgod.jobgod.model.callback;


import com.activeandroid.Model;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.util.SpecificClassExclusionStrategy;
import com.ant.jobgod.jobgod.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;

/**
 * Created by zhuchenxi on 15/5/11.
 */
public abstract class DataCallback<T> extends LinkCallback {

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
            if (status == API.CODE.SUCCEED){
                Gson gson = new GsonBuilder().setExclusionStrategies(new SpecificClassExclusionStrategy(null, Model.class)).create();
                T data = gson.fromJson(jsonObject.getString(API.KEY.DATA), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
                success(info,data);
            }else if (status == API.CODE.PERMISSION_DENIED){
                authorizationFailure();
            }else if (status == API.CODE.Failure){
                failure(info);
            }else{
                error(info);
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
    public abstract void success(String info,T data);
    public void failure(String info){
        Utils.Toast(info);
    }
    public void authorizationFailure(){}
    public void error(String errorInfo){
        Utils.Toast(errorInfo);
    }

}
