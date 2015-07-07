package com.ant.jobgod.jobgod.module.main;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.model.bean.JobBrief;

import java.util.List;

/**
 * Created by alien on 2015/7/7.
 */
public class JobBriefAdapter extends RecyclerView.Adapter<JobBriefAdapter.ViewHolder> {

    private List<JobBrief> data;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
    
}
