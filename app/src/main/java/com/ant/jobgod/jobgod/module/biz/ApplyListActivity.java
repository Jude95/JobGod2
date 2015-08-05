package com.ant.jobgod.jobgod.module.biz;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bizbean.Job;
import com.ant.jobgod.jobgod.model.bizbean.User;
import com.ant.jobgod.jobgod.widget.LinearWrapContentRecyclerView;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/8/4.
 */
@RequiresPresenter(ApplyListPresenter.class)
public class ApplyListActivity extends BaseActivity<ApplyListPresenter> {

    private EasyRecyclerView applyRecycler;
    private ApplyAdapter applyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_activity_applylist);

        applyRecycler = (EasyRecyclerView)findViewById(R.id.applyList);
        applyRecycler.setLayoutManager(new LinearLayoutManager(this));
        applyRecycler.setAdapter(applyAdapter);
    }

    class ApplyAdapter extends RecyclerArrayAdapter<Job> {

        public ApplyAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ApplyViewHolder(viewGroup);
        }
    }

    class ApplyViewHolder extends BaseViewHolder<Job> {

        @InjectView(R.id.jobIcon)
        SimpleDraweeView jobIcon;
        @InjectView(R.id.jobTitle)
        TextView jobTitle;
        @InjectView(R.id.jobIntro)
        TextView jobIntro;
        @InjectView(R.id.applyCount)
        TextView applyCount;
        @InjectView(R.id.publishTime)
        TextView publishTime;
        @InjectView(R.id.linearWrap)
        LinearWrapContentRecyclerView linearWrap;
        @InjectView(R.id.jobAsk)
        TextView jobAsk;

        public ApplyViewHolder(ViewGroup parent) {
            super(parent, R.layout.biz_item_applylist);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(Job data) {
            super.setData(data);
            jobIcon.setImageURI(Uri.parse(data.getImg()));
            jobTitle.setText(data.getTitle());
            jobAsk.setText("岗位要求:" + data.getAsk());
            jobIntro.setText(data.getIntro());
            applyCount.setText(data.getApplyCount());
            publishTime.setText("发布时间:" + data.getApplyBeginTime());
            linearWrap.setAdapter(new UserAdapter(ApplyListActivity.this));
        }
    }

    class UserAdapter extends RecyclerArrayAdapter<User> {

        public UserAdapter(Context context) {
            super(context);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
            return new UserViewHolder(viewGroup);
        }
    }

    class UserViewHolder extends BaseViewHolder<User> {

        @InjectView(R.id.imgFace)
        SimpleDraweeView imgFace;
        @InjectView(R.id.tvName)
        TextView tvName;
        @InjectView(R.id.tvSignature)
        TextView tvSignature;
        @InjectView(R.id.ripple)
        MaterialRippleLayout ripple;

        public UserViewHolder(ViewGroup parent) {
            super(parent, R.layout.biz_item_user);
            ButterKnife.inject(this, itemView);
        }

        @Override
        public void setData(User data) {
            super.setData(data);
            imgFace.setImageURI(Uri.parse(data.getImg()));
            tvName.setText(data.getName());
            tvSignature.setText(data.getSign());
        }
    }
}
