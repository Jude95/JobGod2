package com.ant.jobgod.jobgod.module.setting;

import android.view.ViewGroup;

import com.ant.jobgod.jobgod.app.BaseRecyclerActivity;
import com.ant.jobgod.jobgod.util.BaseViewHolder;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/6.
 */
@RequiresPresenter(SettingPresenter.class)
public class SettingActivity extends BaseRecyclerActivity<SettingPresenter,SettingItem> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new SettingViewHolder(parent,viewType);
    }

    @Override
    protected int getViewType(int position) {
        return adapter.getItem(position).getStyle();
    }
}
