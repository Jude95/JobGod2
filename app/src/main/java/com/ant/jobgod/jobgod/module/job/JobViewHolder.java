package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.util.RecentShortDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

public class JobViewHolder extends BaseViewHolder<JobBrief> {
    private SimpleDraweeView sdvHotJobImg;
    private TextView tvTitle;
    private TextView tvMoneyIntro;
    private TextView tvAddress;
    private TextView tvDate;
    private MaterialRippleLayout ripple;

    public JobViewHolder(ViewGroup parent) {
        super(parent, R.layout.job_item_job);
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
        tvDate.setText(new TimeTransform(data.getApplyBeginTime()).toString(new RecentShortDateFormater()));

        ripple.setRippleColor(ripple.getContext().getResources().getColor(R.color.Grey));
        ripple.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra("id", data.getId());
            switch (data.getSyle()) {
                case 0:
                    intent.setClass(ripple.getContext(), JobDetailReleaseActivity.class);
                    ripple.getContext().startActivity(intent);
                    break;
                case 1:
                    intent.setClass(ripple.getContext(), JobDetailManagerActivity.class);
                    ripple.getContext().startActivity(intent);
                    break;
                default:
                    intent.setClass(ripple.getContext(), JobDetailReleaseActivity.class);
                    ripple.getContext().startActivity(intent);
                    break;
            }

        });

        switch (data.getStatus()){
            case 0:
                tvMoneyIntro.setText(data.getMoneyIntro());
                break;
            case 1:
                tvMoneyIntro.setText("待审核");
                break;
            case 2:
                tvMoneyIntro.setText("报名结束");
                break;
            case 3:
                tvMoneyIntro.setText("工作中");
                break;
            case 4:
                tvMoneyIntro.setText("兼职结束");
                break;
        }

    }

}