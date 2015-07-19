package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/10.
 */
public class TopicDetailPresenter extends BasePresenter<TopicDetailActivity> {

    private JobBrief[] jobBriefs;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        JobModel.getInstance().getJobList(0, 10, new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().setJobBriefData(jobBriefs=data.getJobs());
            }
        });
    }

    @Override
    protected void onCreateView(TopicDetailActivity view) {
        super.onCreateView(view);
        if(jobBriefs!=null) getView().setJobBriefData(jobBriefs);
    }

}
