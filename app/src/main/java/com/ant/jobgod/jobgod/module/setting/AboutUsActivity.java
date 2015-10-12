package com.ant.jobgod.jobgod.module.setting;

import android.os.Bundle;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

/**
 * Created by Mr.Jude on 2015/10/12.
 */
@RequiresPresenter(AboutUsPresenter.class)
public class AboutUsActivity extends BeamBaseActivity<AboutUsPresenter> {
    private TextView tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_aboutus);
        tvInfo = $(R.id.info);
        tvInfo.setText(getString(R.string.app_name) + "Android" + Utils.getAppVersionName());
    }
}
