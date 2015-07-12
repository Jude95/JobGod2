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
 * Created by alien on 2015/7/12.
 */
public class UserAdapter extends RecyclerArrayAdapter<PersonBrief> {

    public UserAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new UserViewHolder(parent);
    }

    class UserViewHolder extends BaseViewHolder<PersonBrief> {

        @InjectView(R.id.imgFace)
        SimpleDraweeView imgFace;
        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvSignature)
        TextView tvSignature;

        /**
         * 重载构造方法
         *
         * @param parent
         */
        public UserViewHolder(ViewGroup parent) {
            super(parent, R.layout.user_item_brief);
            ButterKnife.inject(itemView.getContext(),itemView);
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
