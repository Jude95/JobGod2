package com.ant.jobgod.jobgod.module.job;

import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

class JobViewHolder extends BaseViewHolder<JobBrief> {
    @InjectView(R.id.sdvHotJobImg)
    SimpleDraweeView sdvHotJobImg;
    @InjectView(R.id.tvTitle)
    TextView tvTitle;
    @InjectView(R.id.tvMoneyIntro)
    TextView tvMoneyIntro;

    @Override
    public void setData(JobBrief data) {
        super.setData(data);
        sdvHotJobImg.setImageURI(Uri.parse(data.getImg()));
        tvTitle.setText(data.getTitle());
        tvMoneyIntro.setText(data.getMoneyIntro());
    }

    public JobViewHolder(ViewGroup parent) {
        super(parent, R.layout.job_item_brief);
        ButterKnife.inject(this,itemView);
    }
}