package com.ant.jobgod.jobgod.app;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.activeandroid.ActiveAndroid;
import com.ant.jobgod.jobgod.BuildConfig;
import com.ant.jobgod.jobgod.model.AbsModel;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jude.http.RequestManager;
import com.umeng.analytics.MobclickAgent;

import net.youmi.android.AdManager;

import io.rong.imkit.RongIM;


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

        /* OnCreate 会被多个进程重入，这段保护代码，确保只有您需要使用 RongIM 的进程和 Push 进程执行了 init。
         * xxx.xxx.xxx 是您的主进程或者使用了 RongIM 的其他进程 */
        if("com.ant.jobgod.jobgod".equals(getCurProcessName(getApplicationContext())) ||
                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {

            /* IMKit SDK调用第一步 初始化 */
            RongIM.init(this);

            /* 必须在使用 RongIM 的进程注册回调、注册自定义消息等 */
            if ("com.ant.jobgod.jobgod".equals(getCurProcessName(getApplicationContext()))) {

                instance = this;
                Fresco.initialize(this);
                RequestManager.getInstance().init(this);
                RequestManager.getInstance().setDebugMode(BuildConfig.DEBUG, "GodNet");
                RequestManager.getInstance().setCacheEnable(true);
                Utils.initialize(this, "GodLog", "5,28,0");
                AdManager.getInstance(this).init("5aa51ecd2360f2e1", "d4c8d06a735d82d0", true);


                ActiveAndroid.initialize(this);
                FileManager.getInstance().init(this);
                AbsModel.init(this);
                MobclickAgent.updateOnlineConfig(this);

                String text = "Jude";
                String textencode;
                Utils.Log(textencode = Utils.string2Base64(text));
                Utils.Log(Utils.base64ToString(textencode));
            }
        }
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

    /* 一个获得当前进程的名字的方法 */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}
