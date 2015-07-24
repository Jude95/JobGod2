package com.ant.jobgod.jobgod.model.bean;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.ant.jobgod.jobgod.model.RongYunModel;

/**
 * Created by Mr.Jude on 2015/2/11.
 */

@Table(name = "person")
public class PersonBrief extends Model {
    @Column(name = "userId",index = true)
    private int id;

    @Column(name = "userName")
    private String name;

    @Column(name = "userFace")
    private String face;

    @Column(name = "userSign")
    private String sign;

    public PersonBrief(int userId, String userName, String userFace, String userSign) {
        this.id = userId;
        this.name = userName;
        this.face = userFace;
        this.sign = userSign;
    }

    public void clone(PersonBrief person) {
        this.id = person.id;
        this.name = person.name;
        this.face = person.face;
        this.sign = person.sign;
    }

    public PersonBrief() {
    }

    public int getUID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFace() {
        return face;
    }

    public String getSign() {
        return sign;
    }

    public void saveById(){
        PersonBrief person = new Select().from(PersonBrief.class).where("userId = ?", getUID()).executeSingle();
        if (person == null){
            save();
        }else{
            person.clone(this);
            person.save();
        }
        RongYunModel.getInstance().updateRongYunPersonBrief(this);
    }


}
