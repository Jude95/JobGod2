package com.ant.jobgod.jobgod.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.module.user.ChatActivity;
import com.ant.jobgod.jobgod.module.user.ChatListActivity;
import com.ant.jobgod.jobgod.util.Utils;

import java.util.ArrayList;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Group;
import io.rong.imlib.model.UserInfo;

/**
 * Created by Mr.Jude on 2015/7/8.
 */
public class RongYunModel extends AbsModel {

    public static RongYunModel getInstance() {
        return getInstance(RongYunModel.class);
    }

    @Override
    protected void onAppCreate(Context ctx) {
    }

    public void connectRongYun(String token){
        Utils.Log("连接是融云token:"+token);
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                Utils.Log("融云Token失效");
            }

            @Override
            public void onSuccess(String s) {
                Utils.Log("融云连接成功");
                setRongYun();
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Utils.Log("融云连接失败：" + errorCode.getValue() + ":" + errorCode.getMessage());
            }
        });
    }

    public void setRongYun(){
        try {
            RongIM.setUserInfoProvider(userId -> {
                PersonBrief p = PersonBriefModel.getInstance().getPersonBriefOnBlock(userId);
                return new UserInfo(p.getUID()+"", p.getName(), Uri.parse(p.getFace()));
            }, true);

            RongIM.getInstance().setGroupInfoProvider(new RongIM.GroupInfoProvider() {

                @Override
                public Group getGroupInfo(String groupId) {
                    //TODO
                    return null;//findGroupById()，该方法需自己实现，通过群组 Id 到你的 APP 中获取对应的群组信息返回给融云 SDK。
                }
            }, true);

            UserModel.getInstance().getJoin(new DataCallback<JobBrief[]>() {
                @Override
                public void success(String info, JobBrief[] data) {
                    List<Group> list = new ArrayList<>();
                    for (JobBrief jobBrief : data) {
                        list.add(new Group(jobBrief.getId()+"", jobBrief.getTitle(), Uri.parse(jobBrief.getImg())));
                    }
                    RongIM.getInstance().getRongIMClient().syncGroup(list, new RongIMClient.OperationCallback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode errorCode) {

                        }
                    });

                    RongIM.getInstance().setOnReceiveUnreadCountChangedListener(new RongIM.OnReceiveUnreadCountChangedListener() {
                        @Override
                        public void onMessageIncreased(int i) {
                            publicEvent(i);
                        }
                    }, Conversation.ConversationType.PRIVATE);
                }
            });
        } catch (Exception e) {
            Utils.Log("融云出错");
        }

    }


    public void updateRongYunPersonBrief(PersonBrief p){
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(p.getUID()+"",p.getName(), Uri.parse(p.getFace())));
    }

    public void chatPerson(Context ctx,String id,String title){
        Intent i = new Intent(ctx, ChatActivity.class);
        i.putExtra("id",id);
        i.putExtra("title",title);
        i.putExtra("type", Conversation.ConversationType.PRIVATE.getName().toLowerCase());
        ctx.startActivity(i);
    }

    public void chatGroup(Context ctx,String id,String title){
        Intent i = new Intent(ctx, ChatActivity.class);
        i.putExtra("id",id);
        i.putExtra("title",title);
        i.putExtra("type", Conversation.ConversationType.GROUP.getName().toLowerCase());
        ctx.startActivity(i);
    }

    public void chatList(Context ctx){
        ctx.startActivity(new Intent(ctx, ChatListActivity.class));
    }
}
