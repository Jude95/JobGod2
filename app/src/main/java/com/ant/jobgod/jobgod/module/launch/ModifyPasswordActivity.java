package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(ModifyPasswordPresenter.class)
public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> {
    private android.support.design.widget.TextInputLayout tilName;
    private android.support.design.widget.TextInputLayout tilNumber;
    private android.support.design.widget.TextInputLayout tilPassword;
    private android.support.v7.widget.AppCompatButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_modifypassword);
        this.button = (AppCompatButton) findViewById(R.id.button);
        this.tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        this.tilNumber = (TextInputLayout) findViewById(R.id.tilNumber);
        this.tilName = (TextInputLayout) findViewById(R.id.tilName);
        button.setOnClickListener((View v)->checkVerify());
    }
    private void checkVerify(){
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
        getPresenter().checkIsRegister(mNumber,mPassword);
    }
    public void setNumberNoExist(){
        tilNumber.setError("手机号未注册");
    }

}
