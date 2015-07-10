package com.ant.jobgod.jobgod.module.main;

import com.ant.jobgod.jobgod.model.CommonModel;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.Banner;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

import nucleus.manager.Presenter;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
public class RecommendPresenter extends Presenter<RecommendFragment>{

    @Override
    protected void onCreateView(RecommendFragment view) {
        super.onCreateView(view);
        JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
            @Override
            public void success(String info, Topic[] data) {
                getView().setTopic(data);
            }
        });
        JobModel.getInstance().getHotJobList(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getView().setHotJob(data);
            }
        });

        JobModel.getInstance().getJobList(0, 10, LocationModel.getInstance().getCurLocation().getRegionCode() + "", 0 + "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().setGuess(data.getJobs());
            }
        });
        CommonModel.getInstance().getBanner(new DataCallback<Banner[]>() {
            @Override
            public void success(String info, Banner[] data) {
                getView().setAd(data);
            }
        });
    }
}
