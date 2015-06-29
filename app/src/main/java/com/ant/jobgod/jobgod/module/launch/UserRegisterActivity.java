package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/1/27.
 */
@RequiresPresenter(UserRegisterPresenter.class)
public class UserRegisterActivity extends BaseActivity<UserRegisterPresenter> {
    private TextInputLayout tilName;
    private TextInputLayout tilNumber;
    private TextInputLayout tilPassword;
    private CheckBox checkbox;
    private AppCompatButton btnMessage;
    private TextInputLayout tilCode;
    private AppCompatButton btnRetry;
    private AppCompatButton btnSend;
    private android.support.v7.widget.CardView cardMessage;
    private TextView tvAgreement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_register);
        this.tvAgreement = (TextView) findViewById(R.id.tvAgreement);
        this.cardMessage = (CardView) findViewById(R.id.cardMessage);
        this.btnSend = (AppCompatButton) findViewById(R.id.btnSend);
        this.btnRetry = (AppCompatButton) findViewById(R.id.btnRetry);
        this.tilCode = (TextInputLayout) findViewById(R.id.tilCode);
        this.btnMessage = (AppCompatButton) findViewById(R.id.btnMessage);
        this.checkbox = (CheckBox) findViewById(R.id.checkbox);
        this.tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        this.tilNumber = (TextInputLayout) findViewById(R.id.tilNumber);
        this.tilName = (TextInputLayout) findViewById(R.id.tilName);
        btnMessage.setOnClickListener((View v) -> sendMessage());
        checkbox.setOnClickListener((View v) -> checkbox());
        tvAgreement.setOnClickListener((View v) -> showAgreement());
        btnSend.setOnClickListener((View v) -> sendRegister());
        btnRetry.setOnClickListener((View v) -> getPresenter().retry());
        btnRetry.setText("重新FUCK");
    }

    public void checkbox() {
        if (checkbox.isChecked()) {
            btnMessage.setEnabled(true);
        }else{
            btnMessage.setEnabled(false);
        }
    }

    public void showAgreement() {
        startActivity(new Intent(this, AgreementActivity.class));
    }

    public void sendMessage() {
        String mName = tilName.getEditText().getText().toString();
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        if (mName.trim().isEmpty()) {
            tilName.setError("昵称不能为空");
            return;
        } else {
            tilName.setError("");
        }
        if(mNumber.length()!=11){
            tilNumber.setError("手机号格式错误");
            return;
        } else {
            tilNumber.setError("");
        }
        if(mPassword.length()<6||mPassword.length()>12){
            tilPassword.setError("密码应为6-12位");
            return;
        } else {
            tilPassword.setError("");
        }

        getPresenter().checkIsRegister(mNumber);
    }

    public void enableInfoEdit(boolean enable) {
        tilCode.getEditText().requestFocus();
        tilName.getEditText().setEnabled(enable);
        tilNumber.getEditText().setEnabled(enable);
        tilPassword.getEditText().setEnabled(enable);
        btnMessage.setEnabled(enable);
        checkbox.setEnabled(enable);
    }

    public void setNumberDuplicate() {
        tilNumber.setError("手机号已注册");
    }

    public void showCodeCard() {
        cardMessage.setVisibility(View.VISIBLE);
        enableInfoEdit(false);
    }

    public void setRetryTime(int time) {
        if (time > 0) {
            btnRetry.setText(time + "秒后重新获取");
            btnRetry.setEnabled(false);
        } else {
            btnRetry.setText("重新获取");
            btnRetry.setEnabled(true);
        }
    }

    public void sendRegister() {
        String mName = tilName.getEditText().getText().toString();
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        String mCode = tilCode.getEditText().getText().toString();
        if (mCode.trim().isEmpty()) {
            tilCode.setError("请填写验证码");
            return;
        } else {
            tilCode.setError("");
        }
        getPresenter().register(mName, mNumber, mPassword, mCode);
    }



}
