package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseRecyclerActivity;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/12.
 */
@RequiresPresenter(TopicListPresenter.class)
public class TopicListActivity extends BaseRecyclerActivity<TopicListPresenter,Topic> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return new TopicVH(parent);
    }

    private class TopicVH extends BaseViewHolder<Topic>{
        private TextView mTitle;
        private TextView mIntro;
        private SimpleDraweeView mImage;
        private Topic topic;
        public TopicVH(ViewGroup parent) {
            super(parent, R.layout.job_item_topic);
            mTitle = (TextView) itemView.findViewById(R.id.topic_title);
            mIntro = (TextView) itemView.findViewById(R.id.topic_intro);
            mImage = (SimpleDraweeView) itemView.findViewById(R.id.topic_img);
            mImage.setAspectRatio(1.5f);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(TopicListActivity.this,TopicDetailActivity.class);
                    i.putExtra("topic",topic);
                    startActivity(i);
                }
            });
        }

        @Override
        public void setData(Topic data) {
            topic = data;
            mTitle.setText(data.getTitle());
            mIntro.setText(data.getIntro());
            mImage.setImageURI(Uri.parse(data.getImg()));
        }
    }
}
