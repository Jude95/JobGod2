package com.ant.jobgod.jobgod.model;

import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.Manager;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.jude.http.RequestManager;
import com.jude.http.RequestMap;

/**
 * Created by alien on 2015/7/22.
 */
public class ManagerModel extends AbsModel{

    /**
     * 重载getinstance这个方法,单例模式
     * @return
     */
    public static ManagerModel getInstance(){
        return getInstance(ManagerModel.class);
    }
    /**
     * 获取管理后台信息
     * @param jobId
     * @param callback
     */
    public void getMangerData(int jobId,DataCallback<Manager> callback){
        RequestMap param=new RequestMap();
        param.put("jobId",jobId+"");
        RequestManager.getInstance().post(API.URL.GetContract,param,callback);
    }

    /**
     * 取消兼职报名
     * @param id  合同的id
     * @param callback
     */
    public void cancelApply(int id,StatusCallback callback){
        RequestMap param=new RequestMap("id",id+"");
        RequestManager.getInstance().post(API.URL.CancelApply,param,callback);
    }

    /**
     * 提交对商家的评价
     * @param id 合同的id
     * @param feel
     * @param content
     * @param callback
     */
    public void jodgeBiz(int id,int feel,String content,StatusCallback callback){
        RequestMap param=new RequestMap();
        param.put("id",id+"");
        param.put("feek",feel+"");
        param.put("content",content);
        RequestManager.getInstance().post(API.URL.JodgeBiz,param,callback);
    }

}
