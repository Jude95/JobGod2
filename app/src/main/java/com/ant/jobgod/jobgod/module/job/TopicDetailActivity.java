package com.ant.jobgod.jobgod.module.job;

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
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_topic);
        ButterKnife.inject(this);
    }

    public void setTopic(Topic topic) {
        recyclerJobBrief.setLayoutManager(new LinearLayoutManager(this));
        jobBriefAdapter = new JobBriefAdapter(this);


        jobBriefAdapter.removeHeader(headerView);
        headerView = new TopicHeaderView(topic);
        jobBriefAdapter.addHeader(headerView);

        recyclerJobBrief.setAdapter(jobBriefAdapter);
        imgTopic.setImageURI(Uri.parse(topic.getImg()));
        collapsingToolbar.setTitle(topic.getTitle());
    }

    public void setJobBriefData(JobBrief[] data) {
        jobBriefAdapter.clear();
        jobBriefAdapter.addAll(data);
    }


    class TopicHeaderView implements RecyclerArrayAdapter.ItemView {
        private Topic topic;
        public TopicHeaderView(Topic topic){
            this.topic = topic;
        }

        @Override
        public View onCreateView(ViewGroup parent) {
            TextView textView = new TextView(TopicDetailActivity.this);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(Utils.dip2px(16), Utils.dip2px(16), Utils.dip2px(16), Utils.dip2px(16));
            textView.setId(R.id.text);
            textView.setText(topic.getIntro());
            View divider = new View(TopicDetailActivity.this);
            divider.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(1)));
            divider.setBackgroundColor(getResources().getColor(R.color.md_divider_white));
            LinearLayout linearLayout = new LinearLayout(TopicDetailActivity.this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.addView(textView);
            linearLayout.addView(divider);
            return linearLayout;
        }

        @Override
        public void onBindView(View headerView) {
        }
    }

}
