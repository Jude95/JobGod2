package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.bean.UserAccountData;
import com.ant.jobgod.jobgod.module.launch.ModifyPasswordActivity;
import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(UserDataPresenter.class)
public class UserDataActivity extends BaseActivity<UserDataPresenter> {


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
        authentication.setText(data.getRealName());
        percent.setText("完善度20%");
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
