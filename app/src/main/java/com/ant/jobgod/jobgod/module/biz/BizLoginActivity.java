package com.ant.jobgod.jobgod.module.biz;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/8/4.
 */
@RequiresPresenter(BizLoginPresenter.class)
public class BizLoginActivity extends BaseActivity<BizLoginPresenter> {


    @InjectView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @InjectView(R.id.btnModifyPassword)
    AppCompatButton btnModifyPassword;
    @InjectView(R.id.btnLogin)
    AppCompatButton btnLogin;
    @InjectView(R.id.tilEmail)
    TextInputLayout tilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.biz_activity_login);
        ButterKnife.inject(this);

        String email=tilEmail.getEditText().getText().toString();
        String password=tilPassword.getEditText().getText().toString();

        btnLogin.setOnClickListener(v -> getPresenter().gotoBiz(email,password));
    }

    public void setEmailError(){
        tilEmail.setError("邮件地址错误");
    }

    public void setPasswordError(){
        tilPassword.setError("密码错误");
    }
}
