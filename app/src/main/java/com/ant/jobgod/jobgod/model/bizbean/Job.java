package com.ant.jobgod.jobgod.model.bizbean;

public class Job {

    private int id;
    private String img;
    private String visitCount;
    private String bizName;
    private String applyCount;
    private String moneyIntro;
    private String collectCount;
    private String title;
    private String applyBeginTime;
    private String status;

    //报名列表单独持有
    private String intro;
    private String ask;
    private User[] applyUsers;

    public String getIntro() {
        return intro;
    }

    public String getAsk() {
        return ask;
    }

    public User[] getApplyUsers() {
        return applyUsers;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setVisitCount(String visitCount) {
        this.visitCount = visitCount;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public void setApplyCount(String applyCount) {
        this.applyCount = applyCount;
    }

    public void setMoneyIntro(String moneyIntro) {
        this.moneyIntro = moneyIntro;
    }

    public void setCollectCount(String collectCount) {
        this.collectCount = collectCount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setApplyBeginTime(String applyBeginTime) {
        this.applyBeginTime = applyBeginTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public String getVisitCount() {
        return visitCount;
    }

    public String getBizName() {
        return bizName;
    }

    public String getApplyCount() {
        return applyCount;
    }

    public String getMoneyIntro() {
        return moneyIntro;
    }

    public String getCollectCount() {
        return collectCount;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getApplyBeginTime() {
        return applyBeginTime;
    }

    public String getStatus() {
        return status;
    }
}