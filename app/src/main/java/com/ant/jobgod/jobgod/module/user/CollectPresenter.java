package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by alien on 2015/7/13.
 */
public class CollectPresenter extends BeamListActivityPresenter<CollectActivity,JobBrief> {

    @Override
    protected void onCreate(CollectActivity view,Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        UserModel.getInstance().getCollection(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getAdapter().clear();
                getAdapter().addAll(data);
                getAdapter().stopMore();
            }
        });
    }
}
