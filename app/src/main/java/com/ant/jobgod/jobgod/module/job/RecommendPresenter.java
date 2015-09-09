package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by Mr.Jude on 2015/7/12.
 */
public class RecommendPresenter extends BeamListActivityPresenter<RecommendActivity,JobBrief> {


    @Override
    protected void onCreate(RecommendActivity view,Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        JobModel.getInstance().getRecommendList(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getAdapter().clear();
                getAdapter().addAll(data);
                getAdapter().stopMore();
            }
        });
    }
}
