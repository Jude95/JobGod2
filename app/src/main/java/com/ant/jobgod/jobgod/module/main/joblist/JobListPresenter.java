package com.ant.jobgod.jobgod.module.main.joblist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobBriefPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.common.internal.Lists;

import java.util.ArrayList;

import nucleus.manager.Presenter;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
public class JobListPresenter extends Presenter<JobListFragment> {
    private static final int FILTRATE_FLAG = 1568;
    private static final int PAGE_COUNT = 10;
    private ArrayList<JobBrief> jobs = new ArrayList<>();
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        JobModel.getInstance().getJobList(0, PAGE_COUNT, new DataCallback<JobBriefPage>() {
            @Override
            public void success(String info, JobBriefPage data) {
                getView().addJobWithRefresh(data.getJobs());
                jobs.addAll(Lists.newArrayList(data.getJobs()));
                page++;
                if ((data.getTotalCount()-1)/PAGE_COUNT <= 0){
                    getView().stopLoadMore();
                }
            }
        });
    }

    @Override
    protected void onCreateView(JobListFragment view) {
        super.onCreateView(view);
        if (jobs!=null)getView().addJob(jobs.toArray(new JobBrief[0]));
    }

    public void refresh(){
        JobModel.getInstance().getJobList(0, PAGE_COUNT, new DataCallback<JobBriefPage>() {
            @Override
            public void success(String info, JobBriefPage data) {
                jobs.clear();
                getView().addJobWithRefresh(data.getJobs());
                jobs.addAll(Lists.newArrayList(data.getJobs()));
                page = 0;
                if ((data.getTotalCount()-1)/PAGE_COUNT <= 0){
                    getView().stopLoadMore();
                }
            }
        });
    }

    public void loadMore(){
        JobModel.getInstance().getJobList(page+1, PAGE_COUNT, new DataCallback<JobBriefPage>() {
            @Override
            public void success(String info, JobBriefPage data) {
                if (data.getCurPage()==page+1){
                    getView().addJob(data.getJobs());
                    Utils.Log("Length:"+data.getJobs().length);
                    jobs.addAll(Lists.newArrayList(data.getJobs()));
                    if ((data.getTotalCount()-1)/PAGE_COUNT<= page){
                        getView().stopLoadMore();
                    }
                    page++;
                }else{

                }
            }
        });
    }

    public void startFiltrate(){
        getView().startActivityForResult(new Intent(getView().getActivity(), FiltrateActivity.class),FILTRATE_FLAG);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if (requestCode == FILTRATE_FLAG && resultCode == Activity.RESULT_OK){
            getView().startRefresh();
        }
    }
}
