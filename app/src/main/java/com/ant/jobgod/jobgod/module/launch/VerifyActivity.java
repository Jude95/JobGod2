package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/1/29.
 */
@RequiresPresenter(VerifyPresenter.class)
public class VerifyActivity extends BaseActivity<VerifyPresenter> {

    private TextView mTv_number;
    private EditText mEt_key;
    private Button mBtn_retry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_verify);
        mTv_number = $(R.id.number);
        mEt_key = $(R.id.key);
        mBtn_retry = $(R.id.retry);
        mTv_number.setText(getPresenter().getTel());
    }


    public void updateLastTime(int time){
        if (time > 0 && mBtn_retry!=null){
            mBtn_retry.setText(time+"秒后重新获取");
            mBtn_retry.setEnabled(false);
        }else{
            mBtn_retry.setText("重新获取");
            mBtn_retry.setEnabled(true);
        }

    }

    public void retry(View view){
        getPresenter().retry();
    }

    public void upload(View view){
        String key = mEt_key.getText().toString();
        if (key.isEmpty()){
            Utils.Toast("请输入验证码");
            return;
        }
        getPresenter().upload(key);
    }


}
