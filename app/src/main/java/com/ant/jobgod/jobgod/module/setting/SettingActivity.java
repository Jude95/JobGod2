package com.ant.jobgod.jobgod.module.setting;

import android.view.ViewGroup;

import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/7/6.
 */
@RequiresPresenter(SettingPresenter.class)
public class SettingActivity extends BeamListActivity<SettingPresenter,SettingItem> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new SettingViewHolder(parent,viewType);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(false);
    }

    @Override
    public int getViewType(int position) {
        return getPresenter().getAdapter().getItem(position).getStyle();
    }
}
