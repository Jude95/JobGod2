package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

import nucleus.manager.Presenter;

/**
 * Created by alien on 2015/7/10.
 */
public class AttentionToMePresenter extends Presenter<AttentionToMeFragment> {

    private PersonBrief[] personBriefs;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        UserModel.getInstance().getAttentionToMe(new DataCallback<PersonBrief[]>() {
            @Override
            public void success(String info, PersonBrief[] data) {
                getView().setUsersData(personBriefs = data);
            }
        });
    }

    @Override
    protected void onCreateView(AttentionToMeFragment view) {
        super.onCreateView(view);
        getView().setUsersData(personBriefs);
    }

}
