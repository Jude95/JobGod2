package com.ant.jobgod.jobgod.module.setting;

import android.os.Bundle;
import android.view.View;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.JobDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.expansion.list.BeamListActivityPresenter;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UpdateStatus;

/**
 * Created by Mr.Jude on 2015/7/6.
 */
public class SettingPresenter extends BeamListActivityPresenter<SettingActivity,SettingItem> {

    private SettingItem[] initItems(){
        SettingItem[] items = new SettingItem[]{
                new SettingItem("应用设置"),
                new SettingItem("意见反馈","",null),


                new SettingItem("检查更新","",(View v)->{
                    UmengUpdateAgent.setUpdateAutoPopup(false);
                    UmengUpdateAgent.setUpdateListener((updateStatus, updateInfo) -> {
                        switch (updateStatus) {
                            case UpdateStatus.Yes:
                                MobclickAgent.updateOnlineConfig(getView());
                                break;
                            case UpdateStatus.No:
                                Utils.Toast("没有更新");
                                break;
                            case UpdateStatus.NoneWifi:
                                Utils.Toast("没有wifi连接，只在wifi下更新");
                                break;
                            case UpdateStatus.Timeout:
                                Utils.Toast("连接超时");
                                break;
                        }
                    });
                    UmengUpdateAgent.forceUpdate(getView());
                }),


                new SettingItem("关于","",null),
                new SettingItem("调试设置"),


                new SettingItem("调试按钮","",(View v)->{
                    JobModel.getInstance().getJobDetail(1, new DataCallback<JobDetail>() {
                        @Override
                        public void success(String info, JobDetail data) {
                            Utils.Log(data.getTitle());
                        }
                    });
                }),


                new SettingItem("打开调试界面","",null),
        };
        return items;
    }

    @Override
    protected void onCreate(SettingActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        getAdapter().addAll(initItems());
    }
}
