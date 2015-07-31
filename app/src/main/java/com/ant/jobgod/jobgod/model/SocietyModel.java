package com.ant.jobgod.jobgod.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.ActivityManager;
import com.umeng.comm.core.CommunitySDK;
import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.beans.Source;
import com.umeng.comm.core.impl.CommunityFactory;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.login.LoginListener;
import com.umeng.comm.core.login.Loginable;
import com.umeng.comm.core.nets.responses.FeedItemResponse;
import com.umeng.comm.core.nets.responses.FeedsResponse;
import com.umeng.comm.core.nets.responses.SimpleResponse;
import com.umeng.comm.core.sdkmanager.LoginSDKManager;

/**
 * Created by Mr.Jude on 2015/7/29.
 */
public class SocietyModel extends AbsModel  implements Loginable {
    private LoginListener loginListener;

    private CommunitySDK communitySDK;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        communitySDK=CommunityFactory.getCommSDK(ctx);
        LoginSDKManager.getInstance().addAndUse(this);
        AccountModel.getInstance().registerEvent(this);
    }

    public static SocietyModel getInstance(){
        return getInstance(SocietyModel.class);
    }

    public void onEvent(AccountData account){
        if (loginListener == null)return;
        CommUser user = new CommUser(account.getId()+"");
        // 登录的来源.可使用值参考文档中SOURCE的取值说明
        user.source = Source.SELF_ACCOUNT;
        user.name = account.getName();
        // 如果没有头像地址，可不传递
        user.iconUrl = account.getFace();
        // 性别
        user.gender = CommUser.Gender.FEMALE;
        // 登录完成回调给社区SDK
        loginListener.onComplete(200, user);
    }

    @Override
    public void login(Context context, LoginListener loginListener) {
        // 注意用户id、昵称、source是必填项
        AccountData account = AccountModel.getInstance().getAccount();
        if (account == null){
            this.loginListener = loginListener;
            Activity act = ActivityManager.getInstance().currentActivity();
            act.startActivity(new Intent(act, UserLoginActivity.class));
            return;
        }

        CommUser user = new CommUser(account.getId()+"");
        // 登录的来源.可使用值参考文档中SOURCE的取值说明
        user.source = Source.SELF_ACCOUNT;
        user.name = account.getName();
        // 如果没有头像地址，可不传递
        user.iconUrl = account.getFace();
        // 性别
        user.gender = CommUser.Gender.FEMALE;
        // 登录完成回调给社区SDK
        loginListener.onComplete(200, user);
    }

    @Override
    public void logout(Context context, LoginListener loginListener) {
        AccountModel.getInstance().LoginOut();
    }

    @Override
    public boolean isLogined(Context context) {
        return AccountModel.getInstance().getAccount()==null;
    }

    /**
     * 获取最新的feed 列表
     * @param listener
     */
    public void getFeeds(Listeners.FetchListener<FeedsResponse> listener){
        communitySDK.fetchLastestFeeds(listener);
    }

    /**
     * 获取下一页feed数据
     * @param url
     * @param clz
     * @param listener
     */
    public void getMoreFeeds(String url, Class<FeedsResponse> clz, Listeners.FetchListener<FeedsResponse> listener){
        communitySDK.fetchNextPageData(url, clz, listener);
    }

    /**
     * 发布feed
     * @param feed
     * @param listener
     */
    public void publishFeed(FeedItem feed,Listeners.SimpleFetchListener<FeedItemResponse> listener){
        communitySDK.postFeed(feed, listener);
    }

    /**
     * 获取某条feed
     * @param id
     * @param listener
     */
    public void getFeedForId(String id,Listeners.FetchListener<FeedItemResponse> listener){
        communitySDK.fetchFeedWithId(id, listener);
    }

    /**
     * 点赞
     * @param id
     * @param listener
     */
    public void like(String id,Listeners.SimpleFetchListener<SimpleResponse> listener){
        communitySDK.postLike(id, listener);
    }

    /**
     * 取消赞
     * @param id
     * @param listener
     */
    public void unLike(String id,Listeners.SimpleFetchListener<SimpleResponse> listener){
        communitySDK.postUnLike(id, listener);
    }

    /**
     * 转发
     * @param var1
     * @param var2
     */
    public void forward(FeedItem var1, Listeners.SimpleFetchListener<FeedItemResponse> var2){
        communitySDK.forward(var1, var2);
    }

    /**
     * 发布评论
     * @param var1
     * @param var2
     */
    public void comment(com.umeng.comm.core.beans.Comment var1, Listeners.FetchListener<SimpleResponse> var2){
        communitySDK.postComment(var1, var2);
    }


}
