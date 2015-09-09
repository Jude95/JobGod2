package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.AccountModel;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.smssdk.gui.TimeListener;

@RequiresPresenter(ModifyTelPresenter.class)
public class ModifyTelActivity extends BeamBaseActivity<ModifyTelPresenter> implements TimeListener {


    @InjectView(R.id.oldTel)
    TextInputLayout oldTel;
    @InjectView(R.id.newTel)
    TextInputLayout newTel;
    @InjectView(R.id.oldPassword)
    TextInputLayout oldPassword;
    @InjectView(R.id.newPassword)
    TextInputLayout newPassword;
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
        setContentView(R.layout.user_activity_modifytel);
        ButterKnife.inject(this);

        if (AccountModel.getInstance().getUserAccount().getTel() == null) {
            oldTel.setVisibility(View.GONE);
            oldPassword.setVisibility(View.GONE);
            btnSend.setOnClickListener(v -> sendModifyWithoutTel());
        } else{
            btnSend.setOnClickListener(v -> sendModify());
            getSupportActionBar().setTitle("切换绑定");
        }


        sendCode.setOnClickListener(v -> checkIsLogin());

        btnRetry.setOnClickListener((View v) -> getPresenter().retry());

    }

    private void checkIsLogin() {
        String oldNumber = oldTel.getEditText().getText().toString();
        String newNumber = newTel.getEditText().getText().toString();
        String oPassword=oldPassword.getEditText().getText().toString();
        String nPassword = newPassword.getEditText().getText().toString();
        if (oldNumber.length() != 11) {
            oldTel.setError("手机号格式错误");
            return;
        } else {
            oldTel.setError("");
        }

        if (newNumber.length() != 11) {
            newTel.setError("手机号格式错误");
            return;
        } else
            newTel.setError("");

        if (nPassword.length() < 6 || nPassword.length() > 12) {
            oldTel.setError("密码应为6-12位");
            return;
        } else {
            oldTel.setError("");
        }

        showCodeCard();
        getPresenter().sendCode(newNumber);
    }


    public void enableInfoEdit(boolean enable) {
        tilCode.getEditText().requestFocus();
        newTel.getEditText().setEnabled(enable);
        newPassword.getEditText().setEnabled(enable);
        oldTel.getEditText().setEnabled(enable);
        oldPassword.getEditText().setEnabled(enable);
    }


    public void showCodeCard() {
        cardMessage.setVisibility(View.VISIBLE);
        enableInfoEdit(false);
    }

    public void hideCodeCard(){
        cardMessage.setVisibility(View.INVISIBLE);
        enableInfoEdit(true);
    }

    private void sendModify() {
        String oldNumber = oldTel.getEditText().getText().toString();
        String newNumber = newTel.getEditText().getText().toString();
        String oPassword = oldPassword.getEditText().getText().toString();
        String nPassword = newPassword.getEditText().getText().toString();
        String mCode = tilCode.getEditText().getText().toString();
        if (mCode.trim().isEmpty()) {
            tilCode.setError("请填写验证码");
            return;
        } else {
            tilCode.setError("");
        }

        getPresenter().boundTel(oldNumber, newNumber, oPassword, nPassword, mCode);

    }

    public void sendModifyWithoutTel() {
        String newNumber = newTel.getEditText().getText().toString();
        String mPassword = newPassword.getEditText().getText().toString();
        String mCode = tilCode.getEditText().getText().toString();
        if (mCode.trim().isEmpty()) {
            tilCode.setError("请填写验证码");
            return;
        } else {
            tilCode.setError("");
        }

        getPresenter().boundTel("", newNumber, "", mPassword, mCode);
    }


    public void setOldPasswordError(){
        hideCodeCard();
        oldTel.setError("密码错误");
    }

    public void setTelRepeat(){
        hideCodeCard();
        newTel.setError("手机号已注册");
    }

    public void setCodeError(){
        hideCodeCard();
        tilCode.setError("验证码错误");
    }

    @Override
    public void onLastTimeNotify(int lastSecond) {
        btnRetry.setText(lastSecond + "秒后重新获取");
    }

    @Override
    public void onAbleNotify(boolean valuable) {
        btnRetry.setEnabled(valuable);
        if (valuable) btnRetry.setText("重新获取");
    }
}
