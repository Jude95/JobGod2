package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by Mr.Jude on 2015/7/20.
 */
public class JoinPresenter extends BasePresenter<JoinActivity> {
    private JobBrief[] jobBriefs;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        UserModel.getInstance().getJoin(new DataCallback<JobBrief[]>() {
            @Override
            public void success(String info, JobBrief[] data) {
                if (data.length == 0)getView().setNull();
                else getView().addDataWithRefresh(jobBriefs = data);
            }
        });
    }

    @Override
    protected void onCreateView(JoinActivity view) {
        super.onCreateView(view);
        if(jobBriefs!=null){
            getView().addDataWithRefresh(jobBriefs);
        }
    }
}
