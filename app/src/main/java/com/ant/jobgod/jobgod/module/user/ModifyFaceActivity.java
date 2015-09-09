package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.BeamBaseActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

@RequiresPresenter(ModifyFacePresenter.class)
public class ModifyFaceActivity extends BeamBaseActivity<ModifyFacePresenter> {


    @InjectView(R.id.tv_camera)
    TextView tvCamera;
    @InjectView(R.id.tv_album)
    TextView tvAlbum;
    @InjectView(R.id.tv_net)
    TextView tvNet;
    @InjectView(R.id.img_face)
    SimpleDraweeView imgFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyface);
        ButterKnife.inject(this);
        tvCamera.setOnClickListener(v -> getPresenter().getImageFromCamera());
        tvAlbum.setOnClickListener(v -> getPresenter().getImageFromAlbum());
        tvNet.setOnClickListener(v -> getPresenter().getImageFromNet());
    }

    public void setImgFace(Uri uri) {
        imgFace.setImageURI(uri);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.submit) {
            getPresenter().upload();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_modifyface, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
