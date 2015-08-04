package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.imagetool.imageprovider.ImageElement;
import com.ant.jobgod.imagetool.imageprovider.ImageProvider;
import com.ant.jobgod.imagetool.imageprovider.OnImageSelectListener;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.RemoteFileModel;
import com.ant.jobgod.jobgod.model.SocietyModel;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;
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
        if (SocietyModel.getInstance().checkLogin(getView()))
            SocietyModel.getInstance().publishFeed(feedItem, listener);
        else
            getView().startActivity(new Intent(getView(), UserLoginActivity.class));
    }

    /**
     * 同步操作,从相册取照片
     *
     * @return
     */
    public void getImgFromAlbum() {
        provider.getImageFromAlbum(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                getView().showProgress("加载图片中");
                Uri uri=imageElement.getLargeImage();
                RemoteFileModel.getInstance().putImage(Utils.uri2File(uri), new RemoteFileModel.UploadImageListener() {
                    @Override
                    public void onComplete(RemoteFileModel.SizeImage path) {
                        ImageItem imageItem = new ImageItem();
                        imageItem.originImageUrl = path.getOriginalImage();
                        imageItem.thumbnail = path.getLargeImage();
                        imageItem.middleImageUrl = path.getSmallImage();
                        getView().setOneImg(imageItem);
                        getView().dismissProgress();
                    }

                    @Override
                    public void onError() {
                        Utils.Toast("添加失败");
                        getView().dismissProgress();
                    }
                });
            }
        });
    }


    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        provider.onActivityResult(requestCode,resultCode,data);

    }
}
