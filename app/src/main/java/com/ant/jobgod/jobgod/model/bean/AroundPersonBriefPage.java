package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class AroundPersonBriefPage {
    private int totalCount;
    private int curPage;

    private AroundPersonBrief[] people;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
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
