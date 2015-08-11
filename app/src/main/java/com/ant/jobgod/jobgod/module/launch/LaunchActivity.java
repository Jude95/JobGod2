package com.ant.jobgod.jobgod.module.launch;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BaseActivity;

import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/1.
 */
@RequiresPresenter(LaunchPresenter.class)
public class LaunchActivity extends BaseActivity<LaunchPresenter> {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //SocietyModel.getInstance().checkLogin(this,false);
//        SpotManager.getInstance(this).loadSplashSpotAds();
//        SplashView splashView = new SplashView(this,UserMainActivity.class);
//        setContentView(splashView.getSplashView());
//        SpotManager.getInstance(this).showSplashSpotAds(this, splashView,
//                new SpotDialogListener() {
//
//                    @Override
//                    public void onShowSuccess() {
//                        Utils.Log("开屏展示成功");
//                    }
//
//                    @Override
//                    public void onShowFailed() {
//                        Utils.Log("开屏展示失败");
//                    }
//
//                    @Override
//                    public void onSpotClosed() {
//                        Utils.Log("开屏展示关闭");
//                    }
//                });
    }

    @Override
    public void onBackPressed() {
        // 取消后退键
    }

}
