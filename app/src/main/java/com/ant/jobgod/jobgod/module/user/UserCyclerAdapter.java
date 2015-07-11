package com.ant.jobgod.jobgod.module.user;

import android.content.Context;
import android.net.Uri;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.util.BaseViewHolder;
import com.ant.jobgod.jobgod.util.RecyclerArrayAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/10.
 */
public class UserCyclerAdapter extends RecyclerArrayAdapter<PersonBrief> {


    public UserCyclerAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonBriefViewHolder(parent);
    }


    class PersonBriefViewHolder extends BaseViewHolder<PersonBrief> {

        @InjectView(R.id.imgFace)
        SimpleDraweeView imgFace;
        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvSignature)
        TextView tvSignature;

        public PersonBriefViewHolder(ViewGroup parent) {
            super(parent, R.layout.user_item_brief);
            ButterKnife.inject(getContext(),itemView);
        }

        @Override
        public void setData(PersonBrief data) {
            super.setData(data);
            imgFace.setImageURI(Uri.parse(data.getFace()));
            tvName.setText(data.getName());
            tvSignature.setText(data.getSign());
        }
    }

}
