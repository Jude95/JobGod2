package com.ant.jobgod.jobgod.module.job;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/1.
 */
@RequiresPresenter(JobDetailPresenter.class)
public class JobDetailActivity extends BaseActivity<JobDetailPresenter> {
    @InjectView(R.id.imgJobImg)
    SimpleDraweeView imgJobImg;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_jobdetail);
        ButterKnife.inject(this);
        collapsingToolbar.setTitle("I remember you");
        imgJobImg.setImageURI(Uri.parse("http://img.hb.aicdn.com/067e64a26ec3eff6a258606d3a75c80485baa77260982-YVOQrx_fw658"));
    }
}
