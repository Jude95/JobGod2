package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;
import android.os.Parcelable;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Job;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/8.
 */
public class JobDetailPresenter extends BasePresenter<JobDetailActivity> {

    private Job jobDetailData=new Job();
    private Bundle bundle=new Bundle();
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(JobDetailActivity view) {
        super.onCreateView(view);
        setData();
        bundle.putParcelable("data", jobDetailData);
//        Utils.Log("jobDetailData:"+jobDetailData.getTitle());
    }

    @Override
    protected void onTakeView(JobDetailActivity view) {
        super.onTakeView(view);
        jobDetailData=bundle.getParcelable("data");
        if(jobDetailData!=null){
            setDataFromBundle(jobDetailData);
            Utils.Log("jobDetailData");
            getView().setData(jobDetailData);
        }
        if(jobDetailData==null){
            Utils.Log("jobDetailData==null");
        }
    }

    public void setData(){
        JobModel.getInstance().getJobDetail(getView().getId(), new DataCallback<Job>() {
            @Override
            public void success(String info, Job data) {
                if (data == null) {
                }
                else
                Utils.Log("jobdetail:"+data.getIntro());
                jobDetailData = data;
                getView().setData(data);
            }
        });
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        bundle.putParcelable("data", (Parcelable) jobDetailData);
    }

    public void setDataFromBundle(Job data){
        getView().setData(data);
    }
}
