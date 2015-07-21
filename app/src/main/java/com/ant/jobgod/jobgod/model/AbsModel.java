package com.ant.jobgod.jobgod.model;

import android.content.Context;

import java.util.HashMap;

import de.greenrobot.event.EventBus;

/**
 * Created by zhuchenxi on 15/6/7.
 */
public abstract class AbsModel {
    private final static Class<?>[] MODELS = {
            RemoteFileModel.class,
            UserModel.class,
            AccountModel.class,
            LocationModel.class,
            PersonBriefModel.class,
            CommonModel.class,
            RegionModel.class,
            JobModel.class,
            RongYunModel.class
    };


    private final static HashMap<Class<?>,AbsModel> mModelMap = new HashMap<>();

    //每个model自己持有一个EventBus;
    private EventBus eventBus = new EventBus();
    public void registerEvent(Object object){
        eventBus.register(object);
    }
    protected void publicEvent(Object object){
        eventBus.post(object);
    }


    public final static void init(Context ctx){
        for (Class m:MODELS) {
            if (AbsModel.class.isAssignableFrom(m)){
                try {
                    AbsModel instance = (AbsModel) (m.newInstance());
                    mModelMap.put(m, instance);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Class m:MODELS) {
            getInstance(m).onAppCreate(ctx);
        }
    }

    public static <T extends AbsModel> T getInstance(Class<T> clazz){
        if (mModelMap.containsKey(clazz)) {
            return (T) mModelMap.get(clazz);
        }else
            return null;
    }


    protected void onAppCreate(Context ctx){}
}
