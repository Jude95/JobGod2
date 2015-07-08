package cn.smssdk.gui;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import cn.smssdk.SMSSDK;

/**
 * Created by Mr.Jude on 2015/7/6.
 */
public class SMSManager {
    private static final SMSManager instance = new SMSManager();
    public static SMSManager getInstance(){
        return instance;
    }

    public ArrayList<TimeListener> timeList = new ArrayList<>();
    private boolean inited = false;
    private Timer timer;
    private int last = 0;
    private void startTimer(){
        timer = new Timer();
        notifyEnable();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                last -= 1;
                notifyLastTime();
                if (last == 0) {
                    notifyEnable();
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public boolean sendMessage(Context ctx,String number){
        if (!inited){
            SMSSDK.initSDK(ctx, "587c0a5919c6", "f8652efb1c8a638223bb22d5b02a4279");
            inited = true;
        }
        if (last==0) {
            SMSSDK.getVerificationCode("86", number);
            last = 60;
            startTimer();
            return true;
        }else{
            return false;
        }
    }

    public void notifyLastTime(){
        for (TimeListener listener:timeList){
            final TimeListener finalListener = listener;
            try {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        finalListener.onLastTimeNotify(last);
                    }
                });
            }catch (Exception e){
                unRegisterTimeListenre(listener);
            }
        }
    }

    public void notifyEnable(){
        for (TimeListener listener:timeList){
            final TimeListener finalListener = listener;
            try {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        finalListener.onAbleNotify(last == 0);
                    }
                });
            }catch (Exception e){
                unRegisterTimeListenre(listener);
            }
        }
    }


    public void registerTimeListenre(TimeListener listener){
        timeList.add(listener);
        listener.onLastTimeNotify(last);
        listener.onAbleNotify(last==0);
    }

    public void unRegisterTimeListenre(TimeListener listener){
        timeList.remove(listener);
    }

}
