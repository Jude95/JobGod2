package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/11.
 */
@RequiresPresenter(AuthenticationPresenter.class)
public class AuthenticationActivity extends BaseActivity<AuthenticationPresenter> {


    @InjectView(R.id.imgID)
    ImageView imgID;
    @InjectView(R.id.ID)
    TextInputLayout ID;
    @InjectView(R.id.realName)
    TextInputLayout realName;
    @InjectView(R.id.submit)
    AppCompatButton submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_authentication);
        ButterKnife.inject(this);

        imgID.setOnClickListener(v -> getPresenter().getImageFromCamera());

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = ID.getEditText().getText().toString();
                String name = realName.getEditText().getText().toString();
                /**
                 * 差一个参数img
                 */
                String img = "";
                getPresenter().authentication(id, name, img);
            }
        });

    }

    public void setImg(Uri uri) {
        imgID.setImageURI(uri);
    }

}
