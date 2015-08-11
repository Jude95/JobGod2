package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.util.Utils;

import io.rong.imkit.fragment.ConversationListFragment;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/7/21.
 */
@RequiresPresenter(ChatListPresenter.class)
public class ChatListActivity extends BaseActivity<ChatListPresenter> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_chatlist);
        ConversationListFragment fragment =  (ConversationListFragment)getSupportFragmentManager().findFragmentById(R.id.conversation);
        if (fragment !=null) fragment.setUri(getIntent().getData());
        else{
            Utils.Toast("Token错误");
            finish();
        }
    }
}
