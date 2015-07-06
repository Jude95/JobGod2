package com.ant.jobgod.jobgod.util;

/**
 * Created by Mr.Jude on 2015/2/12.
 */

import android.app.Activity;
import android.text.TextUtils;

import java.util.LinkedList;

public class ActivityManager {
    private static LinkedList<Activity> activityStack;
    private static ActivityManager instance;

    private ActivityManager() {
    }

    public static ActivityManager getInstance() {
        if (instance == null) {
            instance = new ActivityManager();
        }
        return instance;
    }

    /**
     * 主动退出Activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            Utils.Log("ActivityLog: ",activity.getClass().getName()+" 退出栈");
            activity = null;
        }
    }

    /**
     * 获得当前栈顶Activity
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = null;
        if (activityStack != null && !activityStack.isEmpty())
            activity = activityStack.get(activityStack.size() - 1);
        return activity;
    }

    /**
     * 将当前Activity推入栈中
     *
     * @param activity
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new LinkedList<Activity>();
        }
        activityStack.add(activity);
        Utils.Log("ActivityLog: ",activity.getClass().getName()+" 压入栈");
    }

    /**
     * 当Activity退出
     *
     * @param activity
     */
    public void destroyActivity(Activity activity) {
        activityStack.remove(activity);
        Utils.Log("ActivityLog: ",activity.getClass().getName()+" 退出栈");
    }



    /**
     * 退出栈中所有Activity
     */
    public void popAllActivityExceptOne() {
        while (true) {
            Activity activity = currentActivity();
            if (null == activity) {
                break;
            }
            popActivity(activity);
        }
    }

    public void popAllActivity() {
        while (true) {
            Activity activity = currentActivity();
            if (null == activity) {
                break;
            }
            popActivity(activity);
        }
    }

    /**
     * 退出指定名字的activity
     */
    public void popPointNameActivity(String name) {
        while (true) {
            Activity activity = currentActivity();
            if (null == activity) {
                break;
            }

            String activityName = activity.getComponentName().getClassName().toString();
            if (TextUtils.equals(name, activityName)) {
                continue;
            }
            popActivity(activity);
        }
    }

    /**
     * 获得当前ACTIVITY 名字
     */
    public String getCurrentActivityName() {
        Activity activity = currentActivity();
        String name = "";
        if (activity != null) {
            name = activity.getComponentName().getClassName().toString();
        }
        return name;
    }

}

