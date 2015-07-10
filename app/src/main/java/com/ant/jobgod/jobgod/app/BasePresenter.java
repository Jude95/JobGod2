package com.ant.jobgod.jobgod.app;

import com.ant.jobgod.jobgod.util.Utils;

import nucleus.manager.Presenter;
import nucleus.view.NucleusAppCompatActivity;

/**
 * Created by Mr.Jude on 2015/6/18.
 */
public class BasePresenter<V extends NucleusAppCompatActivity> extends Presenter<V> {
    public final void saveStandVar(String key,String value){
        Utils.getPreference().edit().putString(this.getClass().getName()+"-"+key,value).commit();
    }
    public final void saveStandVar(String key,int value){
        Utils.getPreference().edit().putInt(this.getClass().getName()+"-"+key, value).commit();
    }
    public final void saveStandVar(String key,boolean value){
        Utils.getPreference().edit().putBoolean(this.getClass().getName()+"-"+key, value).commit();
    }
    public final void saveStandVar(String key,float value){
        Utils.getPreference().edit().putFloat(this.getClass().getName()+"-"+key, value).commit();
    }
    public final void saveStandVar(String key,long value){
        Utils.getPreference().edit().putLong(this.getClass().getName()+"-"+key, value).commit();
    }
    public final String readStandVar(String key,String defaultValue){
        return Utils.getPreference().getString(this.getClass().getName()+key,defaultValue);
    }
    public final int readStandVar(String key,int defaultValue){
        return Utils.getPreference().getInt(this.getClass().getName()+"-"+key, defaultValue);
    }
    public final boolean readStandVar(String key,boolean defaultValue){
        return Utils.getPreference().getBoolean(this.getClass().getName()+"-"+key, defaultValue);
    }
    public final float readStandVar(String key,float defaultValue){
        return Utils.getPreference().getFloat(this.getClass().getName()+"-"+key, defaultValue);
    }
    public final long readStandVar(String key,long defaultValue){
        return Utils.getPreference().getLong(this.getClass().getName()+"-"+key, defaultValue);
    }
}
