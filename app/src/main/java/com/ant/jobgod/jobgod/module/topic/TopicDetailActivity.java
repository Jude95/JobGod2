package com.ant.jobgod.jobgod.module.topic;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.module.job.JobBriefAdapter;
import com.ant.jobgod.jobgod.util.RecyclerArrayAdapter;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(TopicDetailPresenter.class)
public class TopicDetailActivity extends BaseActivity<TopicDetailPresenter> {


    @InjectView(R.id.imgTopic)
    SimpleDraweeView imgTopic;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.recyclerJobBrief)
    RecyclerView recyclerJobBrief;


    private TopicHeaderView headerView;

    private JobBriefAdapter jobBriefAdapter;

    private Intent intent;

    private Topic topic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topic_activity_topic_detail);
        ButterKnife.inject(this);
        intent = getIntent();
        topic = (Topic) intent.getSerializableExtra("topic");
        init();

    }

    public void init() {
        headerView = new TopicHeaderView();
        recyclerJobBrief.setLayoutManager(new LinearLayoutManager(this));
        jobBriefAdapter = new JobBriefAdapter(this);
        jobBriefAdapter.addHeader(headerView);
        recyclerJobBrief.setAdapter(jobBriefAdapter);
        imgTopic.setImageURI(Uri.parse(topic.getImg()));
        collapsingToolbar.setTitle(topic.getTitle());
    }

    public void setJobBriefData(JobBrief[] data) {
        jobBriefAdapter.clear();
        jobBriefAdapter.addAll(data);
    }


    class TopicHeaderView implements RecyclerArrayAdapter.HeaderView {

        @Override
        public View onCreateView(ViewGroup parent) {
            TextView textView = new TextView(TopicDetailActivity.this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(72)));
            textView.setPadding(Utils.dip2px(16), Utils.dip2px(16), Utils.dip2px(16), Utils.dip2px(16));
            textView.setId(R.id.text);
            View divider = new View(TopicDetailActivity.this);
            divider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(1)));
            divider.setBackgroundColor(R.color.md_divider_white);
            LinearLayout linearLayout = new LinearLayout(TopicDetailActivity.this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(textView);
            linearLayout.addView(divider);
            return linearLayout;
        }

        @Override
        public void onBindView(View headerView) {
            TextView textView = (TextView) headerView.findViewById(R.id.text);
            textView.setText(topic.getSubTitle());
        }
    }

}
