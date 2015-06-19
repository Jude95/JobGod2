package com.ant.jobgod.jobgod.module.launch;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.util.Utils;

import java.util.HashMap;
import java.util.Map;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by Mr.Jude on 2015/6/6.
 */
public class LoginPresenter extends BasePresenter<LoginActivity> {

    public void login(String tel,String pass){
        Platform qq= ShareSDK.getPlatform(getView(), QQ.NAME);
        qq.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Utils.Log("onComplete");
                for (Map.Entry<String, Object> entry:hashMap.entrySet()){
                    Utils.Log(entry.getKey()+":"+entry.getValue());
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Utils.Log("onError"+throwable.getMessage());
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Utils.Log("onCancel");
            }
        });
        qq.authorize();
//        UserModel.getInstance().login(tel, pass, new StatusCallback() {
//            @Override
//            public void success(int status, String info) {
//
//            }
//
//            @Override
//            public void error(String errorInfo) {
//
//            }
//        });
    }
}
