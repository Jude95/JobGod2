package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
@RequiresPresenter(UserLoginPresenter.class)
public class UserLoginActivity extends BaseActivity<UserLoginPresenter> {

    private android.widget.Button btnLogin;
    private android.support.design.widget.TextInputLayout tilNumber;
    private android.support.design.widget.TextInputLayout tilPassword;
    private android.support.v7.widget.AppCompatButton btnModifyPassword;
    private android.support.v7.widget.AppCompatButton btnRegister;
    private android.widget.ImageView btnQQ;
    private android.widget.ImageView btnWeiChat;
    private android.widget.ImageView btnSina;
    private AppCompatButton btnBiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_login2);
        this.btnBiz = (AppCompatButton) findViewById(R.id.btnBiz);
        this.btnSina = (ImageView) findViewById(R.id.btnSina);
        this.btnWeiChat = (ImageView) findViewById(R.id.btnWeiChat);
        this.btnQQ = (ImageView) findViewById(R.id.btnQQ);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.btnRegister = (AppCompatButton) findViewById(R.id.btnRegister);
        this.btnModifyPassword = (AppCompatButton) findViewById(R.id.btnModifyPassword);
        this.tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        this.tilNumber = (TextInputLayout) findViewById(R.id.tilNumber);
        btnRegister.setOnClickListener((View v) -> getPresenter().register());
        btnModifyPassword.setOnClickListener((View v) -> getPresenter().modifyPassword());
        btnLogin.setOnClickListener((View v)->checkLogin());
        btnQQ.setOnClickListener((View v)->getPresenter().loginByQQ());
        btnWeiChat.setOnClickListener((View v)->getPresenter().loginByWeiChat());
        btnSina.setOnClickListener((View v)->getPresenter().loginBySina());
        btnBiz.setOnClickListener((View v)->getPresenter().gotoBiz());
    }

    private void checkLogin(){
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        if(mNumber.length()!=11){
            tilNumber.setError("手机号格式错误");
            return;
        }
        if(mPassword.length()<6||mPassword.length()>12){
            tilPassword.setError("密码应为6-12位");
            return;
        }
        getPresenter().login(mNumber,mPassword);
    }

    public void setPasswordError(){
        btnModifyPassword.setError("密码错误");
    }

    public void setNumberError(){
        btnModifyPassword.setError("手机号还没有注册");
    }
}
