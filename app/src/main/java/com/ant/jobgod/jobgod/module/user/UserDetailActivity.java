package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(UserDetailPresenter.class)
public class UserDetailActivity extends BaseActivity<UserDetailPresenter> {


    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.tvSignature)
    TextView tvSignature;
    @InjectView(R.id.tvGender)
    TextView tvGender;
    @InjectView(R.id.tvHeight)
    TextView tvHeight;
    @InjectView(R.id.tvLocation)
    TextView tvLocation;
    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;
    @InjectView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;
    @InjectView(R.id.tvEducation)
    TextView tvEducation;
    @InjectView(R.id.tvSchool)
    TextView tvSchool;
    @InjectView(R.id.tvMajor)
    TextView tvMajor;
    @InjectView(R.id.tvAwards)
    TextView tvAwards;
    @InjectView(R.id.tvHonor)
    TextView tvHonor;
    @InjectView(R.id.tvPersonality)
    TextView tvPersonality;
    @InjectView(R.id.tvHobby)
    TextView tvHobby;
    @InjectView(R.id.tvSpeciality)
    TextView tvSpeciality;
    @InjectView(R.id.tvUserIntro)
    TextView tvUserIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_user_detail);
        ButterKnife.inject(this);


    }


    public void setUserDetailData(UserDetail data) {
        collapsingToolbar.setTitle(data.getName());
        imgFace.setImageURI(Uri.parse(data.getFace()));
        tvName.setText(data.getRealName());
        tvGender.setText(data.getGender());
        tvHeight.setText(data.getHeight());
        tvLocation.setText(data.getAddress());
        tvEducation.setText(data.getEduLevel());
        tvSchool.setText(data.getSchool());
        tvMajor.setText(data.getMajor());
        tvAwards.setText(data.getAward());
        tvHonor.setText(data.getCertificate());
        tvPersonality.setText(data.getCharacter());
        tvHobby.setText(data.getLike());
        tvSpeciality.setText(data.getSpecialty());
        tvUserIntro.setText(data.getIntro());

    }

}
