package com.ant.jobgod.jobgod.model.callback;

import com.android.http.RequestManager;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.AbsModel;
import com.ant.jobgod.jobgod.model.bean.Banner;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class CommonModel extends AbsModel {
    public static CommonModel getInstance(){
        return getInstance(CommonModel.class);
    }

    public void getBanner(DataCallback<Banner[]> callback){
        RequestManager.getInstance().post(API.URL.GetBanner, null, callback);
    }
}
