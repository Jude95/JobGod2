package com.ant.jobgod.jobgod.module.user;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/11.
 */
@RequiresPresenter(AuthenticationPresenter.class)
public class AuthenticationActivity extends BaseActivity<AuthenticationPresenter> {


    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;
    @InjectView(R.id.ImgID)
    ImageView ImgID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_authentication);
        ButterKnife.inject(this);

        imgFace.setImageURI(Uri.parse("http://img1.imgtn.bdimg.com/it/u=1571729784,3682383797&fm=11&gp=0.jpg"));
    }


    public RequestMap setParam() {
        RequestMap param = new RequestMap();
//        param.put("name", name.getEditText().getText().toString());
        return param;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_authentication, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
