package com.ant.jobgod.jobgod.module.main;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

import nucleus.manager.Presenter;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
public class JobListPresenter extends Presenter<JobListFragment> {

    @Override
    protected void onCreateView(JobListFragment view) {
        super.onCreateView(view);
        JobModel.getInstance().getJobList(0, 10, LocationModel.getInstance().getCurLocation().getRegionCode() + "", 0 + "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().addJob(data.getJobs());
            }
        });
    }
}
