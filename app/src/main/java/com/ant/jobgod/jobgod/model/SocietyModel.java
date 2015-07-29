package com.ant.jobgod.jobgod.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.ant.jobgod.jobgod.model.bean.AccountData;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.ActivityManager;
import com.umeng.comm.core.beans.CommUser;
import com.umeng.comm.core.beans.Source;
import com.umeng.comm.core.impl.CommunityFactory;
import com.umeng.comm.core.login.LoginListener;
import com.umeng.comm.core.login.Loginable;
import com.umeng.comm.core.sdkmanager.LoginSDKManager;

/**
 * Created by Mr.Jude on 2015/7/29.
 */
public class SocietyModel extends AbsModel  implements Loginable {
    private LoginListener loginListener;

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        CommunityFactory.getCommSDK(ctx);
        LoginSDKManager.getInstance().addAndUse(this);
        AccountModel.getInstance().registerEvent(this);
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
}
