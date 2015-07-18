package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyPhonePresenter.class)
public class ModifyPhoneActivity extends BaseActivity<ModifyPhonePresenter> {

    @InjectView(R.id.phone)
    TextInputLayout phone;
    @InjectView(R.id.password)
    TextInputLayout password;
    @InjectView(R.id.sendCode)
    AppCompatButton sendCode;
    @InjectView(R.id.tilCode)
    TextInputLayout tilCode;
    @InjectView(R.id.btnRetry)
    AppCompatButton btnRetry;
    @InjectView(R.id.btnSend)
    AppCompatButton btnSend;
    @InjectView(R.id.cardMessage)
    CardView cardMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyphone);
        ButterKnife.inject(this);

        sendCode.setOnClickListener(v -> checkIsLogin());
        btnSend.setOnClickListener(v -> sendModify());
        btnRetry.setOnClickListener((View v)->getPresenter().retry());

    }

    private void checkIsLogin() {
        String mNumber = phone.getEditText().getText().toString();
        String mPassword = password.getEditText().getText().toString();
        if (mNumber.length() != 11) {
            phone.setError("手机号格式错误");
            return;
        } else {
            phone.setError("");
        }
        if (mPassword.length() < 6 || mPassword.length() > 12) {
            phone.setError("密码应为6-12位");
            return;
        } else {
            phone.setError("");
        }
        showCodeCard();
//        getPresenter().checkIsRegister(mNumber);
    }


    public void enableInfoEdit(boolean enable) {
        tilCode.getEditText().requestFocus();
        phone.getEditText().setEnabled(enable);
        password.setEnabled(enable);
    }

    public void setNumberNoExist() {
        phone.setError("手机号已注册");
    }


    public void showCodeCard() {
        cardMessage.setVisibility(View.VISIBLE);
//        enableInfoEdit(false);
    }

    private void sendModify(){
        String mNumber = phone.getEditText().getText().toString();
        String mPassword = password.getEditText().getText().toString();
        String mCode = tilCode.getEditText().getText().toString();
        if (mCode.trim().isEmpty()) {
            tilCode.setError("请填写验证码");
            return;
        } else {
            tilCode.setError("");
        }
        getPresenter().sendModify(mNumber, mPassword, mCode);
    }

    public void setRetryTime(int time) {
        btnRetry.setText(time + "秒后重新获取");
    }
}
