package com.ant.jobgod.jobgod.module.setting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;

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
                new SettingItem("检查更新","",null),
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
