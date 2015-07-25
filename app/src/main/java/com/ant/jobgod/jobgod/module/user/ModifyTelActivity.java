package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.CardView;
import android.view.View;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.AccountModel;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyTelPresenter.class)
public class ModifyTelActivity extends BaseActivity<ModifyTelPresenter> {


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
            newTel.getEditText().setHint("手机号");
            newPassword.getEditText().setHint("密码");
            btnSend.setOnClickListener(v -> sendModifyWithoutTel());
        } else
            btnSend.setOnClickListener(v -> sendModify());

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
    }


    public void showCodeCard() {
        cardMessage.setVisibility(View.VISIBLE);
        enableInfoEdit(false);
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

        getPresenter().boundTel(oldNumber, newNumber, nPassword, mCode);

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

        getPresenter().boundTel("", newNumber, mPassword, mCode);
    }

    public void setRetryTime(int time) {
        btnRetry.setText(time + "秒后重新获取");
    }

    public void setRetryEnable(boolean enable) {
        btnRetry.setEnabled(enable);
        if (enable) btnRetry.setText("重新获取");
    }
}
