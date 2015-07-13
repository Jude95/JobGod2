package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.module.main.PersonDetailAcitivity;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

class UserBriefViewHolder extends BaseViewHolder<PersonBrief> {

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
    public UserBriefViewHolder(ViewGroup parent) {
        super(parent, R.layout.user_item_brief);

        ButterKnife.inject(itemView.getContext(), itemView);
    }

    @Override
    public void setData(PersonBrief data) {
        super.setData(data);
        imgFace.setImageURI(Uri.parse(data.getFace()));
        tvName.setText(data.getName());
        tvSignature.setText(data.getSign());
        ripple.setOnClickListener(v -> itemView.getContext().startActivity(new Intent(itemView.getContext(), PersonDetailAcitivity.class)));
    }
}
