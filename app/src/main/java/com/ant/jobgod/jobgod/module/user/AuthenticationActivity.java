package com.ant.jobgod.jobgod.module.user;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/11.
 */
@RequiresPresenter(AuthenticationPresenter.class)
public class AuthenticationActivity extends BeamBaseActivity<AuthenticationPresenter> {


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
        submit.setOnClickListener(v -> checkToUpload());
    }

    public void setImg(Bitmap img) {
        imgID.setImageBitmap(img);
    }

    public void checkToUpload() {
        String name = realName.getEditText().getText().toString();
        String idNumber = ID.getEditText().getText().toString();
        if (name.trim().isEmpty()) {
            realName.setError("姓名不能为空");
            return;
        }
        if (idNumber.trim().length() != 18) {
            ID.setError("身份证格式错误");
            return;
        }
        getPresenter().upload(name, idNumber);
    }


}
