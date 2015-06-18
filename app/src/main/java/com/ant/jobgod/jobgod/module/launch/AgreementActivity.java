package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/1/30.
 */
@RequiresPresenter(AgreementPresenter.class)
public class AgreementActivity extends BaseActivity<AgreementPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrement);
    }
}
