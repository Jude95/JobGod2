package com.ant.jobgod.jobgod.module.job;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentViewHolder extends BaseViewHolder<Comment> {


    @InjectView(R.id.face)
    SimpleDraweeView face;
    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.date)
    TextView date;
    @InjectView(R.id.content)
    TextView content;

    public CommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.job_activity_comment);
        ButterKnife.inject(this.itemView);
    }

    @Override
    public void setData(Comment data) {
        super.setData(data);
        face.setImageURI(Uri.parse(data.getFace()));
        name.setText(data.getName());
        date.setText(new TimeTransform(data.getDate()).toString(new RecentDateFormater()));
        content.setText(data.getContent());
    }
}
