package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.app.BaseRecyclerActivity;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/20.
 */
@RequiresPresenter(CommentPresenter.class)
public class CommentActivity extends BaseRecyclerActivity<CommentPresenter,Comment> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRefreshAble();
        setLoadMoreAble();
    }

    @Override
    protected void onRefresh() {
        super.onRefresh();
        getPresenter().refresh();
    }

    @Override
    protected void onLoadMore() {
        super.onLoadMore();
        getPresenter().loadMore();
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new CommentViewHolder(parent);
    }
}
