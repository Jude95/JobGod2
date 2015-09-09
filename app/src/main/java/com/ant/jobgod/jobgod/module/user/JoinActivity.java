package com.ant.jobgod.jobgod.module.user;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.module.job.JobViewHolder;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/7/20.
 */
@RequiresPresenter(JoinPresenter.class)
public class JoinActivity extends BeamListActivity<JoinPresenter,JobBrief> {
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new JobViewHolder(parent);
    }
    @Override
    protected ListConfig getConfig() {
        return super.getConfig();
    }
}
