package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;


/**
 * Created by Mr.Jude on 2015/1/30.
 */
@RequiresPresenter(AgreementPresenter.class)
public class AgreementActivity extends BeamBaseActivity<AgreementPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch_activity_agrement);
    }
}
