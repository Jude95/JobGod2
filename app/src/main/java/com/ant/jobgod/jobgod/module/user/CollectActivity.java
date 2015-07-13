package com.ant.jobgod.jobgod.module.user;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.app.BaseRecyclerActivity;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.module.job.JobViewHolder;
import com.ant.jobgod.jobgod.util.BaseViewHolder;

import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/13.
 */
@RequiresPresenter(CollectPresenter.class)
public class CollectActivity extends BaseRecyclerActivity<CollectPresenter,JobBrief> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new JobViewHolder(parent);
    }

}
