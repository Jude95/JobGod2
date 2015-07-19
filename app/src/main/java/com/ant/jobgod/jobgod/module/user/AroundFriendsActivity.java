package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.app.BaseRecyclerActivity;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBrief;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
@RequiresPresenter(AroundFriendsPresenter.class)
public class AroundFriendsActivity extends BaseRecyclerActivity<AroundFriendsPresenter,AroundPersonBrief> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRefreshAble();
        setLoadMoreAble();
    }

    @Override
    protected void onRefresh() {
        getPresenter().refresh();
    }

    @Override
    protected void onLoadMore() {
        getPresenter().loadMore();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new AroundPersonBriefViewHolder(parent);
    }
}
