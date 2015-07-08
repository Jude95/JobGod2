package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.Job;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(JobBriefDetailPresenter.class)
public class JobBriefDetailActivity extends BaseActivity<JobBriefDetailPresenter> {

    @InjectView(R.id.promulgator_face)
    SimpleDraweeView promulgatorFace;
    @InjectView(R.id.bizName)
    TextView bizName;
    @InjectView(R.id.bizAvgFeel)
    TextView bizAvgFeel;
    @InjectView(R.id.promulgator)
    RelativeLayout promulgator;
    @InjectView(R.id.jobWage)
    TextView jobWage;
    @InjectView(R.id.jobAddress)
    TextView jobAddress;
    @InjectView(R.id.jobCount)
    TextView jobCount;
    @InjectView(R.id.applyBeginTime)
    TextView applyBeginTime;
    @InjectView(R.id.applyEndTime)
    TextView applyEndTime;
    @InjectView(R.id.jobBeginTime)
    TextView jobBeginTime;
    @InjectView(R.id.jobEndTime)
    TextView jobEndTime;
    @InjectView(R.id.timeIntro)
    TextView timeIntro;
    @InjectView(R.id.jobPostedcount)
    TextView jobPostedcount;
    @InjectView(R.id.jobIntro)
    TextView jobIntro;
    @InjectView(R.id.jobAsk)
    TextView jobAsk;
    @InjectView(R.id.jobImg)
    SimpleDraweeView jobImg;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;
    @InjectView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    private Intent intent;
    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_job_brief_detail);
        ButterKnife.inject(this);
        intent=getIntent();
        id=intent.getStringExtra("id");
    }

    public String getId(){
        return id;
    }
    public void setData(Job data){
        setTitle(data.getTitle());
        promulgatorFace.setImageURI(Uri.parse(data.getImg()));
        bizName.setText(data.getBizName());
        jobAddress.setText(data.getAddress());
        jobCount.setText(data.getApplyCount());
        applyBeginTime.setText(data.getApplyBeginTime() + "");
        applyEndTime.setText(data.getApplyCount() + "");
        jobBeginTime.setText(data.getJobBeginTime() + "");
        jobEndTime.setText(data.getJobEndTime()+"");
        jobIntro.setText(data.getIntro());
    }

}
