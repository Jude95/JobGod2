package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

import nucleus.manager.Presenter;

/**
 * Created by alien on 2015/7/10.
 */
public class AttentionFromMePresenter extends Presenter<AttentionFromMeFragment> {

    private PersonBrief[] personBriefs;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        UserModel.getInstance().getAttentionFromMe(new DataCallback<PersonBrief[]>() {
            @Override
            public void success(String info, PersonBrief[] data) {
                if (data.length == 0 )getView().setNull();
                else getView().setUsersData(personBriefs = data);
            }
        });
    }

    @Override
    protected void onCreateView(AttentionFromMeFragment view) {
        super.onCreateView(view);
        getView().setUsersData(personBriefs);
    }

}
