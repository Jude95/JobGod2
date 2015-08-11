package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;
import com.umeng.comm.core.beans.FeedItem;
import com.umeng.comm.core.beans.ImageItem;
import com.umeng.comm.core.listeners.Listeners;
import com.umeng.comm.core.nets.responses.FeedItemResponse;

/**
 * Created by alien on 2015/7/30.
 */
public class PublishFeedPresenter extends BasePresenter<PublishFeedActivity> {


    private ImageProvider provider;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        provider = new ImageProvider(getView());
    }

    /**
     * 发布一个feed
     *
     * @param feedItem
     * @param listener
     */
    public void publishFeed(FeedItem feedItem, Listeners.SimpleFetchListener<FeedItemResponse> listener) {
        SocietyModel.getInstance().publishFeed(feedItem, listener);
    }

    /**
     * 同步操作,从相册取照片
     *
     * @return
     */
    public void getImgFromAlbum() {
        provider.getImageFromAlbum(new OnImageSelectListener() {
            @Override
            public void onImageSelect() {

            }

            @Override
            public void onImageLoaded(Uri uri) {
                ImageItem imageItem = new ImageItem();
                imageItem.originImageUrl = uri.toString();
                getView().setOneImg(imageItem);
            }

            @Override
            public void onError() {

            }
        });
    }


    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        provider.onActivityResult(requestCode,resultCode,data);

    }
}
