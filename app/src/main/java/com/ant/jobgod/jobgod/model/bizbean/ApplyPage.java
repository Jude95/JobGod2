package com.ant.jobgod.jobgod.model.bizbean;

/**
 * Created by alien on 2015/8/5.
 */
public class ApplyPage {


    private int curPage;
    private int totalCount;
    private Job[] jobs;

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }


    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurPage() {
        return curPage;
    }


    public int getTotalCount() {
        return totalCount;
    }

}
