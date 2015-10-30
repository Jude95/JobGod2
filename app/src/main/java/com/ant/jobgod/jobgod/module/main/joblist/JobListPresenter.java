package com.ant.jobgod.jobgod.module.main.joblist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobBriefPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.jude.beam.expansion.list.BeamListFragmentPresenter;


/**
 * Created by Mr.Jude on 2015/7/10.
 */
public class JobListPresenter extends BeamListFragmentPresenter<JobListFragment,JobBrief> {
    private static final int FILTRATE_FLAG = 1568;
    private static final int PAGE_COUNT = 30;
    private int page = 0;

    @Override
    protected void onCreate(JobListFragment view,Bundle savedState) {
        super.onCreate(view, savedState);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        JobModel.getInstance().getJobList(0, PAGE_COUNT, new DataCallback<JobBriefPage>() {
            @Override
            public void success(String info, JobBriefPage data) {
                getAdapter().clear();
                getAdapter().addAll(data.getJobs());
                page = 1;
            }

            @Override
            public void error(String errorInfo) {
                getView().showError();
            }
        });
    }

    @Override
    public void onLoadMore() {
        JobModel.getInstance().getJobList(page + 1, PAGE_COUNT, new DataCallback<JobBriefPage>() {
            @Override
            public void success(String info, JobBriefPage data) {
                getAdapter().addAll(data.getJobs());
                page++;
            }

            @Override
            public void error(String errorInfo) {
                getAdapter().pauseMore();
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
            onRefresh();
        }
    }
}
