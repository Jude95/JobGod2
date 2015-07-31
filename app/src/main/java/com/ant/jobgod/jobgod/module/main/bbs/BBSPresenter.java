package com.ant.jobgod.jobgod.module.main.bbs;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.SocietyModel;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.FeedsResponse;

import java.util.List;

import nucleus.manager.Presenter;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
public class BBSPresenter extends Presenter<BBSFragment> {

    private List<FeedItem> list;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setData();
    }

    @Override
    protected void onCreateView(BBSFragment view) {
        super.onCreateView(view);
        if(list!=null){
            getView().setData(list);
        }
    }


    /**
     * 刷新列表
     */
    public void setData(){
        SocietyModel.getInstance().getFeeds(new Listeners.FetchListener<FeedsResponse>() {
            @Override
            public void onStart() {
            }

            @Override
            public void onComplete(FeedsResponse feedsResponse) {
                if (feedsResponse.result.size() < 20) {
                    getView().stopMore();
                }
                getView().setData(list = feedsResponse.result);
            }
        });
    }

    /**
     * 加载更多
     * @param url
     */
    public void loadMore(String url){
        SocietyModel.getInstance().getMoreFeeds(url, FeedsResponse.class, new Listeners.FetchListener<FeedsResponse>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(FeedsResponse feedsResponse) {
                if (feedsResponse.result.size() < 20) {
                    getView().stopMore();
                }
                getView().loadMore(feedsResponse.result);
            }
        });
    }
}
