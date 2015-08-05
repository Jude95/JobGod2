package com.ant.jobgod.jobgod.model.bizbean;

/**
 * Created by alien on 2015/8/5.
 */
public class JobPage {

    private int curPage;
    private Job[] data;
    private String totalCount;

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setData(Job[] data) {
        this.data = data;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurPage() {
        return curPage;
    }

    public Job[] getData() {
        return data;
    }

    public String getTotalCount() {
        return totalCount;
    }


}
