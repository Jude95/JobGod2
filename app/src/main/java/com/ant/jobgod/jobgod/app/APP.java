package com.ant.jobgod.jobgod.app;

import android.app.Activity;
import android.app.Application;

import com.activeandroid.ActiveAndroid;
import com.android.http.RequestManager;
import com.android.volley.VolleyLog;
import com.ant.jobgod.jobgod.model.AbsModel;
import com.ant.jobgod.jobgod.util.ActivityManager;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;

import io.rong.imkit.RongIM;


/**
 * Created by Mr.Jude on 2015/1/27.
 */
public class APP extends Application {
    private static APP instance = null;
    public static APP test;
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
        VolleyLog.setTag("xxxxxx");
        RequestManager.getInstance().setCacheEnable(true);
        Utils.initialize(this, "GodLog", "5,28,0");

        RongIM.init(this);

        ActiveAndroid.initialize(this);
        FileManager.getInstance().init(this);
        AbsModel.init(this);
        MobclickAgent.updateOnlineConfig(this);
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public void notifyLogin(){
        Activity ctx = ActivityManager.getInstance().currentActivity();
        //new NotifyLoginDialogFragment().show(ctx.getFragmentManager(),"notifyLogin");
    }

}
