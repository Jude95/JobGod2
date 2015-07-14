package com.ant.jobgod.jobgod.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;
import com.android.http.RequestManager;
import com.android.volley.VolleyLog;
import com.ant.jobgod.jobgod.BuildConfig;
import com.ant.jobgod.jobgod.config.Config;
import com.ant.jobgod.jobgod.model.AbsModel;
import com.ant.jobgod.jobgod.util.ActivityManager;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.analytics.MobclickAgent;

import net.youmi.android.AdManager;

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
        RequestManager.getInstance().setDebugMode(BuildConfig.DEBUG, "GodNet");
        VolleyLog.setTag("xxxxxx");
        RequestManager.getInstance().setDebugMode(true, "GodNet");
        RequestManager.getInstance().setCacheEnable(true);
        Utils.initialize(this, "GodLog", "5,28,0");
        AdManager.getInstance(this).init("5aa51ecd2360f2e1", "d4c8d06a735d82d0", true);
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

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base); MultiDex.install(this);
    }

}
