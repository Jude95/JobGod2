package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.RemoteFileModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.bijection.Presenter;
import com.jude.library.imageprovider.ImageProvider;
import com.jude.library.imageprovider.OnImageSelectListener;

import java.io.File;

/**
 * Created by alien on 2015/7/11.
 */
public class AuthenticationPresenter extends Presenter<AuthenticationActivity> {

    private ImageProvider mProvider;
    private Uri mImage;
    @Override
    protected void onCreate(AuthenticationActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        mProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(AuthenticationActivity view) {
        super.onCreateView(view);
        if (mImage!=null)
        getView().setImg(mProvider.readImageWithSize(mImage, 500, 500));
    }

    /**
     * 网络请求，响应getview的点击事件
     */
    public void upload(String name,String number){
        getView().getExpansion().showProgressDialog("上传中");
        if (mImage==null){
            Utils.Toast("请先拍照");
            return;
        }
        Utils.Log(mImage.getPath());
        RemoteFileModel.getInstance().putImage(new File(mImage.getPath()), new RemoteFileModel.UploadImageListener() {
            @Override
            public void onComplete(RemoteFileModel.SizeImage path) {
                UserModel.getInstance().authentication(name, number, path.getOriginalImage(), new StatusCallback() {
                    @Override
                    public void success(String info) {
                        getView().getExpansion().dismissProgressDialog();
                        AccountModel.getInstance().updateAccountData();
                        Utils.Toast("上传成功,我们会尽快审核。");
                        getView().finish();
                    }
                });
            }

            @Override
            public void onError() {
                Utils.Toast("上传失败");
                getView().getExpansion().dismissProgressDialog();

            }
        });
    }

    public void getImageFromCamera(){
        mProvider.getImageFromCamera(new OnImageSelectListener() {
            @Override
            public void onImageSelect() {
                getView().getExpansion().showProgressDialog("图片加载中");
            }

            @Override
            public void onImageLoaded(Uri uri) {
                getView().getExpansion().dismissProgressDialog();
                mImage = uri;
                getView().setImg(mProvider.readImageWithSize(uri,500,500));
            }

            @Override
            public void onError() {
                getView().getExpansion().dismissProgressDialog();
                Utils.Log("图片加载错误");
            }
        });
    }


    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mProvider.onActivityResult(requestCode, resultCode, data);
    }

}
