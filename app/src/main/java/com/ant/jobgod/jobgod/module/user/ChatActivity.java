package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.PersonBriefModel;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.util.Utils;

import io.rong.imkit.fragment.ConversationFragment;
import nucleus.factory.RequiresPresenter;

/**
 * Created by zhuchenxi on 15/7/21.
 */
@RequiresPresenter(ChatPresenter.class)
public class ChatActivity extends BaseActivity<ChatPresenter> {
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_chat);
        id = getIntent().getStringExtra("id");
        Utils.Log("id"+id);
        ConversationFragment fragment =  (ConversationFragment)getSupportFragmentManager().findFragmentById(R.id.conversation);
        PersonBriefModel.getInstance().getPersonBrief(id, new PersonBriefModel.PersonBriefCallback() {
            @Override
            public void onCallback(PersonBrief personBrief) {
                Utils.Log(personBrief.getName());
                getSupportActionBar().setTitle(personBrief.getName());
                Uri uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversation").appendPath(io.rong.imlib.model.Conversation.ConversationType.PRIVATE.getName().toLowerCase())
                        .appendQueryParameter("targetId", personBrief.getUID()).appendQueryParameter("title", personBrief.getName()).build();
                if (fragment !=null) fragment.setUri(uri);
                else{
                    Utils.Toast("Token错误");
                    finish();
                }
            }
        });

    }
}
