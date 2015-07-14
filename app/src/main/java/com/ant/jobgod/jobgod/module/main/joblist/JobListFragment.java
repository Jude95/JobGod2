package com.ant.jobgod.jobgod.module.main.joblist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
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
        setHasOptionsMenu(true);
        return rootView;
    }

    public void stopRefresh() {
        mJobAdapter.clear();
    }

    public void stopLoadMore() {
        listJob.hideMoreProgress();
    }

    public void startRefresh(){
        listJob.getSwipeToRefresh().setRefreshing(true);
    }

    public void addJob(JobBrief[] jobs) {
        mJobAdapter.addAll(jobs);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_job,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.filtrate){
            getPresenter().startFiltrate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
