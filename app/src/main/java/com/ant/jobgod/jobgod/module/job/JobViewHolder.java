package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.ant.jobgod.jobgod.util.RecentDateFormater;
import com.ant.jobgod.jobgod.util.RecentShortDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;

class JobViewHolder extends BaseViewHolder<JobBrief> {
    private SimpleDraweeView sdvHotJobImg;
    private TextView tvTitle;
    private TextView tvMoneyIntro;
    private TextView tvAddress;
    private TextView tvDate;
    private MaterialRippleLayout ripple;

    public JobViewHolder(ViewGroup parent) {
        super(parent, R.layout.job_item_brief);
        sdvHotJobImg= (SimpleDraweeView) itemView.findViewById(R.id.sdvHotJobImg);
        tvTitle= (TextView) itemView.findViewById(R.id.tvTitle);
        tvMoneyIntro= (TextView) itemView.findViewById(R.id.tvMoneyIntro);
        tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
        tvDate = (TextView) itemView.findViewById(R.id.tvDate);
        ripple= (MaterialRippleLayout) itemView.findViewById(R.id.ripple);
    }

    @Override
    public void setData(JobBrief data) {
        sdvHotJobImg.setImageURI(Uri.parse(data.getImg()));
        tvTitle.setText(data.getTitle());
        tvMoneyIntro.setText(data.getMoneyIntro());
        tvDate.setText(new TimeTransform(data.getApplyBeginTime()).toString(new RecentShortDateFormater()));
        ripple.setRippleColor(ripple.getContext().getResources().getColor(R.color.Grey));
        ripple.setOnClickListener(v -> {
            Intent intent=new Intent(ripple.getContext(), JobDetailActivity.class);
            intent.putExtra("id",data.getId());
            ripple.getContext().startActivity(intent);
        });
    }

}