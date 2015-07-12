package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by Mr.Jude on 2015/7/12.
 */
public class RecommendPresenter extends BasePresenter<RecommendActivity> {
    private JobBrief[] jobs;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        JobModel.getInstance().getRecommendList(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getView().refreshData(jobs = data);
            }
        });
    }

    @Override
    protected void onCreateView(RecommendActivity view) {
        super.onCreateView(view);
        getView().refreshData(jobs);
    }
}
