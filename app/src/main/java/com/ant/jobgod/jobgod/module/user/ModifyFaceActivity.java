package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import java.io.File;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyFacePresenter.class)
public class ModifyFaceActivity extends BaseActivity<ModifyFacePresenter> {


    @InjectView(R.id.example)
    ImageView example;
    @InjectView(R.id.camera)
    TextView camera;
    @InjectView(R.id.album)
    TextView album;
    @InjectView(R.id.net)
    TextView net;

    private final int CUT_IMG_REQUEST_CODE =0;
    private final int ACMERA_REQUEST_CODE=1,ACMERA_RESULT_CODE=1;
    private final int ALBUM_REQUEST_CODE=2,ALBUM_RESULT_CODE=2;

    private Intent data;

    private File cutImgFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyface);
        ButterKnife.inject(this);

        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getPresenter().openCamera();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.data=data;
        if(resultCode!=RESULT_OK){  /*系统提供的常量*/
            return;
        }
        switch (requestCode){
            case ACMERA_REQUEST_CODE:
                if (data != null)
                    cutImgFile= getPresenter().startPhotoZoom(this,data.getData(), CUT_IMG_REQUEST_CODE);
                break;
            case ALBUM_REQUEST_CODE:

                break;
            /*剪切的请求编码*/
            case CUT_IMG_REQUEST_CODE:
                example.setImageURI(Uri.parse(cutImgFile.getAbsolutePath()));
                break;

        }
    }
}
