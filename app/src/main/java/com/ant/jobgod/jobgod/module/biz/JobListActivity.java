package com.ant.jobgod.jobgod.module.biz;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bizbean.Job;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/8/4.
 */
@RequiresPresenter(JobListPresenter.class)
public class JobListActivity extends BeamListActivity<JobListPresenter,Job> {

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new PublishViewHolder(viewGroup);
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
