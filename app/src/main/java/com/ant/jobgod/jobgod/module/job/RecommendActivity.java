package com.ant.jobgod.jobgod.module.job;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.app.BaseRecyclerActivity;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.util.BaseViewHolder;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/12.
 */
@RequiresPresenter(RecommendPresenter.class)
public class RecommendActivity extends BaseRecyclerActivity<RecommendPresenter,JobBrief> {
    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new JobViewHolder(parent);
    }
}
