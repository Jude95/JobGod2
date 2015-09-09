package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;

/**
 * Created by alien on 2015/7/10.
 */
public class AttentionToMePresenter extends BeamListFragmentPresenter<AttentionToMeFragment,PersonBrief> {

    @Override
    protected void onCreate(AttentionToMeFragment view, Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getAttentionToMe(new DataCallback<PersonBrief[]>() {
            @Override
            public void success(String info, PersonBrief[] data) {
                getAdapter().clear();
                getAdapter().addAll(data);
                getAdapter().stopMore();
            }

            @Override
            public void error(String errorInfo) {
                getView().showError();
            }
        });
    }



}
