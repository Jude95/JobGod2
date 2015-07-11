package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/11.
 */
@RequiresPresenter(AuthenticationPresenter.class)
public class AuthenticationActivity extends BaseActivity<AuthenticationPresenter> {

    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.name)
    TextInputLayout name;
    @InjectView(R.id.signature)
    TextInputLayout signature;
    @InjectView(R.id.submit)
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_authentication);
        ButterKnife.inject(this);

        imgFace.setImageURI(Uri.parse("http://img1.imgtn.bdimg.com/it/u=1571729784,3682383797&fm=11&gp=0.jpg"));
    }
}
