package com.ant.jobgod.jobgod.model.bean;

import java.io.Serializable;

/**
 * Created by alien on 2015/7/10.
 */
public class UserDetail implements Serializable{

    private long birthday;
    private int gender;
    private String name;
    private String sign;
    private String face;
    private String specialty;
    private String eduLevel;
    private String like;
    private String certificate;
    private String character;
    private String award;
    private String major;
    private String school;
    private String intro;
    private String address;
    private int id;
    private int height;
    private int avgAbility;
    private int avgAttitude;
    private int avgCredit;
    private boolean focus;


    public String getName() {
        return name;
    }

    public String getSign() {
        return sign;
    }

    public String getFace() {
        return face;
    }

    public String getAddress() {
        return address;
    }

    public long getBirthday() {
        return birthday;
    }

    public int getGender() {
        return gender;
    }

    public String getSpecialty() {
        return specialty;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public String getLike() {
        return like;
    }

    public String getCertificate() {
        return certificate;
    }

    public String getCharacter() {
        return character;
    }

    public String getAward() {
        return award;
    }

    public String getMajor() {
        return major;
    }

    public String getSchool() {
        return school;
    }

    public String getIntro() {
        return intro;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getAvgAbility() {
        return avgAbility;
    }

    public int getAvgAttitude() {
        return avgAttitude;
    }

    public int getAvgCredit() {
        return avgCredit;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setAvgAbility(int avgAbility) {
        this.avgAbility = avgAbility;
    }

    public void setAvgAttitude(int avgAttitude) {
        this.avgAttitude = avgAttitude;
    }

    public void setAvgCredit(int avgCredit) {
        this.avgCredit = avgCredit;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }



    public void setIsAttention(boolean isAttention){

    }


}
