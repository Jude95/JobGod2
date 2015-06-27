package com.ant.jobgod.jobgod.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.activeandroid.ActiveAndroid;
import com.android.http.RequestManager;
import com.ant.jobgod.jobgod.config.SP;
import com.ant.jobgod.jobgod.model.AbsModel;
import com.ant.jobgod.jobgod.module.launch.LoginActivity;
import com.ant.jobgod.jobgod.util.ActivityManager;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.HashMap;


/**
 * Created by Mr.Jude on 2015/1/27.
 */
public class APP extends Application {
    private static APP instance = null;

    public static APP getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Fresco.initialize(this);
        RequestManager.getInstance().init(this);
        RequestManager.getInstance().setDebugMode(true, "GodNet");
        RequestManager.getInstance().setCacheEnable(true);
        Utils.initialize(this, "GodLog", "5,28,0");
        //RongIM.init(this);
        ActiveAndroid.initialize(this);
        FileManager.getInstance().init(this);
        AbsModel.init(this);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }



    @Override
    public void onTerminate() {
        super.onTerminate();
        //ActiveAndroid.dispose();
    }

    public void closeToLogin(){
        Context ctx = ActivityManager.getInstance().currentActivity();
        ctx.startActivity(new Intent(ctx, LoginActivity.class));
        ActivityManager.getInstance().popAllActivity();
    }

    public void notifyLogin(){
        Activity ctx = ActivityManager.getInstance().currentActivity();
        //new NotifyLoginDialogFragment().show(ctx.getFragmentManager(),"notifyLogin");
    }

}
