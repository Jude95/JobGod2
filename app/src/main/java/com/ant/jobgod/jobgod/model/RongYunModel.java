package com.ant.jobgod.jobgod.model;

import android.content.Context;
import android.net.Uri;

import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.util.Utils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
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
        setRongYun(AccountModel.getInstance().getAccount().getTokenApp());
    }

    public void setRongYun(String token){
        try {
            RongIM.connect(token, new RongIMClient.ConnectCallback() {
                @Override
                public void onTokenIncorrect() {
                    Utils.Log("融云Token失效");
                }

                @Override
                public void onSuccess(String s) {
                    Utils.Log("融云连接成功");
                }

                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    Utils.Log("融云连接失败：" + errorCode.getValue() + ":" + errorCode.getMessage());
                }
            });

            RongIM.setUserInfoProvider(userId -> {
                PersonBrief p = PersonBriefModel.getInstance().getPersonBriefOnBlock(userId);
                return new UserInfo(p.getUID(),p.getName(), Uri.parse(p.getFace()));
            }, true);

        } catch (Exception e) {
            Utils.Log("融云出错");
        }
    }

    public void updateRongYunPersonBrief(PersonBrief p){
        RongIM.getInstance().refreshUserInfoCache(new UserInfo(p.getUID(),p.getName(), Uri.parse(p.getFace())));
    }
}
