package com.ant.jobgod.jobgod.module.launch;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/1/27.
 */
public class RegisterActivity extends BaseActivity<RegisterPresenter> {

    private EditText mEt_number;
    private EditText mEt_password;
    private CheckBox mCheckbox;
    private Button mButton;
    private String mNumber;
    private String mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        launchMode(getIntent());
        mEt_number = $(R.id.number);
        mEt_password = $(R.id.password);
        mCheckbox = $(R.id.checkbox);
        mButton = $(R.id.button);
    }

    private void launchMode(Intent intent){
        if (intent.getBooleanExtra("finish",false)){
            finish();
        }
    }

    public void checkbox(View view){
        if(mCheckbox.isChecked()){
            mButton.setEnabled(true);
        }else{
            mButton.setEnabled(false);
        }
    }

    public void check(View view){
        mNumber = mEt_number.getText().toString();
        mPassword = mEt_password.getText().toString();

        if(mNumber.length()!=11){
            Utils.Toast("请输入11位手机号");
            return;
        }
        if(mPassword.length()<6||mPassword.length()>12){
            Utils.Toast("请输入6-12位密码");
            return;
        }
        getPresenter().checkIsRegister(mNumber);

    }

    public void continueRegister(){
            Intent intent = new Intent(this, VerifyActivity.class);
            intent.putExtra("number", mNumber);
            intent.putExtra("password", mPassword);
            intent.putExtra("type", VerifyPresenter.Type_Register);
            startActivityForResult(intent, 1);
    }


    public void agreement(View view){
        startActivity(new Intent(this,AgreementActivity.class));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
            if (resultCode == RESULT_OK){
                Intent intent = new Intent();
                intent.putExtra("number",mNumber);
                intent.putExtra("password",mPassword);
                setResult(RESULT_OK,intent);
                finish();
            }
    }
}
