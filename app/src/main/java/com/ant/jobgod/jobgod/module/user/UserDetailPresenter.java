package com.ant.jobgod.jobgod.module.user;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;

/**
 * Created by alien on 2015/7/13.
 */
public class UserDetailPresenter extends BasePresenter<UserDetailActivity> {
    private String id;
    private UserDetail userDetail;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        id = getView().getIntent().getStringExtra("id");
        UserModel.getInstance().getUserDetail(id, new DataCallback<UserDetail>() {
            @Override
            public void success(String info, UserDetail data) {
                getView().setUserDetail(userDetail = data);
            }
        });
    }

    @Override
    protected void onCreateView(UserDetailActivity view) {
        super.onCreateView(view);
        if (userDetail!=null)
        getView().setUserDetail(userDetail);
    }

    public void attention(){
        getView().setIsAttention(!userDetail.isFocus());
        if (userDetail.isFocus())
            UserModel.getInstance().unAttention(id, null);
        else
            UserModel.getInstance().attention(id, null);
        userDetail.setIsAttention(!userDetail.isFocus());
    }

    public void chat(){
        Intent i = new Intent(getView(),ChatActivity.class);
        i.putExtra("id",id);
        getView().startActivity(i);
    }
}
