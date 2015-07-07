package com.ant.jobgod.jobgod.module.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Banner;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.ant.jobgod.jobgod.model.CommonModel;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UpdateStatus;

/**
 * Created by Mr.Jude on 2015/7/6.
 */
public class SettingPresenter extends BasePresenter<SettingActivity> {
    private SettingItem[] items;

    private void initItems(){
        items = new SettingItem[]{
                new SettingItem("账号设置"),
                new SettingItem("重新登录","",(View v)->getView().startActivity(new Intent(getView(), UserLoginActivity.class))),
                new SettingItem("修改密码","通过手机号验证",null),
                new SettingItem("应用设置"),
                new SettingItem("意见反馈","",null),


                new SettingItem("检查更新","",(View v)->{
                    UmengUpdateAgent.setUpdateAutoPopup(false);
                    UmengUpdateAgent.setUpdateListener((updateStatus, updateInfo) -> {
                        switch (updateStatus) {
                            case UpdateStatus.Yes: // has update
                                break;
                            case UpdateStatus.No: // has no update
                                Utils.Toast("没有更新");
                                break;
                            case UpdateStatus.NoneWifi: // none wifi
                                Utils.Toast("没有wifi连接，只在wifi下更新");
                                break;
                            case UpdateStatus.Timeout: // time out
                                Utils.Toast("超时");
                                break;
                        }
                    });
                    UmengUpdateAgent.forceUpdate(getView());
                }),


                new SettingItem("关于","",null),
                new SettingItem("调试设置"),


                new SettingItem("调试按钮","",(View v)->{
                    Utils.Log("Trades:" + JobModel.getInstance().getTrade().length);
                    for (Trade t:JobModel.getInstance().getTrade()){
                        Utils.Log("Trade"+t.getName());
                    }
                    CommonModel.getInstance().getBanner(new DataCallback<Banner[]>() {
                        @Override
                        public void success(String info, Banner[] data) {
                            Utils.Log("getBanner:" + data.length);
                        }
                    });
                    JobModel.getInstance().getHotJobList(new DataCallback<JobBrief[]>() {
                        @Override
                        public void result(int status, String info) {
                            Utils.Log(status + info);
                        }

                        @Override
                        public void success(String info, JobBrief[] data) {
                            Utils.Log("getHotJobList:" + data.length);
                        }
                    });
                    JobModel.getInstance().getTopicList(new DataCallback<Topic[]>() {
                        @Override
                        public void result(int status, String info) {
                            Utils.Log(status+info);
                        }

                        @Override
                        public void success(String info, Topic[] data) {
                            Utils.Log("getTopicList:"+data.length);
                        }
                    });
                }),


                new SettingItem("打开调试界面","",null),
        };
    }

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        initItems();
        Utils.Log("onCreate");
    }

    @Override
    protected void onSave(Bundle state) {
        super.onSave(state);
        Utils.Log("onSave");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.Log("onDestroy");
    }

    @Override
    protected void onCreateView(SettingActivity view) {
        getView().addData(items);
        Utils.Log("onCreateView");
    }

    @Override
    protected void onTakeView(SettingActivity view) {
        super.onTakeView(view);
        Utils.Log("onTakeView");
    }

    @Override
    protected void onDropView() {
        super.onDropView();
        Utils.Log("onDropView");
    }
}
