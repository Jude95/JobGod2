package com.ant.jobgod.jobgod.model.callback;


import com.activeandroid.Model;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.util.SpecificClassExclusionStrategy;
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
            JSONObject dataArr = jsonObject.getJSONObject(API.KEY.DATA);
            Gson gson = new GsonBuilder().setExclusionStrategies(new SpecificClassExclusionStrategy(null, Model.class)).create();
            T data = gson.fromJson(dataArr.toString(), ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
            if (status == API.CODE.SUCCEED){
                success(info,data);
            }else if (status == API.CODE.PERMISSION_DENIED){
                authorizationFailure();
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
        error("网络错误");
        super.onError(s);
    }

    public abstract void success(String info , T data);
    public void authorizationFailure(){}
    public abstract void error(String errorInfo);

}
