package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Job;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/10.
 */
public class JobDetailReleasePresenter extends BasePresenter<JobDetailReleaseActivity> {

    private String id;
    private Job mJob;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        id = getView().getIntent().getStringExtra("id");
        JobModel.getInstance().getJobDetail(id, new DataCallback<Job>() {
            @Override
            public void success(String info, Job data) {
                getView().setData(mJob=data);
            }
        });
    }

    @Override
    protected void onCreateView(JobDetailReleaseActivity view) {
        super.onCreateView(view);
        if (mJob!=null) getView().setData(mJob);

    }

    public void toCommentActivity(){
        Intent intent=new Intent(getView(),CommentActivity.class);
        intent.putExtra("id",id);
        getView().startActivity(intent);
    }
}
