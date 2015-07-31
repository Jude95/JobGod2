package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.ant.jobgod.imagetool.imageprovider.ImageElement;
import com.ant.jobgod.imagetool.imageprovider.ImageProvider;
import com.ant.jobgod.imagetool.imageprovider.OnImageSelectListener;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.SocietyModel;
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
    private Uri uri;

    public final static int REQUEST_ALBUM=100;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        provider=new ImageProvider(getView());
    }

    /**
     * 发布一个feed
     * @param feedItem
     * @param listener
     */
    public void publishFeed(FeedItem feedItem,Listeners.SimpleFetchListener<FeedItemResponse> listener){
        SocietyModel.getInstance().publishFeed(feedItem, listener);
    }


    public void getImgFromAlbum(){
        provider.getImageFromAlbum(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                uri = imageElement.getLargeImage();
                ImageItem img=new ImageItem();
                img.originImageUrl= String.valueOf(uri);
                img.middleImageUrl=String.valueOf(uri);
                img.thumbnail=String.valueOf(uri);
                img.format="jpg";
                Utils.Log("uri:" + uri);

                getView().setImg(img);
            }
        });
    }

    public Uri getImgFromCamera(){
        provider.getImageFromCamera(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                uri = imageElement.getLargeImage();
            }
        });
        return uri;
    }

    public void getImgs(){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        getView().startActivityForResult(intent, REQUEST_ALBUM);
    }


    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_ALBUM){
            uri = data.getData();
            ImageItem img=new ImageItem();
            img.originImageUrl= String.valueOf(uri);
            img.middleImageUrl=String.valueOf(uri);
            img.thumbnail=String.valueOf(uri);
            img.format="jpg";

            Utils.Log("uri:" + uri);

            getView().setImg(img);
        }
    }
}
