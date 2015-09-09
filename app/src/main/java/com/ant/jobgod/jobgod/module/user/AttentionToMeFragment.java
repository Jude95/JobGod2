package com.ant.jobgod.jobgod.module.user;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListFragment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

@RequiresPresenter(AttentionToMePresenter.class)
public class AttentionToMeFragment extends BeamListFragment<AttentionToMePresenter,PersonBrief> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new PersonBriefViewHolder(viewGroup);
    }
    @Override
    public int getLayout() {
        return R.layout.include_recyclerview;
    }
}
