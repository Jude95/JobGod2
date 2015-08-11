package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.RongYunModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by alien on 2015/7/13.
 */
public class UserDetailPresenter extends BasePresenter<UserDetailActivity> {
    private int id;
    private UserDetail userDetail;
    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        id = getView().getIntent().getIntExtra("id",0);
        setData();

    }

    /**
     * 设置用户个人信息数据
     */
    private void setData(){
        UserModel.getInstance().getUserDetail(id+"", new DataCallback<UserDetail>() {
            @Override
            public void success(String info, UserDetail data) {
                getView().setUserDetail(userDetail=data);
            }
        });
    }

    @Override
    protected void onCreateView(UserDetailActivity view) {
        super.onCreateView(view);
        if (userDetail!=null)
        getView().setUserDetail(userDetail);
    }

    /**
     * 关注按钮的点击事件
     */
    public void attention(){
        if (userDetail.isFocus())
            UserModel.getInstance().unAttention(id, new StatusCallback() {
                @Override
                public void success(String info) {
                    Utils.Toast("已取消关注");
                    userDetail.setFocus(false);
                    getView().setIsAttention(userDetail.isFocus());
                }
            });
        else
            UserModel.getInstance().attention(id, new StatusCallback() {
                @Override
                public void success(String info) {
                    Utils.Toast("关注成功");
                    userDetail.setFocus(true);
                    getView().setIsAttention(userDetail.isFocus());
               }
            });

    }

    public void chat(){
        RongYunModel.getInstance().chatPerson(getView(),userDetail.getId()+"",userDetail.getName());
    }
}
