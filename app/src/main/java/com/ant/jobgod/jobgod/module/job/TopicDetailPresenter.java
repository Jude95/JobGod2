package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by alien on 2015/7/10.
 */
public class TopicDetailPresenter extends BeamListActivityPresenter<TopicDetailActivity,JobBrief> {
    private Topic topic;
    @Override
    protected void onCreate(TopicDetailActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        topic = (Topic) getView().getIntent().getSerializableExtra("topic");
        JobModel.getInstance().getTopicJobList(topic.getId(), new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getAdapter().addAll(data);
            }
        });
    }

    @Override
    protected void onCreateView(TopicDetailActivity view) {
        super.onCreateView(view);
        getView().setTopic(topic);
    }
}
