package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;

import com.ant.jobgod.jobgod.model.RongYunModel;
import com.ant.jobgod.jobgod.model.UserModel;
import com.ant.jobgod.jobgod.model.bean.UserDetail;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.expansion.data.BeamDataActivityPresenter;

/**
 * Created by alien on 2015/7/13.
 */
public class UserDetailPresenter extends BeamDataActivityPresenter<UserDetailActivity,UserDetail> {
    private int id;
    private UserDetail userDetail;
    @Override
    protected void onCreate(UserDetailActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        id = view.getIntent().getIntExtra("id",0);
        getView().getExpansion().showProgressPage();
        UserModel.getInstance().getUserDetail(id + "", new DataCallback<UserDetail>() {
            @Override
            public void success(String info, UserDetail data) {
                getView().getExpansion().dismissProgressPage();
                publishObject(userDetail = data);
            }

            @Override
            public void error(String errorInfo) {
                getView().getExpansion().dismissProgressPage();
                getView().getExpansion().showErrorPage();
            }
        });
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
