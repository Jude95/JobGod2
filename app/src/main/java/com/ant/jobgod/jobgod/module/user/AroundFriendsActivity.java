package com.ant.jobgod.jobgod.module.user;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.model.bean.AroundPersonBrief;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/7/19.
 */
@RequiresPresenter(AroundFriendsPresenter.class)
public class AroundFriendsActivity extends BeamListActivity<AroundFriendsPresenter,AroundPersonBrief> {


    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AroundPersonBriefViewHolder(parent);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setLoadmoreAble(true);
    }
}
