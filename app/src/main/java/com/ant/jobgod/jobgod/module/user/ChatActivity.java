package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import io.rong.imkit.fragment.ConversationFragment;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/7/21.
 */
@RequiresPresenter(ChatPresenter.class)
public class ChatActivity extends BaseActivity<ChatPresenter> {
    String id;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_chat);
        id = getIntent().getData().getQueryParameter("targetId");
        title = getIntent().getData().getQueryParameter("title");
        ConversationFragment fragment =  (ConversationFragment)getSupportFragmentManager().findFragmentById(R.id.conversation);
        getSupportActionBar().setTitle(title);
        if (fragment !=null) fragment.setUri(getIntent().getData());
        else{
            finish();
        }
    }
}
