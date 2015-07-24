package com.ant.jobgod.jobgod.model;

import android.content.Context;

import com.android.http.RequestManager;
import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.config.API;
import com.ant.jobgod.jobgod.model.bean.CommentPage;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.ant.jobgod.jobgod.model.bean.JobBriefPage;
import com.ant.jobgod.jobgod.model.bean.JobDetail;
import com.ant.jobgod.jobgod.model.bean.Region;
import com.ant.jobgod.jobgod.model.bean.Topic;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/6/6.
 * 工作的数据管理。
 */
public class JobModel extends AbsModel {
    public static JobModel getInstance() {
        return getInstance(JobModel.class);
    }

    private static final String TRADE_FILE = "trade";
    private static final String FILTRATE_TRADE_FILE = "filtrateTrade";
    private static final String FILTRATE_REGION_FILE = "filtrateRegion";
    private static final String FILTRATE_SORT_SP = "filtrateSort";

    @Override
    protected void onAppCreate(Context ctx) {
        super.onAppCreate(ctx);
        updateTrades();
    }

    //从网络获取行业分类，为了速度用文件转同步，第一次启动时可能有短暂时间为空
    public Trade[] getTrade() {
        Trade[] trades = (Trade[]) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object, TRADE_FILE));
        return trades == null ? new Trade[0] : trades;
    }

    //取行业分类的偏好项
    public Trade[] getFiltrateTrade() {
        Trade[] trades = (Trade[]) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object, FILTRATE_TRADE_FILE));
        return trades == null ? getTrade() : trades;
    }

    //取地区偏好项
    public Region[] getFiltrateRegion() {
        Region[] trades = (Region[]) Utils.readObjectFromFile(FileManager.getInstance().getChild(FileManager.Dir.Object, FILTRATE_REGION_FILE));
        return trades == null ? new Region[]{RegionModel.getInstance().findCity(LocationModel.getInstance().getCurLocation().getRegionCode())} : trades;
    }

    public int getFiltrateSort() {
        return Utils.getPreference().getInt(FILTRATE_SORT_SP, 0);
    }

    public void saveFiltrateSort(int sort) {
        Utils.getPreference().edit().putInt(FILTRATE_SORT_SP, sort).commit();
    }

    //保存行业分类的偏好项
    public void saveFiltrateTrade(Trade[] trades) {
        Utils.writeObjectToFile(trades, FileManager.getInstance().getChild(FileManager.Dir.Object, FILTRATE_TRADE_FILE));
    }

    //保存行业分类的偏好项
    public void saveFiltrateRegion(Region[] regions) {
        Utils.writeObjectToFile(regions, FileManager.getInstance().getChild(FileManager.Dir.Object, FILTRATE_REGION_FILE));
    }

    private void updateTrades() {
        RequestManager.getInstance().post(API.URL.GetTrades, null, new DataCallback<Trade[]>() {
            @Override
            public void success(String info, Trade[] data) {
                Utils.writeObjectToFile(data, FileManager.getInstance().getChild(FileManager.Dir.Object, TRADE_FILE));
            }
        });
    }

    public void getRecommendList(DataCallback<JobBrief[]> callback) {
        RequestManager.getInstance().post(API.URL.GetRecommendList, null, callback);
    }

    public void getTopicList(DataCallback<Topic[]> callback) {
        RequestManager.getInstance().post(API.URL.GetTopicList, null, callback);
    }

    public void getTopicJobList(String topicId, DataCallback<JobBrief[]> callback) {
        RequestManager.getInstance().post(API.URL.GetTopicJobList, new RequestMap("id", topicId), callback);
    }

    public void getJobDetail(int jobId, DataCallback<JobDetail> callback) {
        RequestManager.getInstance().post(API.URL.GetJobDetail, new RequestMap("id", jobId + ""), callback);
    }

    public void getJobList(int page, int count, DataCallback<JobBriefPage> callback) {
        RequestMap params = new RequestMap();
        params.put("page", page + "");
        params.put("count", count + "");
        for (Region r : getFiltrateRegion()) {
            params.put("cityCode[]", r.getCid() + "");
        }
        for (Trade t : getFiltrateTrade()) {
            params.put("trade[]", t.getId() + "");
        }
        params.put("sort", getFiltrateSort() + "");
        RequestManager.getInstance().post(API.URL.GetJobList, params, callback);
    }

    public void collect(int id, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.Collect, new RequestMap("id", id + ""), callback);
    }

    public void unCollect(int id, StatusCallback callback) {
        RequestManager.getInstance().post(API.URL.UnCollect, new RequestMap("id", id + ""), callback);
    }

    public void getCommentList(int id, int page, int count, DataCallback<CommentPage> callback) {
        RequestMap params = new RequestMap("id", id + "");
        params.put("page", page + "");
        params.put("count", count + "");
        RequestManager.getInstance().post(API.URL.GetComment, params, callback);
    }

    public void comment(int id, String content,StatusCallback callback) {
        RequestMap param=new RequestMap();
        param.put("jobId",id+"");
        param.put("content",content);
        RequestManager.getInstance().post(API.URL.Comment, param, callback);
    }

    public void applyJob(int jobId, StatusCallback callback) {
        RequestMap param = new RequestMap("id", jobId + "");
        RequestManager.getInstance().post(API.URL.Apply, param, callback);
    }
}
