package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.AroundPersonBrief;
import com.ant.jobgod.jobgod.util.RecentShortDateFormater;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.Jude on 2015/7/19.
 */
public class AroundPersonBriefViewHolder extends BaseViewHolder<AroundPersonBrief> {
    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvSignature)
    TextView tvSignature;
    @InjectView(R.id.ripple)
    MaterialRippleLayout ripple;
    @InjectView(R.id.tvDate)
    TextView tvDate;
    @InjectView(R.id.tvDistance)
    TextView tvDistance;

    /**
     * 重载构造方法
     *
     * @param parent
     */
    public AroundPersonBriefViewHolder(ViewGroup parent) {
        super(parent, R.layout.user_item_around);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void setData(AroundPersonBrief data) {
        tvDate.setText(new TimeTransform(data.getAddSyncDate()).toString(new RecentShortDateFormater()));
        if(data.getDistance() /1000<1){
            tvDistance.setText(data.getDistance() + "m");
        }else{
            tvDistance.setText(String.format("%.1f", (float) data.getDistance() / 1000) + "km");
        }
        imgFace.setImageURI(Uri.parse(data.getFace()));
        tvName.setText(data.getName());
        tvSignature.setText(data.getSign());
        ripple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(itemView.getContext(), UserDetailActivity.class);
                i.putExtra("id",data.getId());
                itemView.getContext().startActivity(i);
            }
        });
    }
}
