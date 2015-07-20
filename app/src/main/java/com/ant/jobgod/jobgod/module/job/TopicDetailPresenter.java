package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/10.
 */
public class TopicDetailPresenter extends BasePresenter<TopicDetailActivity> {
    private Topic topic;
    private JobBrief[] jobBriefs;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        topic = (Topic) getView().getIntent().getSerializableExtra("topic");
        JobModel.getInstance().getTopicJobList(topic.getId(), new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getView().setJobBriefData(jobBriefs = data);
            }
        });
    }

    @Override
    protected void onCreateView(TopicDetailActivity view) {
        super.onCreateView(view);
        getView().setTopic(topic);
        if(jobBriefs!=null) getView().setJobBriefData(jobBriefs);

    }

}
