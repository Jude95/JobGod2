package com.ant.jobgod.jobgod.module.main.joblist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobPage;
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

    private ArrayList<JobBrief> jobs = new ArrayList<>();
    private int page = 0;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        Utils.Log(LocationModel.getInstance().getCurLocation() == null);
        JobModel.getInstance().getJobList(0, 10, LocationModel.getInstance().getCurLocation().getRegionCode() + "", 0 + "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().addJob(data.getJobs());
                jobs.addAll(Lists.newArrayList(data.getJobs()));
                page++;
            }
        });
    }

    @Override
    protected void onCreateView(JobListFragment view) {
        super.onCreateView(view);
        if (jobs!=null)getView().addJob(jobs.toArray(new JobBrief[0]));
    }

    public void refresh(){
        JobModel.getInstance().getJobList(0, 10, LocationModel.getInstance().getCurLocation().getRegionCode() + "", 0 + "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                getView().stopRefresh();
                getView().addJob(data.getJobs());
                jobs.clear();
                jobs.addAll(Lists.newArrayList(data.getJobs()));
                page = 0;
            }
        });
    }

    public void loadMore(){
        JobModel.getInstance().getJobList(page+1, 10, LocationModel.getInstance().getCurLocation().getRegionCode() + "", 0 + "", 0, "", new DataCallback<JobPage>() {
            @Override
            public void success(String info, JobPage data) {
                if (data.getCurPage()==page+1){
                    if (data.getJobs().length==0){
                        Utils.Toast("没有更多了");
                    }
                    getView().stopRefresh();
                    getView().addJob(data.getJobs());
                    jobs.addAll(Lists.newArrayList(data.getJobs()));
                    page++;
                }else{
                    getView().stopLoadMore();
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
            refresh();
        }
    }
}
