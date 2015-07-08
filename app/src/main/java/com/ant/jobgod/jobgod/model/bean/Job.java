package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class Job {
    private String id;
    private String bizId;
    private String bizName;
    private String bizFace;
    private int bizAvgFeel;
    private int style;
    private int trade;
    private String title;
    private String intro;
    private String img;
    private String ask;
    private String moneyIntro;
    private long applyBeginTime;
    private long applyEndTime;
    private long jobBeginTime;
    private long jobEndTime;
    private int personCount;
    private int personCountIntro;
    private String address;
    private String addCode;
    private int collectCount;
    private int applyCount;
    private int visitCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getBizFace() {
        return bizFace;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }

    public int getTrade() {
        return trade;
    }

    public void setTrade(int trade) {
        this.trade = trade;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getMoneyIntro() {
        return moneyIntro;
    }

    public void setMoneyIntro(String moneyIntro) {
        this.moneyIntro = moneyIntro;
    }

    public long getApplyBeginTime() {
        return applyBeginTime;
    }

    public void setApplyBeginTime(long applyBeginTime) {
        this.applyBeginTime = applyBeginTime;
    }

    public long getApplyEndTime() {
        return applyEndTime;
    }

    public void setApplyEndTime(long applyEndTime) {
        this.applyEndTime = applyEndTime;
    }

    public long getJobBeginTime() {
        return jobBeginTime;
    }

    public void setJobBeginTime(long jobBeginTime) {
        this.jobBeginTime = jobBeginTime;
    }

    public long getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(long jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public int getPersonCountIntro() {
        return personCountIntro;
    }

    public void setPersonCountIntro(int personCountIntro) {
        this.personCountIntro = personCountIntro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddCode() {
        return addCode;
    }

    public void setAddCode(String addCode) {
        this.addCode = addCode;
    }

    public int getCollectCount() {
        return collectCount;
    }

    public void setCollectCount(int collectCount) {
        this.collectCount = collectCount;
    }

    public int getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(int applyCount) {
        this.applyCount = applyCount;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }


    public int getBizAvgFeel() {
        return bizAvgFeel;
    }

    public void setBizAvgFeel(int bizAvgFeel) {
        this.bizAvgFeel = bizAvgFeel;
    }

    public String getBizName() {

        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }
}
