package com.ant.jobgod.jobgod.module.setting;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

/**
 * Created by Mr.Jude on 2015/3/1.
 */
@RequiresPresenter(FeedbackPresenter.class)
public class FeedbackActivity extends BeamBaseActivity<FeedbackPresenter> {
    private FeedbackFragment mFeedbackFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity_feedback);
        new FeedbackAgent(this).setWelcomeInfo("你好，我是吐槽经理,来尽情释放你的吐槽能量吧！");
        if (savedInstanceState == null) {
            String conversation_id = new FeedbackAgent(this).getDefaultConversation().getId();
            mFeedbackFragment = FeedbackFragment.newInstance(conversation_id);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container,mFeedbackFragment,"feedback")
                    .commit();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        mFeedbackFragment.refresh();
    }


}
