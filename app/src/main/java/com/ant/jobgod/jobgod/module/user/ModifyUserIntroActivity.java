package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;

import com.android.http.RequestMap;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

@RequiresPresenter(ModifyUserIntroPresenter.class)
public class ModifyUserIntroActivity extends BaseActivity<ModifyUserIntroPresenter> {

    @InjectView(R.id.name)
    TextInputLayout name;
    @InjectView(R.id.signature)
    TextInputLayout signature;
    @InjectView(R.id.submit)
    Button submit;
    @InjectView(R.id.imgFace)
    SimpleDraweeView imgFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_modifyintro);
        ButterKnife.inject(this);

        submit.setOnClickListener(v -> {
            if(name.getEditText().getText().toString().isEmpty()||signature.getEditText().getText().toString().isEmpty()){
                Utils.Toast("不能为空");
                return;
            }
            getPresenter().postNet(new DataCallback() {
                @Override
                public void success(String info, Object data) {
                    Utils.Toast("修改成功");
                }
            });
        });
        /**
         * 修改头像
         */
        imgFace.setImageURI(Uri.parse("http://img4.imgtn.bdimg.com/it/u=2205791892,1328528914&fm=23&gp=0.jpg"));
        imgFace.setOnClickListener(v -> startActivity(new Intent(ModifyUserIntroActivity.this,ModifyFaceActivity.class)));

    }

    public RequestMap setParam() {
        RequestMap param = new RequestMap();
        param.put("name", name.getEditText().getText().toString());
        param.put("sign", signature.getEditText().getText().toString());
        return param;
    }

}
