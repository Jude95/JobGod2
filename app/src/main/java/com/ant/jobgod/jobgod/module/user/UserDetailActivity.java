package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.module.launch.ModifyPasswordActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(UserDetailPresenter.class)
public class UserDetailActivity extends BaseActivity<UserDetailPresenter> {


    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.a)
    TextView a;
    @InjectView(R.id.percent)
    TextView percent;
    @InjectView(R.id.modifyInfo)
    TextView modifyInfo;
    @InjectView(R.id.tvPhone)
    TextView tvPhone;
    @InjectView(R.id.modifyPhone)
    TextView modifyPhone;
    @InjectView(R.id.tvName)
    TextView tvName;
    @InjectView(R.id.realName)
    TextView realName;
    @InjectView(R.id.modifyPassword)
    TextView modifyPassword;
    @InjectView(R.id.progerssAbility)
    ProgressBar progerssAbility;
    @InjectView(R.id.progerssIntegrity)
    ProgressBar progerssIntegrity;
    @InjectView(R.id.progerssAttitude)
    ProgressBar progerssAttitude;
    @InjectView(R.id.imgBgFace)
    SimpleDraweeView imgBgFace;
    @InjectView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.fabEdit)
    FloatingActionButton fabEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_infodetail);
        ButterKnife.inject(this);

        setAllListener();

        collapsingToolbar.setTitle("个人信息");
        imgFace.setImageURI(Uri.parse("http://img4.imgtn.bdimg.com/it/u=2205791892,1328528914&fm=23&gp=0.jpg"));
        imgBgFace.setImageURI(Uri.parse("http://img4.imgtn.bdimg.com/it/u=2205791892,1328528914&fm=23&gp=0.jpg"));
    }

    public void setUserDetailData(UserDetail data) {

    }

    public void setAllListener(){
        modifyInfo.setOnClickListener(v -> getPresenter().startAcitivity(UserInfoModifyActivity.class));
        modifyPassword.setOnClickListener(v -> getPresenter().startAcitivity(ModifyPasswordActivity.class));
        fabEdit.setOnClickListener(v -> getPresenter().startAcitivity(ModifyUserIntroAcitivity.class));
    }

}
