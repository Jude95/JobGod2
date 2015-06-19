package com.ant.jobgod.jobgod.module.launch;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
@RequiresPresenter(LoginPresenter.class)
public class LoginActivity extends BaseActivity<LoginPresenter> implements View.OnFocusChangeListener {

    private android.widget.EditText etNumber;
    private android.widget.EditText etPassword;
    private android.widget.ImageView imgShow;
    private ValueAnimator mAnimator;
    private LinearLayout viewBackground;
    private android.widget.Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_login);
        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.viewBackground = (LinearLayout) findViewById(R.id.viewBackground);
        this.imgShow = (ImageView) findViewById(R.id.imgShow);
        this.etPassword = (EditText) findViewById(R.id.etPassword);
        this.etNumber = (EditText) findViewById(R.id.etNumber);
        viewBackground.setOnClickListener(view -> view.requestFocus());
        etNumber.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etPassword.setText("");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnLogin.setOnClickListener((View v)->getPresenter().login(etNumber.getText().toString(),etPassword.getText().toString()));
        etNumber.setOnFocusChangeListener(this);
        etPassword.setOnFocusChangeListener(this);
        imgShow.post(() -> initAnimator());
    }

    private boolean isShrink = false;
    private void initAnimator(){
        mAnimator = ValueAnimator.ofInt(imgShow.getHeight(),0);
        mAnimator.setDuration(300);
        mAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        mAnimator.addUpdateListener(animation -> imgShow.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        (Integer) animation.getAnimatedValue())));
    }


    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if(hasFocus&&!isShrink){
            isShrink = true;
            Utils.Log("start");
            mAnimator.start();
        }else{
            ((InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(v.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            if(!etNumber.hasFocus()&&!etPassword.hasFocus()&&isShrink){
                isShrink = false;
                Utils.Log("reverse");
                mAnimator.reverse();
            }
        }
    }
}
