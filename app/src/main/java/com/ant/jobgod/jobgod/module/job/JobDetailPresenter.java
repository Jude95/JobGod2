package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;
import android.os.Parcelable;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Job;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/8.
 */
public class JobDetailPresenter extends BasePresenter<JobDetailActivity> {

    private Job jobDetailData;
    private Bundle bundle;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        bundle.putParcelable("data", (Parcelable) jobDetailData);
    }

    @Override
    protected void onCreateView(JobDetailActivity view) {
        super.onCreateView(view);
    }

    @Override
    protected void onTakeView(JobDetailActivity view) {
        super.onTakeView(view);
        jobDetailData=bundle.getParcelable("data");
        setDataFromBundle(jobDetailData);
    }

    public void setData(){
        JobModel.getInstance().getJobDetail(getView().getId(), new DataCallback<Job>() {
            @Override
            public void success(String info, Job data) {
                jobDetailData=data;
                getView().setData(data);
            }
        });
    }

    public void setDataFromBundle(Job data){
        getView().setData(data);
    }
}
