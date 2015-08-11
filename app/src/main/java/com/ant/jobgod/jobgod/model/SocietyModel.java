package com.ant.jobgod.jobgod.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.location.Location;

import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.util.ActivityManager;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.comm.core.CommunitySDK;
import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.beans.Source;
import com.umeng.comm.core.impl.CommunityFactory;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.login.LoginListener;
import com.umeng.comm.core.login.Loginable;
import com.umeng.comm.core.nets.responses.CommentResponse;
import com.umeng.comm.core.nets.responses.FeedItemResponse;
import com.umeng.comm.core.nets.responses.FeedsResponse;
import com.umeng.comm.core.nets.responses.LocationResponse;
import com.umeng.comm.core.nets.responses.PortraitUploadResponse;
import com.umeng.comm.core.nets.responses.SimpleResponse;

/**
 * Created by Mr.Jude on 2015/7/29.
 */
public class SocietyModel extends AbsModel  implements Loginable {

    private LoginListener loginListener;

    private CommunitySDK communitySDK;

    private CommUser commUser;

    private boolean forceLogin = false;
    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        AccountModel.getInstance().registerEvent(this);
        communitySDK= CommunityFactory.getCommSDK(ctx);
        communitySDK.getConfig().getLoginSDKManager().addAndUse(this);
    }

    public static SocietyModel getInstance(){
        return getInstance(SocietyModel.class);
    }

    public void onEvent(AccountData account){
        login(ActivityManager.getInstance().currentActivity());
//        if (loginListener == null)return;
//        CommUser user = new CommUser(account.getId()+"");
//        user.source = Source.SELF_ACCOUNT;
//        String base64Name = Utils.string2Base64(account.getId()+"_"+account.getName());
//        user.name = base64Name;
//        user.iconUrl = account.getFace();
//        user.gender = CommUser.Gender.FEMALE;
//        loginListener.onComplete(200, user);
    }

    @Override
    public void login(Context context, LoginListener loginListener) {
        Utils.Log("userLogin");
        AccountData account = AccountModel.getInstance().getAccount();
//        if (account == null&&forceLogin){
//            this.loginListener = loginListener;
//            Activity act = ActivityManager.getInstance().currentActivity();
//            act.startActivity(new Intent(act, UserLoginActivity.class));
//            return;
//        }
        CommUser user = new CommUser(account.getId()+"");
        Utils.Log("realName:"+account.getId()+"_"+account.getName());
        String base64Name=Utils.string2Base64(account.getId()+"_"+account.getName());
        Utils.Log("base64Name:"+base64Name);
        user.name = base64Name;
        user.source = Source.SELF_ACCOUNT;
        user.iconUrl = account.getFace();
        user.gender = CommUser.Gender.FEMALE;
        loginListener.onComplete(200, user);
        Utils.Log("userloginname:" + user.name);
    }

    @Override
    public void logout(Context context, LoginListener loginListener) {
        AccountModel.getInstance().LoginOut();
    }

    @Override
    public boolean isLogined(Context context) {
        return commUser ==null;
    }

    public void login(Context ctx){
        communitySDK.login(ctx, new LoginListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(int i, CommUser commUser) {
                Utils.Log("Logined");
            }
        });
    }

    /**
     * 更新个人信息，是在友盟社区的信息，传入commuser对象
     * @param commUser
     * @param listener
     */
    public void updateBBSInfo(CommUser commUser, Listeners.CommListener listener){
        communitySDK.updateUserProfile(commUser,listener);
        this.commUser=commUser;
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
     * 获取feed的评论列表
     * @param feedId
     * @param listener
     */
    public void getComments(String feedId,Listeners.SimpleFetchListener<CommentResponse> listener){
        communitySDK.fetchFeedComments(feedId, listener);
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
     * 点赞
     * @param id
     */
    public void like(String id,Listeners.SimpleFetchListener<SimpleResponse> listener){
        communitySDK.postLike(id, listener);
    }

    /**
     * 取消赞
     * @param id
     */
    public void unLike(String id,Listeners.SimpleFetchListener<SimpleResponse> listener){
        communitySDK.postUnLike(id, listener);
    }

    /**
     * 发布评论
     * @param var1
     */
    public void comment(com.umeng.comm.core.beans.Comment var1,Listeners.FetchListener<SimpleResponse> listener){
        communitySDK.postComment(var1, listener);
    }

    /**
     * 更新社区的头像
     * @param bitmap
     * @param listener
     */
    public void updateBBSFace(Bitmap bitmap, Listeners.SimpleFetchListener<PortraitUploadResponse> listener){
        communitySDK.updateUserProtrait(bitmap, listener);
    }

    /**
     * 获取地理位置信息
     * @param var1
     * @param var2
     */
    public void getLocation(Location var1, Listeners.FetchListener<LocationResponse> var2){
        communitySDK.getLocationAddr(var1,var2);
    }

}
