package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class JobBrief {
    private String id;
    private String bizName;
    private int bizAvgFeel;
    private int status;
    private String title;
    private String img;
    private String moneyIntro;
    private long applyBeginTime;
    private int syle;

    public int getSyle() {
        return syle;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    private int cityCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBizName() {
        return bizName;
    }

    public void setBizName(String bizName) {
        this.bizName = bizName;
    }

    public int getBizAvgFeel() {
        return bizAvgFeel;
    }

    public void setBizAvgFeel(int bizAvgFeel) {
        this.bizAvgFeel = bizAvgFeel;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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


}
