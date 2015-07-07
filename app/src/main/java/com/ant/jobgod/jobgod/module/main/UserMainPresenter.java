package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.CommonModel;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.Banner;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.umeng.message.PushAgent;
import com.umeng.update.UmengUpdateAgent;

/**
 * Created by zhuchenxi on 15/6/27.
 */
public class UserMainPresenter extends BasePresenter<UserMainActivity>{

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        PushAgent.getInstance(getView()).enable();
        UmengUpdateAgent.update(getView());
    }

    @Override
    protected void onCreateView(UserMainActivity view) {
        super.onCreateView(view);
        getView().setTradeData(JobModel.getInstance().getTrade());

        JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
            @Override
            public void success(String info, Topic[] data) {
                getView().setTopicData(data);
            }
        });
        JobModel.getInstance().getHotJobList(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getView().setHotJobData(data);
            }
        });

        JobModel.getInstance().getJobList(0, 10, LocationModel.getInstance().getCurLocation().getRegionCode() + "", 0 + "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().setJobBriefData(data.getJobs());
            }
        });
        CommonModel.getInstance().getBanner(new DataCallback<Banner[]>() {
            @Override
            public void success(String info, Banner[] data) {
                getView().setAdData(data);
            }
        });

    }

    public void updateData(){
        onCreateView(getView());
    }

}
