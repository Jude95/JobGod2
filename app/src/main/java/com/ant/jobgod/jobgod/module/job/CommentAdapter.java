package com.ant.jobgod.jobgod.module.job;

import android.content.Context;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.model.bean.Comment;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentAdapter extends RecyclerArrayAdapter<Comment> {

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CommentViewHolder(viewGroup);
    }

}
