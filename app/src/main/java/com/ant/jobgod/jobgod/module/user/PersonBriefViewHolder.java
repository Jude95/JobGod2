package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

class PersonBriefViewHolder extends BaseViewHolder<PersonBrief> {

    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvSignature)
    TextView tvSignature;
    @InjectView(R.id.ripple)
    MaterialRippleLayout ripple;

    /**
     * 重载构造方法
     *
     * @param parent
     */
    public PersonBriefViewHolder(ViewGroup parent) {
        super(parent, R.layout.user_item_person);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void setData(PersonBrief data) {
        super.setData(data);
        imgFace.setImageURI(Uri.parse(data.getFace()));
        tvName.setText(data.getName());
        tvSignature.setText(data.getSign());
        ripple.setOnClickListener(v -> {
            Intent i = new Intent(itemView.getContext(), UserDetailActivity.class);
            i.putExtra("id",data.getUID());
            itemView.getContext().startActivity(i);
        });
    }
}
