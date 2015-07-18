package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/6/27.
 */
@RequiresPresenter(ModifyPasswordPresenter.class)
public class ModifyPasswordActivity extends BaseActivity<ModifyPasswordPresenter> {
    private android.support.design.widget.TextInputLayout tilNumber;
    private android.support.design.widget.TextInputLayout tilPassword;
    private android.support.v7.widget.AppCompatButton btnMessage;
    private TextInputLayout tilCode;
    private AppCompatButton btnRetry;
    private AppCompatButton btnSend;
    private android.support.v7.widget.CardView cardMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_modifypassword);
        this.cardMessage = (CardView) findViewById(R.id.cardMessage);
        this.btnSend = (AppCompatButton) findViewById(R.id.btnSend);
        this.btnRetry = (AppCompatButton) findViewById(R.id.btnRetry);
        this.tilCode = (TextInputLayout) findViewById(R.id.tilCode);
        this.btnMessage = (AppCompatButton) findViewById(R.id.btnMessage);
        this.tilPassword = (TextInputLayout) findViewById(R.id.tilPassword);
        this.tilNumber = (TextInputLayout) findViewById(R.id.tilNumber);
        btnMessage.setOnClickListener((View v)->checkIsLogin());
        btnSend.setOnClickListener((View v) -> sendModify());
        btnRetry.setOnClickListener((View v)->getPresenter().retry());
    }
    private void checkIsLogin(){
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        if(mNumber.length()!=11){
            tilNumber.setError("手机号格式错误");
            return;
        }else{
            tilNumber.setError("");
        }
        if(mPassword.length()<6||mPassword.length()>12){
            tilPassword.setError("密码应为6-12位");
            return;
        }else{
            tilPassword.setError("");
        }
        getPresenter().checkIsRegister(mNumber);
    }

    public void setNumberNoExist() {
        tilNumber.setError("手机号未注册");
    }

    private void sendModify(){
        String mNumber = tilNumber.getEditText().getText().toString();
        String mPassword = tilPassword.getEditText().getText().toString();
        String mCode = tilCode.getEditText().getText().toString();
        if (mCode.trim().isEmpty()) {
            tilCode.setError("请填写验证码");
            return;
        } else {
            tilCode.setError("");
        }
        getPresenter().sendModify(mNumber, mPassword, mCode);
    }

    public void enableInfoEdit(boolean enable) {
        tilCode.getEditText().requestFocus();
        tilNumber.getEditText().setEnabled(enable);
        tilPassword.setEnabled(enable);
    }

    public void setRetryTime(int time) {
        btnRetry.setText(time + "秒后重新获取");
    }

    public void setRetryEnable(boolean enable){
        btnRetry.setEnabled(enable);
        if (enable)btnRetry.setText("重新获取");
    }

    public void showCodeCard() {
        cardMessage.setVisibility(View.VISIBLE);
        enableInfoEdit(false);
    }

}
