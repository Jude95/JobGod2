package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.module.launch.ModifyPasswordActivity;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(UserDetailPresenter.class)
public class UserDetailActivity extends BaseActivity<UserDetailPresenter> {


    @InjectView(R.id.name)
    TextView name;
    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.signature)
    TextView signature;
    @InjectView(R.id.percent)
    TextView percent;
    @InjectView(R.id.phone)
    TextView phone;
    @InjectView(R.id.authentication)
    TextView authentication;
    @InjectView(R.id.ability)
    TextView ability;
    @InjectView(R.id.credit)
    TextView credit;
    @InjectView(R.id.attitude)
    TextView attitude;
    @InjectView(R.id.viewFace)
    RelativeLayout viewFace;
    @InjectView(R.id.viewPhone)
    RelativeLayout viewPhone;
    @InjectView(R.id.viewAuthentication)
    RelativeLayout viewAuthentication;
    @InjectView(R.id.viewModifyPassword)
    RelativeLayout viewModifyPassword;
    @InjectView(R.id.viewData)
    RelativeLayout viewData;
    @InjectView(R.id.rippleName)
    MaterialRippleLayout rippleName;
    @InjectView(R.id.rippleSignature)
    MaterialRippleLayout rippleSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_infodetail);
        ButterKnife.inject(this);

        setAllListener();

        imgFace.setImageURI(Uri.parse("http://img4.imgtn.bdimg.com/it/u=2205791892,1328528914&fm=23&gp=0.jpg"));
    }

    public void setUserDetailData(UserDetail data) {

    }

    public void setAllListener() {
        viewFace.setOnClickListener(v -> getPresenter().startAcitivity(ModifyFaceActivity.class));
        viewData.setOnClickListener(v -> getPresenter().startAcitivity(ModifyInfoActivity.class));
        viewModifyPassword.setOnClickListener(v -> getPresenter().startAcitivity(ModifyPasswordActivity.class));
        viewPhone.setOnClickListener(v -> getPresenter().startAcitivity(ModifyPhoneActivity.class));
        viewAuthentication.setOnClickListener(v -> getPresenter().startAcitivity(AuthenticationActivity.class));
        rippleName.setOnClickListener(v -> getPresenter().createEditDialog("输入昵称",8,"昵称",name));
        rippleSignature.setOnClickListener(v -> getPresenter().createEditDialog("输入签名",8,"签名",signature));
    }



}
