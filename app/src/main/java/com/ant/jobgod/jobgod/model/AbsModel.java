package com.ant.jobgod.jobgod.model;

import android.content.Context;

import java.util.HashMap;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public abstract class AbsModel {
    private final static Class<?>[] MODELS = {
            UserModel.class,
            AccountModel.class,
            LocationModel.class,
            PersonBriefModel.class,
            JobModel.class
    };


    private final static HashMap<Class<?>,AbsModel> mModelMap = new HashMap<>();

    public final static void init(Context ctx){
        for (Class m:MODELS) {
            if (m.isAssignableFrom(AbsModel.class)){
                try {
                    AbsModel instance = (AbsModel) (m.newInstance());
                    mModelMap.put(m, instance);
                    instance.onAppCreate(ctx);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static <T extends AbsModel> T getInstance(Class<T> clazz){
        return (T)mModelMap.get(clazz);
    }


    protected void onAppCreate(Context ctx){}
}
