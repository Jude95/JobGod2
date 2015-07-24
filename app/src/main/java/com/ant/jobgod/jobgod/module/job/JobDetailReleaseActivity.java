package com.ant.jobgod.jobgod.module.job;

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
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobDetail;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.facebook.drawee.view.SimpleDraweeView;

import net.youmi.android.banner.AdSize;
import net.youmi.android.banner.AdView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(JobDetailReleasePresenter.class)
public class JobDetailReleaseActivity extends BaseActivity<JobDetailReleasePresenter> {

    @InjectView(R.id.bizFace)
    SimpleDraweeView bizFace;
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
    @InjectView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.personCountIntro)
    TextView personCountIntro;
    @InjectView(R.id.tvApply)
    TextView tvApply;
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
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;

    private MenuItem mCommentMenuItem;
    private int commentCount;


    private JobBriefAdapter relateAdapter=new JobBriefAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_detailrelease);
        ButterKnife.inject(this);
        jobImg.getHierarchy().setActualImageFocusPoint(new PointF(0.5f, 0));
        floatingActionButton.setOnClickListener(v -> getPresenter().collect());
        viewAd.addView(new AdView(this, AdSize.SIZE_468x60));
    }

    public void setIsCollected(boolean isCollected){
        floatingActionButton.setImageResource(isCollected ?
                R.drawable.ic_star_focus :
                R.drawable.ic_star_unfocus);
    }

    public void setData(JobDetail data) {
        setIsCollected(data.isCollected());
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
        personCountIntro.setText(data.getPersonCountIntro());

        relateJob.setAdapter(relateAdapter);

        setCommentCount(data.getCommentCount());
    }

    /**
     * 相关兼职推荐
     * @param jobData
     */
    public void setRelateJobData(JobBrief[] jobData){
        relateAdapter.clear();
        relateAdapter.addAll(jobData);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_job_detail, menu);
        mCommentMenuItem = menu.findItem(R.id.comment);
        mCommentMenuItem.setTitle(commentCount+"条评论");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.comment){
            getPresenter().toCommentActivity();
        }
        return super.onOptionsItemSelected(item);
    }


    public void setCommentCount(int count){
        commentCount = count;
        if (mCommentMenuItem!=null)mCommentMenuItem.setTitle(count+"条评论");
    }
}
