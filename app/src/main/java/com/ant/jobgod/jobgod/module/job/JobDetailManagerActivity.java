package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.Job;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(JobDetailManagerPresenter.class)
public class JobDetailManagerActivity extends BaseActivity<JobDetailManagerPresenter> {


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
    @InjectView(R.id.bizFace)
    SimpleDraweeView bizFace;
    @InjectView(R.id.jobBeginTime)
    TextView jobBeginTime;
    @InjectView(R.id.jobEndTime)
    TextView jobEndTime;
    @InjectView(R.id.shareQQ)
    ImageView shareQQ;
    @InjectView(R.id.shareQQSpace)
    ImageView shareQQSpace;
    @InjectView(R.id.shareSina)
    ImageView shareSina;
    @InjectView(R.id.shareWeChat)
    ImageView shareWeChat;
    @InjectView(R.id.relateJob)
    LinearWrapContentRecyclerView relateJob;
    @InjectView(R.id.viewAd)
    LinearLayout viewAd;
    @InjectView(R.id.btnApply)
    android.support.v7.widget.AppCompatButton btnApply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_detailmanager);
        ButterKnife.inject(this);
        jobImg.getHierarchy().setActualImageFocusPoint(new PointF(0.5f, 0));
    }

    public void setData(Job data) {
        collapsingToolbar.setTitle(data.getTitle());
        timeIntro.setText(data.getTimeIntro());
        jobImg.setImageURI(Uri.parse(data.getImg()));
        bizFace.setImageURI(Uri.parse(data.getBizFace()));
        bizName.setText(data.getBizName());
        jobAddress.setText(data.getAddress());
        jobCount.setText(data.getApplyCount() + "人");
        applyBeginTime.setText(new TimeTransform(data.getApplyBeginTime()).toString(new RecentDateFormater()));
        applyEndTime.setText(new TimeTransform(data.getApplyEndTime()).toString(new RecentDateFormater()));
        jobIntro.setText(data.getIntro());
        jobAsk.setText(data.getAsk());
        jobWage.setText(data.getMoneyIntro());
        jobBeginTime.setText(new TimeTransform(data.getJobBeginTime()).toString(new RecentDateFormater()));
        jobEndTime.setText(new TimeTransform(data.getJobEndTime()).toString(new RecentDateFormater()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_job_discuss,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.discuss){
            startActivity(new Intent(this,));
        }
        return super.onOptionsItemSelected(item);
    }
}
