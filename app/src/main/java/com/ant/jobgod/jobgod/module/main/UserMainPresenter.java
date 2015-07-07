package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.Utils;
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
                Utils.Log("data:" + data);
            }
        });

        JobModel.getInstance().getJobList(0, 0, LocationModel.get, 0+"", 0, "", new DataCallback<JobPage[]>() {
            @Override
            public void success(String info, JobPage[] data) {
                getView().setJobBriefData(data[0].getJobs());
            }
        });
    }


}
