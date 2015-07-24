package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/8.
 */
public class JobDetailManagerPresenter extends BasePresenter<JobDetailManagerActivity> {
    private int id;
    private JobDetail mJob;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setData();
    }

    @Override
    protected void onCreateView(JobDetailManagerActivity view) {
        super.onCreateView(view);
        if (mJob != null) {
            getView().setData(mJob);
        }
    }

    public void setData() {
        id = getView().getIntent().getIntExtra("id", 0);
        JobModel.getInstance().getJobDetail(id, new DataCallback<JobDetail>() {
            @Override
            public void success(String info, JobDetail data) {
                getView().setData(mJob = data);
                getView().setRelateJobData(data.getRelative());

                if (data.isPosted()) {
                    getView().toManagerBackedge();
                } else
                    getView().applyJob();
            }
        });
    }

    /**
     * 收藏兼职
     */
    public void collect() {
        getView().setIsCollected(!mJob.isCollected());
        if (mJob.isCollected())
            JobModel.getInstance().unCollect(id, new StatusCallback() {
                @Override
                public void success(String info) {
                }

                @Override
                public void result(int status, String info) {
                    super.result(status, info);
                    switch (status){
                        case 200:
                            Utils.Toast("取消收藏");
                            break;
                    }
                }
            });
        else
            JobModel.getInstance().collect(id, new StatusCallback() {
                @Override
                public void success(String info) {
                }

                @Override
                public void result(int status, String info) {
                    super.result(status, info);
                    switch (status){
                        case 200:
                            Utils.Toast("收藏成功");
                            break;
                    }
                }
            });
        mJob.setCollected(!mJob.isCollected());
    }

    /**
     * 报名兼职
     */
    public void applyJob() {
        JobModel.getInstance().applyJob(id, new StatusCallback() {
            @Override
            public void success(String info) {

            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                switch (status) {
                    case 200:
                        Utils.Toast("报名成功");
                        Intent intent=new Intent(getView(), ManagerBackedgeActivity.class);
                        intent.putExtra("id",id);
                        getView().startActivity(intent);
                        setData();
                        break;
                    case 201:
                        Utils.Toast("未实名认证");
                        break;
                    case 202:
                        Utils.Toast("未绑定手机");
                        break;
                }
            }
        });
    }

    public void toCommentActivity() {
        Intent intent = new Intent(getView(), CommentActivity.class);
        intent.putExtra("id", id);
        getView().startActivity(intent);
    }


}
