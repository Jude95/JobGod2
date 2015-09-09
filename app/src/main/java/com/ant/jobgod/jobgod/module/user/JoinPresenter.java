package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by Mr.Jude on 2015/7/20.
 */
public class JoinPresenter extends BeamListActivityPresenter<JoinActivity,JobBrief> {

    @Override
    protected void onCreate(JoinActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getJoin(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getAdapter().clear();
                getAdapter().addAll(data);
                getAdapter().stopMore();
            }
        });
    }
}
