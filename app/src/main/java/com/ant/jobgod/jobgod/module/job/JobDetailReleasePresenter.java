package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;

/**
 * Created by alien on 2015/7/10.
 */
public class JobDetailReleasePresenter extends BeamDataActivityPresenter<JobDetailReleaseActivity,JobDetail> {

    private int id;
    private JobDetail mJob;

    @Override
    protected void onCreate(JobDetailReleaseActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        id = getView().getIntent().getIntExtra("id",0);
        JobModel.getInstance().getJobDetail(id, new DataCallback<JobDetail>() {
            @Override
            public void success(String info, JobDetail data) {
                mJob = data;
                publishObject(data);
            }
        });
    }

    /**
     * 收藏兼职
     */
    public void collect(){
        if(AccountModel.getInstance().getUserAccount()==null){
            Utils.Toast("请先登录");
            getView().startActivity(new Intent(getView(), UserLoginActivity.class));
            return;
        }
        if (mJob.isCollected())
            JobModel.getInstance().unCollect(id, new StatusCallback() {
                @Override
                public void success(String info) {
                    Utils.Toast("已取消收藏");
                    mJob.setCollected(false);
                    getView().setIsCollected(false);
                    }
            });
        else
            JobModel.getInstance().collect(id, new StatusCallback() {
                @Override
                public void success(String info) {
                    Utils.Toast("收藏成功");
                    mJob.setCollected(true);
                    getView().setIsCollected(true
                    );
                }
            });

    }

    @Override
    protected void onCreateView(JobDetailReleaseActivity view) {
        super.onCreateView(view);
        if (mJob!=null) getView().setData(mJob);

    }

    public void toCommentActivity(){
        Intent intent=new Intent(getView(),CommentActivity.class);
        intent.putExtra("id",id);
        getView().startActivity(intent);
    }
}
