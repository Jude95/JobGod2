package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
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


    @InjectView(R.id.modifyInfo)
    TextView modifyInfo;
    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.imgBgFace)
    SimpleDraweeView imgBgFace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_user_detail);
        ButterKnife.inject(this);

        modifyInfo.setOnClickListener(v -> startActivity(new Intent(UserDetailActivity.this, UserInfoModifyActivity.class)));
        imgFace.setImageURI(Uri.parse("http://image.baidu.com/search/index?tn=baiduimage&ipn=r&ct=201326592&cl=2&lm=-1&st=-1&fm=result&fr=&sf=1&fmq=1435825300259_R&pv=&ic=0&nc=1&z=&se=1&showtab=0&fb=0&width=&height=&face=0&istype=2&itg=0&ie=utf-8&word=%E5%A4%B4%E5%83%8F#z=0&pn=&ic=0&st=-1&face=0&s=0&lm=-1"));

    }

    public void setUserDetailData(UserDetail data) {
    }

}
