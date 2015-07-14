package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/13.
 */
public class CollectPresenter extends BasePresenter<CollectActivity> {

    private JobBrief[] jobBriefs;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        JobModel.getInstance().getRecommendList(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getView().refreshData(jobBriefs = data);
            }
        });

    }

    @Override
    protected void onCreateView(CollectActivity view) {
        super.onCreateView(view);
        if(jobBriefs!=null){
            getView().refreshData(jobBriefs);
        }
    }



}
