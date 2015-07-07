package com.ant.jobgod.jobgod.model;

import com.ant.jobgod.jobgod.model.bean.Job;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by Mr.Jude on 2015/6/6.
 * 工作的数据管理。
 */
public class JobModel extends AbsModel{
    public static JobModel getInstance(){
        return getInstance(JobModel.class);
    }

    public Trade[] getTrade(){
        return new Trade[0];
    }

    public void getHotJobList(DataCallback<Job[]> callback){
        callback.onError("");
    }

    public void getTopicList(DataCallback<Topic[]> callback){
        callback.onError("");
    }

    public void getTopicJobList(DataCallback<Job[]> callback){
        callback.onError("");
    }

    public void getJobList(int page,int count,String citycode,String type,int sort,String key,DataCallback<JobPage[]> callback){
        callback.onError("");
    }
}
