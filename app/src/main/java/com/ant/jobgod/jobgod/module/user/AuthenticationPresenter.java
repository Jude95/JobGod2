package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.imagetool.imageprovider.ImageElement;
import com.ant.jobgod.imagetool.imageprovider.ImageProvider;
import com.ant.jobgod.imagetool.imageprovider.OnImageSelectListener;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.RemoteFileModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

import java.io.File;

/**
 * Created by alien on 2015/7/11.
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationActivity> {

    private static final int REQUEST_CROP_PICTURE = 1365;
    private Uri mFinalImg = null;
    private ImageProvider mProvider;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        mProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(AuthenticationActivity view) {
        super.onCreateView(view);
        if (mFinalImg!=null)getView().setImg(mFinalImg);
    }

    /**
     * 网络请求，响应getview的点击事件
     */
    public void upload(String name,String number){
        getView().showProgress("上传中");
        if (mFinalImg==null){
            Utils.Toast("请先拍照");
            return;
        }
        Utils.Log(mFinalImg.toString());
        RemoteFileModel.getInstance().putImage(new File(mFinalImg.toString()), new RemoteFileModel.UploadImageListener() {
            @Override
            public void onComplete(RemoteFileModel.SizeImage path) {
                UserModel.getInstance().authentication(name, number, path.getOriginalImage(), new StatusCallback() {
                    @Override
                    public void success(String info) {
                        getView().dismissProgress();
                        Utils.Toast("上传成功,我们会尽快审核。");
                        getView().finish();
                    }
                });
            }

            @Override
            public void onError() {
                Utils.Toast("上传失败");
                getView().dismissProgress();
            }
        });
    }

    public void getImageFromCamera(){
        mProvider.getImageFromCamera(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                getView().setImg(mFinalImg = imageElement.getLargeImage());
            }
        });
    }


    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mProvider.onActivityResult(requestCode, resultCode, data);
    }

}
