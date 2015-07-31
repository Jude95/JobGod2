package com.ant.jobgod.jobgod.module.main.bbs;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.FeedItemResponse;

/**
 * Created by alien on 2015/7/29.
 */
public class BBSDetailPresenter extends BasePresenter<BBSDetailActivity> {

    private FeedItem feedItem;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        setData();
    }

    @Override
    protected void onCreateView(BBSDetailActivity view) {
        super.onCreateView(view);
        if(feedItem!=null){
            getView().setData(feedItem);
        }
    }


    public void setData(){
        SocietyModel.getInstance().getFeedForId(getView().getIntent().getStringExtra("feedId"), new Listeners.FetchListener<FeedItemResponse>() {
            @Override
            public void onStart() {

            }

            @Override
            public void onComplete(FeedItemResponse feedItemResponse) {
                getView().setData(feedItem = feedItemResponse.result);
            }
        });
    }
}
