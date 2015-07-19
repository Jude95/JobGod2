package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class AroundPersonBriefPage {
    private int total;
    private int curPage;

    private AroundPersonBrief[] people;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public AroundPersonBrief[] getPeople() {
        return people;
    }

    public void setPeople(AroundPersonBrief[] people) {
        this.people = people;
    }
}
