package com.ant.jobgod.jobgod.module.launch;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<LoginPresenter> {

    private android.widget.Button btnLogin;
    private android.support.design.widget.TextInputLayout tilNumber;
    private android.support.design.widget.TextInputLayout tilPassword;
    private android.support.v7.widget.AppCompatButton btnModifyPassword;
    private android.support.v7.widget.AppCompatButton btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_login2);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.btnRegister = (AppCompatButton) findViewById(R.id.btnRegister);
        this.btnModifyPassword = (AppCompatButton) findViewById(R.id.btnModifyPassword);
        this.tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        this.tilNumber = (TextInputLayout) findViewById(R.id.tilNumber);
        btnRegister.setOnClickListener((View v) -> getPresenter().register());
        btnModifyPassword.setOnClickListener((View v) -> getPresenter().modifyPassword());
        btnLogin.setOnClickListener((View v)->getPresenter().login(tilNumber.getEditText().getText().toString(),tilPassword.getEditText().getText().toString()));
    }

}
