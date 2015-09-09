package com.ant.jobgod.jobgod.module.job;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.beam.expansion.list.ListConfig;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

@RequiresPresenter(TopicDetailPresenter.class)
public class TopicDetailActivity extends BeamListActivity<TopicDetailPresenter,JobBrief> {


    @InjectView(R.id.imgTopic)
    SimpleDraweeView imgTopic;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;



    private TopicHeaderView headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
    }

    @Override
    public int getLayout() {
        return R.layout.job_activity_topic;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new JobViewHolder(viewGroup);
    }

    @Override
    protected ListConfig getConfig() {
        return super.getConfig().setRefreshAble(false);
    }

    public void setTopic(Topic topic) {

        getPresenter().getAdapter().removeHeader(headerView);
        headerView = new TopicHeaderView(topic);
        getPresenter().getAdapter().addHeader(headerView);
        getPresenter().getAdapter().notifyDataSetChanged();

        imgTopic.setImageURI(Uri.parse(topic.getImg()));
        collapsingToolbar.setTitle(topic.getTitle());
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
