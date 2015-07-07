package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobPage;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/6/6.
 * 工作的数据管理。
 */
public class JobModel extends AbsModel{
    public static JobModel getInstance(){
        return getInstance(JobModel.class);
    }
    private static final String TRADEFILE = "trade";

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        updateTrades();
    }

    public Trade[] getTrade(){
        Trade[] trades = (Trade[]) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object,TRADEFILE));
        return trades;
    }
    private void updateTrades(){
        RequestManager.getInstance().post(API.URL.GetHotJobList, null, new DataCallback<Trade[]>() {
            @Override
            public void success(String info, Trade[] data) {
                Utils.writeObjectToFile(data,FileManager.getInstance().getChild(FileManager.Dir.Object,TRADEFILE));
            }
        });
    }

    public void getHotJobList(DataCallback<JobBrief[]> callback){
        RequestManager.getInstance().post(API.URL.GetHotJobList,null,callback);
    }

    public void getTopicList(DataCallback<Topic[]> callback){
        RequestManager.getInstance().post(API.URL.GetTopicList, null, callback);
    }

    public void getTopicJobList(String topicId,DataCallback<JobBrief[]> callback){
        RequestManager.getInstance().post(API.URL.GetTopicJobList, new RequestMap("topicId",topicId), callback);
    }

    public void getJobList(int page,int count,String citycode,String type,int sort,String key,DataCallback<JobPage[]> callback){
        RequestMap params = new RequestMap();
        params.put("page",page+"");
        params.put("count",count+"");
        params.put("citycode",citycode+"");
        params.put("type",type+"");
        params.put("sort",sort+"");
        params.put("key",key);
        RequestManager.getInstance().post(API.URL.GetTopicJobList, params, callback);
    }
}
