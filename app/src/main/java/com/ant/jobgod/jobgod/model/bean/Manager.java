package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by alien on 2015/7/22.
 */
public class Manager {

    private int id;   //合同的id
    private int bizId;
    private int jobId;
    private int status;  //人的报名状态
    private String jobName;

    public int getId() {
        return id;
    }

    public String getJobName() {
        return jobName;
    }

    public int getBizId() {
        return bizId;
    }

    public int getJobId() {
        return jobId;
    }

    public int getStatus() {
        return status;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setBizId(int bizId) {
        this.bizId = bizId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
