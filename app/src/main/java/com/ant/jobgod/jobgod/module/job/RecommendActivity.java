package com.ant.jobgod.jobgod.module.job;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/7/12.
 */
@RequiresPresenter(RecommendPresenter.class)
public class RecommendActivity extends BeamListActivity<RecommendPresenter,JobBrief> {
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new JobViewHolder(parent);
    }
}