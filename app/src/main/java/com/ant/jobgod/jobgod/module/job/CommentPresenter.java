package com.ant.jobgod.jobgod.module.job;

import android.content.Intent;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.AccountModel;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.ant.jobgod.jobgod.model.bean.CommentPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.module.launch.UserLoginActivity;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.expansion.list.BeamListActivityPresenter;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentPresenter extends BeamListActivityPresenter<CommentActivity,Comment> {
    private static final int PAGE_COUNT = 20;
    private int id;
    private static int page = 0;

    @Override
    protected void onCreate(CommentActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        id = getView().getIntent().getIntExtra("id",0);
        onRefresh();
    }

    @Override
    public void onRefresh() {
        JobModel.getInstance().getCommentList(id, 0, PAGE_COUNT, new DataCallback<CommentPage>() {
            @Override
            public void success(String info, CommentPage data) {
                getAdapter().clear();
                getAdapter().addAll(data.getComments());
                page = 1;
            }

            @Override
            public void error(String errorInfo) {
                getView().showError();
            }
        });
    }

    @Override
    public void onLoadMore() {
        JobModel.getInstance().getCommentList(id, page, PAGE_COUNT, new DataCallback<CommentPage>() {
            @Override
            public void success(String info, CommentPage data) {
                getAdapter().addAll(data.getComments());
                page++;
            }
        });
    }

    /**
     * 发表评论
     * @param content
     */
    public void submitComment(String content){
        if(AccountModel.getInstance().getUserAccount()==null){
            Utils.Toast("请先登录");
            getView().startActivity(new Intent(getView(), UserLoginActivity.class));
            return;
        }
        JobModel.getInstance().comment(id, content,new StatusCallback() {
            @Override
            public void success(String info) {
                Utils.Toast("评论成功");
                onRefresh();
                getView().setCommentEmpty();
            }
        });
    }

}
