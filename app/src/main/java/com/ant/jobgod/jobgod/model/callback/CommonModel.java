package com.ant.jobgod.jobgod.model.callback;

import com.ant.jobgod.jobgod.model.AbsModel;
import com.ant.jobgod.jobgod.model.bean.Banner;

/**
 * Created by Mr.Jude on 2015/7/7.
 */
public class CommonModel extends AbsModel {
    public CommonModel getInstance(){
        return getInstance(getClass());
    }

    public void getBanner(DataCallback<Banner> callback){
        callback.onError("");
    }
}
