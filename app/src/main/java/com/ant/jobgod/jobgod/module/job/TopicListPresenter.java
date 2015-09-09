package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by Mr.Jude on 2015/7/12.
 */
public class TopicListPresenter extends BeamListActivityPresenter<TopicListActivity,Topic> {

    @Override
    protected void onCreate(TopicListActivity view,Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }


    @Override
    public void onRefresh() {
        JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
            @Override
            public void success(String info, Topic[] data) {
                getAdapter().stopMore();
                getAdapter().addAll(data);
                getAdapter().stopMore();
            }
        });
    }

}
