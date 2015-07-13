package com.ant.jobgod.jobgod.module.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/13.
 */
@RequiresPresenter(PersonDetailPresenter.class)
public class PersonDetailAcitivity extends BaseActivity<PersonDetailPresenter> {


    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.signature)
    TextView signature;
    @InjectView(R.id.gender)
    TextView gender;
    @InjectView(R.id.height)
    TextView height;
    @InjectView(R.id.address)
    TextView address;
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
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.appBar)
    AppBarLayout appBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_persondetail);
        ButterKnife.inject(this);

        imgFace.setImageURI(Uri.parse("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2584355946,4148531126&fm=116&gp=0.jpg"));
    }
}
