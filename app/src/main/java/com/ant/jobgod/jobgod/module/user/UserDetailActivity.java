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
        collapsingToolbar.setTitle("个人信息");

        imgBgFace.setImageURI(Uri.parse("http://img4.imgtn.bdimg.com/it/u=2205791892,1328528914&fm=23&gp=0.jpg"));
    }

    public void setUserDetailData(UserDetail data) {
    }

}
