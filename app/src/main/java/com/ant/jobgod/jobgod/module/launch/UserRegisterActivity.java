package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.AccountModel;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/1/27.
 */
@RequiresPresenter(UserRegisterPresenter.class)
public class UserRegisterActivity extends BaseActivity<UserRegisterPresenter> {



    private android.support.design.widget.TextInputLayout tilName;
    private android.support.design.widget.TextInputLayout tilNumber;
    private android.support.design.widget.TextInputLayout tilPassword;
    private CheckBox checkbox;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_register);
        this.button = (AppCompatButton) findViewById(R.id.button);
        this.checkbox = (CheckBox) findViewById(R.id.checkbox);
        this.tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        this.tilNumber = (TextInputLayout) findViewById(R.id.tilNumber);
        this.tilName = (TextInputLayout) findViewById(R.id.tilName);
        launchMode(getIntent());
        button.setOnClickListener((View v)->check());
        AccountModel.getInstance().test();
        AccountModel.getInstance().test2();
    }

    private void launchMode(Intent intent){
        if (intent.getBooleanExtra("finish",false)){
            finish();
        }
    }

    public void checkbox(View view){
        if (checkbox.isChecked()) {
            button.setEnabled(true);
        }else{
            button.setEnabled(false);
        }
    }


    public void check(){
        String mName = tilName.getEditText().getText().toString();
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        if (mName.trim().isEmpty()) {
            //Utils.Toast("请输入昵称");
            tilName.setError("昵称不能为空");
            return;
        }
        if(mNumber.length()!=11){
            //Utils.Toast("请输入11位手机号");
            tilNumber.setError("手机号格式错误");
            return;
        }
        if(mPassword.length()<6||mPassword.length()>12){
            //Utils.Toast("请输入6-12位密码");
            tilPassword.setError("密码应为6-12位");
            return;
        }
        getPresenter().checkIsRegister(mName,mNumber,mPassword);
    }

    public void setNumberDuplicate() {
        tilNumber.setError("手机号已注册");
    }


    public void agreement(View view){
        startActivity(new Intent(this,AgreementActivity.class));
    }




}
