package com.ant.jobgod.jobgod.module.user;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ant.jobgod.imagetool.corpimage.CropImageIntentBuilder;
import com.ant.jobgod.imagetool.imageprovider.ImageElement;
import com.ant.jobgod.imagetool.imageprovider.ImageProvider;
import com.ant.jobgod.imagetool.imageprovider.OnImageSelectListener;
import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/11.
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationActivity> {

    private static final int REQUEST_CROP_PICTURE = 1365;
    private static final String TEMP_IMG = "userFaceTempImage.jpg";
    private String mFinalImg;
    private ImageProvider mProvider;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        mProvider = new ImageProvider(getView());
    }

    @Override
    protected void onCreateView(AuthenticationActivity view) {
        super.onCreateView(view);
    }


    /**
     * 响应getview的点击事件
     */
    public void authentication(String idCard,String name,String img){
        UserModel.getInstance().certificate(idCard, name, img, new StatusCallback() {
            @Override
            public void success(String info) {
                Utils.Toast("提交成功，等待审核");
            }
        });
    }

    /**
     * 打开相机拍照
     */
    public void getImageFromCamera(){
        mProvider.getImageFromCamera(new OnImageSelectListener<ImageElement>() {
            @Override
            public void onImageSelect(ImageElement imageElement) {
                startCrop(imageElement.getLargeImage());
            }
        });
    }

    public void startCrop(Uri data){
        //删除上一次的图片
        FileManager.getInstance().deletChild(FileManager.Dir.Image,mFinalImg);
        //用时间来取名，临时措施
        mFinalImg = System.currentTimeMillis()+".jpg";
        CropImageIntentBuilder cropImage = new CropImageIntentBuilder(960, 1080,960, 1080, Uri.fromFile(FileManager.getInstance().getChild(FileManager.Dir.Image, mFinalImg)));
        cropImage.setSourceImage(data);
        getView().startActivityForResult(cropImage.getIntent(getView()), REQUEST_CROP_PICTURE);
    }

    @Override
    protected void onResult(int requestCode, int resultCode, Intent data) {
        super.onResult(requestCode, resultCode, data);
        mProvider.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CROP_PICTURE && resultCode == Activity.RESULT_OK){
            getView().setImg(Uri.fromFile(FileManager.getInstance().getChild(FileManager.Dir.Image, mFinalImg)));
            //裁剪成功，删除临时文件
            FileManager.getInstance().deletChild(FileManager.Dir.Image, TEMP_IMG);
        }
    }

}
