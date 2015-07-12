package com.ant.jobgod.jobgod.module.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.util.FileManager;
import com.ant.jobgod.jobgod.util.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyFacePresenter extends BasePresenter<ModifyFaceActivity> {

    private final String IMAGE_TYPE = "image/*";
    private final int ACMERA_REQUEST_CODE=1;
    private final int ALBUM_REQUEST_CODE=2;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(ModifyFaceActivity view) {
        super.onCreateView(view);
    }



    public Uri openCamera(){
        Intent iCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File img= FileManager.getInstance().getChild(FileManager.Dir.Image,getPhotoFileName());
        // 指定调用相机拍照后照片的储存路径，这里是为了剪切时找到它
        iCamera.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(img));
        getView().startActivityForResult(iCamera, ACMERA_REQUEST_CODE);
        return Uri.fromFile(img);
    }

    public void openAlbum(){
        Intent iAlbum=new Intent(Intent.ACTION_GET_CONTENT);
        iAlbum.setType(IMAGE_TYPE);
        getView().startActivityForResult(iAlbum, ALBUM_REQUEST_CODE);
    }


    /**
     * 调用系统剪切功能剪切照片
     * @param context 对应的Acitivity
     * @param uri 对应的图片的uri
     * @param  requestCode 请求码
     */
    public File startPhotoZoom(Context context,Uri uri,int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        File cutImgAdress= FileManager.getInstance().getChild(FileManager.Dir.Image,getPhotoFileName());

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cutImgAdress));
        intent.putExtra("crop", "true");/*调用系统剪切图片*/
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        intent.putExtra("noFaceDetection", true);
        ((Activity)context).startActivityForResult(intent, requestCode);
        return cutImgAdress;
    }


    /**
     * 使用系统当前日期md5加密后作为照片的名称
     */
    public String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        String fileName= dateFormat.format(date);
        fileName=Utils.MD5(fileName.getBytes());
        return  fileName+ ".jpg";
    }


}
