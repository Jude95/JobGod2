package com.ant.jobgod.jobgod.module.biz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bizbean.Job;
import com.ant.jobgod.jobgod.module.main.bbs.PublishFeedPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/8/4.
 */
@RequiresPresenter(PublishFeedPresenter.class)
public class JobListActivity extends BaseActivity<PublishFeedPresenter> {

    private EasyRecyclerView recyclerView;
    private PublishAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_activity_publishlist);

        recyclerView = (EasyRecyclerView) findViewById(R.id.publishList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

            }
        });
    }


    class PublishAdapter extends RecyclerArrayAdapter<Job> {

        public PublishAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
            return new PublishViewHolder(viewGroup);
        }

    }

    class PublishViewHolder extends BaseViewHolder<Job> {

        @InjectView(R.id.img)
        SimpleDraweeView img;
        @InjectView(R.id.title)
        TextView title;
        @InjectView(R.id.bizName)
        TextView bizName;
        @InjectView(R.id.applyCount)
        TextView applyCount;
        @InjectView(R.id.visitCount)
        TextView visitCount;
        @InjectView(R.id.collectCount)
        TextView collectCount;
        @InjectView(R.id.applyTime)
        TextView applyTime;

        public PublishViewHolder(ViewGroup parent) {
            super(parent, R.layout.biz_item_publish);
            ButterKnife.inject(this,itemView);
        }

        @Override
        public void setData(Job data) {
            super.setData(data);
            img.setImageURI(Uri.parse(data.getImg()));
            title.setText(data.getTitle());
            bizName.setText(data.getBizName());
            applyCount.setText("报名人数:" + data.getApplyCount());
            visitCount.setText("浏览次数:" + data.getVisitCount());
            collectCount.setText("收藏人数:" + data.getCollectCount());
            applyTime.setText("报名时间:"+data.getApplyBeginTime());
        }
    }
}
