package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
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
        UserModel.getInstance().getCollection(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                getView().addDataWithRefresh(jobBriefs = data);
            }
        });

    }

    @Override
    protected void onCreateView(CollectActivity view) {
        super.onCreateView(view);
        if(jobBriefs!=null){
            getView().addDataWithRefresh(jobBriefs);
        }
    }



}
