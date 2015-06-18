package com.ant.jobgod.jobgod.app;

import android.os.Bundle;

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
        return Utils.getPreference().getString("Activity-"+key,defaultValue);
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

    private Bundle state = new Bundle();
    public final void saveTempVar(String key,String value){
        state.putString(this.getClass().getName()+"-"+key, value);
    }
    public final void saveTempVar(String key,int value){
        state.putInt(this.getClass().getName()+"-"+key, value);
    }
    public final void saveTempVar(String key,boolean value){
        state.putBoolean(this.getClass().getName()+"-"+key, value);
    }
    public final void saveTempVar(String key,float value){
        state.putFloat(this.getClass().getName()+"-"+key, value);
    }
    public final void saveTempVar(String key,long value){
        state.putLong(this.getClass().getName()+"-"+key, value);
    }


    public final String readTempVar(String key,String defaultValue){
        return state.getString(this.getClass().getName()+"-"+key,defaultValue);
    }
    public final int readTempVar(String key,int defaultValue){
        return state.getInt(this.getClass().getName()+"-"+key, defaultValue);
    }
    public final boolean readTempVar(String key,boolean defaultValue){
        return state.getBoolean(this.getClass().getName()+"-"+key, defaultValue);
    }
    public final float readTempVar(String key,float defaultValue){
        return state.getFloat(this.getClass().getName()+"-"+key, defaultValue);
    }
    public final long readTempVar(String key,long defaultValue){
        return state.getLong(this.getClass().getName()+"-"+key, defaultValue);
    }

    @Override
    protected void onSave(Bundle state) {
        this.state = state.getBundle("Activity");
        if (this.state == null){this.state = new Bundle();}
        super.onSave(state);
    }

    @Override
    protected void onCreate(Bundle savedState) {
        savedState.putBundle("Activity",state);
        super.onCreate(savedState);
    }
}
