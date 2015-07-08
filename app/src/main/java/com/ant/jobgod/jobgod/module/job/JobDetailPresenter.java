package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Job;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/8.
 */
public class JobDetailPresenter extends BasePresenter<JobDetailActivity> {

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setData();
    }

    @Override
    protected void onCreateView(JobDetailActivity view) {
        super.onCreateView(view);
    }

    @Override
    protected void onTakeView(JobDetailActivity view) {
        super.onTakeView(view);

    }

    public void setData(){
        JobModel.getInstance().getJobDetail(getView().getId(), new DataCallback<Job>() {
            @Override
            public void success(String info, Job data) {
                getView().setData(data);
            }
        });
    }
}
