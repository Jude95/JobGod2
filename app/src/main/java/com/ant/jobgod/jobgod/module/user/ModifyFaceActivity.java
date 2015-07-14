package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyFacePresenter.class)
public class ModifyFaceActivity extends BaseActivity<ModifyFacePresenter> {

    @InjectView(R.id.img_face)
    ImageView imgFace;
    @InjectView(R.id.tv_camera)
    TextView tvCamera;
    @InjectView(R.id.tv_album)
    TextView tvAlbum;
    @InjectView(R.id.tv_net)
    TextView tvNet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyface);
        ButterKnife.inject(this);
        tvCamera.setOnClickListener(v -> getPresenter().getImageFromCamera());
        tvAlbum.setOnClickListener(v->getPresenter().getImageFromAlbum());
        tvNet.setOnClickListener(v->getPresenter().getImageFromNet());
    }

    public void setImgFace(Uri uri){
        imgFace.setImageURI(uri);
    }
}
