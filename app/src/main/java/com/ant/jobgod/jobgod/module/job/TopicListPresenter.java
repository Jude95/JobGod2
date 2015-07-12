package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by Mr.Jude on 2015/7/12.
 */
public class TopicListPresenter extends BasePresenter<TopicListActivity> {
    private Topic[] topics;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
            @Override
            public void success(String info, Topic[] data) {
                getView().refreshData(topics = data);
            }
        });
    }

    @Override
    protected void onCreateView(TopicListActivity view) {
        super.onCreateView(view);
        getView().refreshData(topics);
    }
}
