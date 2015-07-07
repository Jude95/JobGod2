package com.ant.jobgod.jobgod.module.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
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
                    UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
                        @Override
                        public void onUpdateReturned(int updateStatus,UpdateResponse updateInfo) {
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
                        }
                    });
                    UmengUpdateAgent.update(getView());
                }),
                new SettingItem("关于","",null),
                new SettingItem("调试设置"),
                new SettingItem("调试按钮","",null),
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
