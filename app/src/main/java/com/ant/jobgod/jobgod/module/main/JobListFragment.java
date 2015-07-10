package com.ant.jobgod.jobgod.module.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
@RequiresPresenter(JobListPresenter.class)
public class JobListFragment extends NucleusFragment<JobListPresenter> {

    @InjectView(R.id.list_job)
    SuperRecyclerView listJob;

    private JobBriefAdapter mJobAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.main_fragment_joblist, container, false);
        ButterKnife.inject(this, rootView);
        listJob.setLayoutManager(new LinearLayoutManager(getActivity()));
        listJob.setAdapter(mJobAdapter = new JobBriefAdapter(getActivity()));
        listJob.setRefreshListener(() -> getPresenter().refresh());
        listJob.setOnMoreListener((i, i1, i2) -> getPresenter().loadMore());
        listJob.getSwipeToRefresh().setRefreshing(true);
        return rootView;
    }

    public void stopRefresh(){
        mJobAdapter.clear();
    }

    public void stopLoadMore(){
        listJob.hideMoreProgress();
    }

    public void addJob(JobBrief[] jobs){
        mJobAdapter.addAll(jobs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }
}
