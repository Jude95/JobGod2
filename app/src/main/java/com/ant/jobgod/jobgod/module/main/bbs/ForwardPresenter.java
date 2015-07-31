package com.ant.jobgod.jobgod.module.main.bbs;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.util.Utils;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.FeedItemResponse;

/**
 * Created by alien on 2015/7/30.
 */
public class ForwardPresenter extends BasePresenter<ForwardActivity> {
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(ForwardActivity view) {
        super.onCreateView(view);
    }

    //转发
    public void forward(String content){
        Utils.Log("feeditem:"+getView().getIntent().getParcelableExtra("feed"));

        FeedItem item=getView().getIntent().getParcelableExtra("feed");

        SocietyModel.getInstance().forward(item, new Listeners.SimpleFetchListener<FeedItemResponse>() {
            @Override
            public void onComplete(FeedItemResponse feedItemResponse) {
                Utils.Toast("转发成功");
            }
        });
    }
}
