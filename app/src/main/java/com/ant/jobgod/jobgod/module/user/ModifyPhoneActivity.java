package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyPhonePresenter.class)
public class ModifyPhoneActivity extends BaseActivity<ModifyPhonePresenter> {

    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.phone)
    TextInputLayout phone;
    @InjectView(R.id.SMScode)
    TextInputLayout SMScode;
    @InjectView(R.id.sendCode)
    Button sendCode;
    @InjectView(R.id.submit)
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyphone);
        ButterKnife.inject(this);

        imgFace.setImageURI(Uri.parse("http://img1.imgtn.bdimg.com/it/u=1571729784,3682383797&fm=11&gp=0.jpg"));
    }

}
