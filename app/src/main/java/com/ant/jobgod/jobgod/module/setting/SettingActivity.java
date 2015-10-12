package com.ant.jobgod.jobgod.module.setting;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by Mr.Jude on 2015/10/12.
 */
@RequiresPresenter(SettingPresenter.class)
public class SettingActivity extends BeamBaseActivity<SettingPresenter> {

    @InjectView(R.id.update)
    LinearLayout update;
    @InjectView(R.id.about)
    LinearLayout about;
    @InjectView(R.id.feedback)
    LinearLayout feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_main);
        ButterKnife.inject(this);
        about.setOnClickListener(v -> startActivity(new Intent(this, AboutUsActivity.class)));
        feedback.setOnClickListener(v->startActivity(new Intent(this,FeedbackActivity.class)));
        update.setOnClickListener(v-> UmengUpdateAgent.forceUpdate(this));
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int i, UpdateResponse updateResponse) {
                if (i == 1) {
                    Utils.Toast("暂无更新");
                }
            }
        });
    }
}
