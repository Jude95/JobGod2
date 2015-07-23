package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class AroundPersonBrief{
    private int id;
    private String name;
    private String face;
    private String sign;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFace() {
        return face;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    private long addSyncDate;
    private long distance;

    public long getAddSyncDate() {
        return addSyncDate;
    }

    public void setAddSyncDate(long addSyncDate) {
        this.addSyncDate = addSyncDate;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
