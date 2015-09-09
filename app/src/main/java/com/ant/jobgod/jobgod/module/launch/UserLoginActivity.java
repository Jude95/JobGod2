package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.module.biz.BizLoginActivity;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
    @RequiresPresenter(UserLoginPresenter.class)
    public class UserLoginActivity extends BeamBaseActivity<UserLoginPresenter> {

        @InjectView(R.id.tilNumber)
        TextInputLayout tilNumber;
        @InjectView(R.id.tilPassword)
        TextInputLayout tilPassword;
        @InjectView(R.id.btnModifyPassword)
        AppCompatButton btnModifyPassword;
        @InjectView(R.id.btnLogin)
        AppCompatButton btnLogin;
        @InjectView(R.id.btnQQ)
        ImageView btnQQ;
        @InjectView(R.id.btnWeiChat)
        ImageView btnWeiChat;
        @InjectView(R.id.btnSina)
        ImageView btnSina;
        @InjectView(R.id.btnRegister)
        AppCompatButton btnRegister;
        @InjectView(R.id.btnBiz)
        AppCompatButton btnBiz;

        private String mNumber;
        private String mPassword;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.launch_activity_login2);
            ButterKnife.inject(this);
            btnRegister.setOnClickListener((View v) -> getPresenter().register());
            btnModifyPassword.setOnClickListener((View v) -> getPresenter().modifyPassword());
            btnLogin.setOnClickListener((View v) -> checkLogin());
//            btnQQ.setOnClickListener((View v) -> getPresenter().loginByQQ());
//            btnWeiChat.setOnClickListener((View v) -> getPresenter().loginByWeiChat());
//            btnSina.setOnClickListener((View v) -> getPresenter().loginBySina());
            btnBiz.setOnClickListener((View v) -> startActivity(new Intent(this, BizLoginActivity.class)));
        }

        private void checkLogin() {
            mNumber = tilNumber.getEditText().getText().toString();
            mPassword = tilPassword.getEditText().getText().toString();
            if (mNumber.length() != 11) {
                tilNumber.setError("手机号格式错误");
                return;
            } else {
                tilNumber.setError("");
            }
            if (mPassword.length() < 6 || mPassword.length() > 12) {
                tilPassword.setError("密码应为6-12位");
                return;
            } else {
                tilPassword.setError("");
            }
            getPresenter().login(mNumber, mPassword);
        }

        public void setAccountError() {
            tilNumber.setError("账号不存在");
        }
        public void setPasswordError() {
            tilPassword.setError("密码错误");
        }

        public void setNumberError() {
            tilNumber.setError("手机号还没有注册");
        }

        public void setNumberAndPassword(String number,String password){
            tilNumber.getEditText().setText(number);
            tilPassword.getEditText().setText(password);
        }


    }
