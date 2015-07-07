package com.ant.jobgod.jobgod.module.job;

import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

class JobViewHolder extends BaseViewHolder<JobBrief> {
    private SimpleDraweeView sdvHotJobImg;
    private TextView tvTitle;
    private TextView tvMoneyIntro;

    public JobViewHolder(ViewGroup parent, @LayoutRes int res) {
        super(parent, res);
        sdvHotJobImg= (SimpleDraweeView) itemView.findViewById(R.id.sdvHotJobImg);
        tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        tvMoneyIntro= (TextView) itemView.findViewById(R.id.tvMoneyIntro);
    }

    public JobViewHolder(ViewGroup parent) {
        super(parent, R.layout.job_item_brief);
        sdvHotJobImg= (SimpleDraweeView) itemView.findViewById(R.id.sdvHotJobImg);
        tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        tvMoneyIntro= (TextView) itemView.findViewById(R.id.tvMoneyIntro);
    }

    @Override
    public void setData(JobBrief data) {
        sdvHotJobImg.setImageURI(Uri.parse(data.getImg()));
        tvTitle.setText(data.getTitle());
        tvMoneyIntro.setText(data.getMoneyIntro());
    }
}