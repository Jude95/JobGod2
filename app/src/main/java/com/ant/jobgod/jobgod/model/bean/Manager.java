package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by alien on 2015/7/22.
 */
public class Manager {

    private int id; //合同的id
    private int userId;
    private long time;
    private int bizId;
    private int jobId;
    private int status;  //人的报名状态
    private int feel;
    private String jobName;
    private String ability;
    private String credit;
    private String attitude;
    private String userEva;
    private String bizEva;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public long getTime() {
        return time;
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

    public String getJobName() {
        return jobName;
    }

    public String getAbility() {
        return ability;
    }

    public String getCredit() {
        return credit;
    }

    public String getAttitude() {
        return attitude;
    }

    public int getFeel() {
        return feel;
    }

    public String getUserEva() {
        return userEva;
    }

    public String getBizEva() {
        return bizEva;
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setTime(long time) {
        this.time = time;
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

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude;
    }

    public void setFeel(int feel) {
        this.feel = feel;
    }

    public void setUserEva(String userEva) {
        this.userEva = userEva;
    }

    public void setBizEva(String bizEva) {
        this.bizEva = bizEva;
    }
}
