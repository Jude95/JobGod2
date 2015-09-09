package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.bean.UserAccountData;
import com.ant.jobgod.jobgod.module.launch.ModifyPasswordActivity;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

@RequiresPresenter(UserDataPresenter.class)
public class UserDataActivity extends BeamBaseActivity<UserDataPresenter> {


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
    @InjectView(R.id.viewLoginOut)
    RelativeLayout loginOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_data);
        ButterKnife.inject(this);
        setAllListener();

    }

    public void setUserDetailData(UserAccountData data) {
        name.setText(data.getName());
        imgFace.setImageURI(Uri.parse(data.getFace()));
        signature.setText(data.getSign());
        phone.setText(data.getTel());
        switch (data.getAuthenticationStatus()){
            case 0:
                authentication.setText("未认证");
                break;
            case 1:
                authentication.setText("审核中");
                break;
            case 2:
                authentication.setText("认证失败,请重新认证");
                break;
            case 3:
                authentication.setText(data.getRealName());
                break;
        }

        percent.setText("完善度"+ calculateDetailPercent(data)+"%");
    }

    public int calculateDetailPercent(UserAccountData data){
        int value = 0;
        if(data.getDetail().getGender()==0)value+=1;
        if(data.getDetail().getHeight()==0)value+=1;
        if(data.getDetail().getBirthday()==0)value+=1;
        if(data.getDetail().getEduLevel()==0)value+=1;
        if(data.getDetail().getSchool()==null||data.getDetail().getSchool().trim().isEmpty())value+=1;
        if(data.getDetail().getMajor()==null||data.getDetail().getMajor().trim().isEmpty())value+=1;
        if(data.getDetail().getCharacter()==null||data.getDetail().getCharacter().trim().isEmpty())value+=1;
        if(data.getDetail().getLike()==null||data.getDetail().getLike().trim().isEmpty())value+=1;
        if(data.getDetail().getSpecialty()==null||data.getDetail().getSpecialty().trim().isEmpty())value+=1;
        if(data.getDetail().getIntro()==null||data.getDetail().getIntro().trim().isEmpty())value+=1;
        return 100-value*10;
    }


    public void setAllListener() {
        viewFace.setOnClickListener(v -> getPresenter().startActivity(ModifyFaceActivity.class));
        viewData.setOnClickListener(v -> getPresenter().startActivity(ModifyDetailActivity.class));
        loginOut.setOnClickListener(v -> {
            AccountModel.getInstance().UserLoginOut();
            finish();
        });
        viewModifyPassword.setOnClickListener(v -> getPresenter().startActivity(ModifyPasswordActivity.class));
        viewPhone.setOnClickListener(v -> getPresenter().startActivity(ModifyTelActivity.class));
        viewAuthentication.setOnClickListener(v -> getPresenter().checkAuthentication());
        rippleName.setOnClickListener(v -> getPresenter().editName());
        rippleSignature.setOnClickListener(v -> getPresenter().editSign());
    }


}
