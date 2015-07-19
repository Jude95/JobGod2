package com.ant.jobgod.jobgod.model.bean;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class JobBriefPage {
    private int totalCount;
    private int curPage;

    private JobBrief[] jobs;

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

    public JobBrief[] getJobs() {
        return jobs;
    }

    public void setJobs(JobBrief[] jobs) {
        this.jobs = jobs;
    }
}
