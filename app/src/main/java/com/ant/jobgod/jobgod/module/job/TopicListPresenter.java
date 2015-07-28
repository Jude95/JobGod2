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
    private Topic[] topics;//持有数据

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        //在OnCreate里加载数据并保存一份，再设置给View，
        // 注意里是异步,运行到这里时Activity的onCreate还没有执行。但回调时Activity的onCreate一定已经执行完。View已初始化完毕。
        JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
            @Override
            public void success(String info, Topic[] data) {
                getView().addDataWithRefresh(topics = data);
            }
        });
    }

    @Override
    protected void onCreateView(TopicListActivity view) {
        super.onCreateView(view);
        //activity每次被创建就直接把数据设置给它。而不需要再去加载一边。因此不会在横竖屏切换时重复加载。
        getView().addDataWithRefresh(topics);
    }
}
