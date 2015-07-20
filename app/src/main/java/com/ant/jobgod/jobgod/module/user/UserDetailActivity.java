package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.util.TimeTransform;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/13.
 */
@RequiresPresenter(UserDetailPresenter.class)
public class UserDetailActivity extends BaseActivity<UserDetailPresenter> {


    @InjectView(R.id.signature)
    TextView signature;
    @InjectView(R.id.gender)
    TextView gender;
    @InjectView(R.id.height)
    TextView height;
    @InjectView(R.id.birthday)
    TextView birthday;
    @InjectView(R.id.divider)
    View divider;
    @InjectView(R.id.eduLevel)
    TextView eduLevel;
    @InjectView(R.id.school)
    TextView school;
    @InjectView(R.id.major)
    TextView major;
    @InjectView(R.id.award)
    TextView award;
    @InjectView(R.id.certificate)
    TextView certificate;
    @InjectView(R.id.character)
    TextView character;
    @InjectView(R.id.like)
    TextView like;
    @InjectView(R.id.specialty)
    TextView specialty;
    @InjectView(R.id.intro)
    TextView intro;
    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;
    @InjectView(R.id.floating_action_button)
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_persondetail);
        ButterKnife.inject(this);
        floatingActionButton.setOnClickListener(v->getPresenter().attention());
    }

    public void setIsAttention(boolean isAttention){
        floatingActionButton.setImageResource(isAttention?
                R.drawable.ic_star_focus:
                R.drawable.ic_star_unfocus);
    }

    public void setUserDetail(UserDetail detail) {
        setIsAttention(detail.isAttention());
        imgFace.setImageURI(Uri.parse(detail.getFace()));
        collapsingToolbar.setTitle(detail.getName());
        signature.setText(detail.getSign());
        gender.setText(detail.getGender());
        height.setText(detail.getHeight());
        birthday.setText(new TimeTransform(detail.getBirthday()).toString("yyyy年MM月dd日"));
        eduLevel.setText(detail.getEduLevel());
        school.setText(detail.getSchool());
        major.setText(detail.getMajor());
        award.setText(detail.getAward());
        certificate.setText(detail.getCertificate());
        character.setText(detail.getCharacter());
        like.setText(detail.getLike());
        specialty.setText(detail.getSpecialty());
        intro.setText(detail.getIntro());
    }
}
