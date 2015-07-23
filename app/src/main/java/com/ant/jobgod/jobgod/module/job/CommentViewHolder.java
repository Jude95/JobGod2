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

/**
 * Created by alien on 2015/7/20.
 */
public class CommentViewHolder extends BaseViewHolder<Comment> {

    private SimpleDraweeView face;
    private TextView name;
    private TextView date;
    private TextView content;

    public CommentViewHolder(ViewGroup parent) {
        super(parent, R.layout.job_item_comment);
        face = (SimpleDraweeView) itemView.findViewById(R.id.face);
        name = (TextView) itemView.findViewById(R.id.name);
        date = (TextView) itemView.findViewById(R.id.date);
        content = (TextView) itemView.findViewById(R.id.content);
    }

    @Override
    public void setData(Comment data) {
        super.setData(data);

        face.setImageURI(Uri.parse(data.getFace()));
        name.setText(data.getName());
        date.setText(new TimeTransform(data.getTime()).toString(new RecentDateFormater()));
        content.setText(data.getContent());
    }
}
