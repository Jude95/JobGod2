package com.ant.jobgod.jobgod.model;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.config.SP;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.SpecificClassExclusionStrategy;
import com.ant.jobgod.jobgod.util.Utils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Mr.Jude on 2015/6/12.
 */
public class PersonBriefModel extends AbsModel {
    public static PersonBriefModel getInstance() {
        return getInstance(PersonBriefModel.class);
    }


    public PersonBrief getPersonBriefOnBlock(String id){
        if (id == null) return null;
        PersonBrief person = new Select().from(PersonBrief.class).where("userId = ?", id).executeSingle();
        if (person == null){
            person = getPersonFromServerDirect(id);
        }
        if (person == null){
            person = new PersonBrief("0","未知","","");
        }
        return person;
    }

    public PersonBrief getPersonFromServerDirect(final String id){
        PersonBrief person = null;
        String response = Utils.sendPost(API.URL.GetPersonBrief,"userId="+id);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            int status = jsonObject.getInt(API.KEY.STATUS);
            String info = jsonObject.getString(API.KEY.INFO);
            JSONObject dataArr = jsonObject.getJSONObject(API.KEY.DATA);
            Gson gson = new GsonBuilder().setExclusionStrategies(new SpecificClassExclusionStrategy(null, Model.class)).create();
            PersonBrief data = gson.fromJson(dataArr.toString(), PersonBrief.class);

            person = new Select().from(PersonBrief.class).where("userId = ?",id).executeSingle();
            if (person == null) person = new PersonBrief();
            person.clone(data);
            person.save();

        } catch (JSONException e) {
        }
        return person;
    }

    public void sysnData(){
        List<PersonBrief> list = new Select().from(PersonBrief.class).execute();
        RequestMap params = new RequestMap();
        for (PersonBrief p:list) {
            params.put("userId[]",p.getUID());
        }
        params.put("time", Utils.getPreference().getString(SP.PersonSync,"0"));
        RequestManager.getInstance().post(API.URL.UpdateGetPersonBrief, params, new DataCallback<PersonBrief[]>() {
            @Override
            public void success(String info, PersonBrief[] data) {
                Utils.getPreference().edit().putString(SP.PersonSync,System.currentTimeMillis()/1000+"").commit();
                for (PersonBrief p : data) {
                    p.saveById();
                }
            }

            @Override
            public void error(String errorInfo) {

            }
        });
    }



}