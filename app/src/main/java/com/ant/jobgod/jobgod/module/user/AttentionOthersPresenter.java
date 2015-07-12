package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.bean.PersonBrief;

import nucleus.manager.Presenter;

/**
 * Created by alien on 2015/7/10.
 */
public class AttentionOthersPresenter extends Presenter<AttentionOthersFragment> {

    private PersonBrief[] data;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(AttentionOthersFragment view) {
        super.onCreateView(view);
        if(data!=null){
            getView().setUersData(data);
        }
    }

    public void setUersData(){
        getView().setUersData(data);
    }
}
