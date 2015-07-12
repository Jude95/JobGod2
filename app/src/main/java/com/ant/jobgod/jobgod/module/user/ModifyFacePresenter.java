package com.ant.jobgod.jobgod.module.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.util.Utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alien on 2015/7/11.
 */
public class ModifyFacePresenter extends BasePresenter<ModifyFaceActivity> {

    private final String IMAGE_TYPE = "image/*";
    private final int ACMERA_REQUEST_CODE=1,ACMERA_RESULT_CODE=1;
    private final int ALBUM_REQUEST_CODE=1,ALBUM_RESULT_CODE=2;


    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
    }

    @Override
    protected void onCreateView(ModifyFaceActivity view) {
        super.onCreateView(view);
    }


    public void openCamera(){
        Intent iCamera=new Intent(Intent.ACTION_GET_CONTENT);
        iCamera.setType(IMAGE_TYPE);
        getView().startActivityForResult(iCamera, ACMERA_REQUEST_CODE);
    }



    /**
     * 调用系统剪切功能剪切照片
     * @param context 对应的Acitivity
     * @param uri 对应的图片的uri
     * @param  requestCode 请求码
     */
    public static File startPhotoZoom(Context context,Uri uri,int requestCode) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");

        File cutImgAdress= createFile();

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
     * 创建一个文件，当前时间作为文件名
     * @return
     */
    public static File createFile(){
        /*判断sd卡是否存在*/
        boolean sdCardExist=Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        Utils.Log("getDataDirctory:"+Environment.getDataDirectory());
        if(sdCardExist){
            return new File(Environment.getDataDirectory(),getPhotoFileName());
        }
        return null;
    }

    /**
     * 使用系统当前日期md5加密后作为照片的名称
     */
    public static String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyyMMdd_HHmmss");
        String fileName= dateFormat.format(date);
        fileName=Utils.MD5(fileName.getBytes());
        return  fileName+ ".jpg";
    }


}
