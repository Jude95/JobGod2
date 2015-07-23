package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.ant.jobgod.jobgod.model.bean.CommentPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.ant.jobgod.jobgod.model.callback.StatusCallback;
import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.common.internal.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentPresenter extends BasePresenter<CommentActivity> {
    private static final int PAGE_COUNT = 20;
    private int id;
    private static int page = 0;
    private List<Comment> arr=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        id = getView().getIntent().getIntExtra("id",0);
        refresh();
    }

    @Override
    protected void onCreateView(CommentActivity view) {
        super.onCreateView(view);
        if(arr.size()>0){
            getView().setData((Comment[]) arr.toArray());
        }
    }

    public void refresh(){
        JobModel.getInstance().getCommentList(id, 0, PAGE_COUNT, new DataCallback<CommentPage>() {
            @Override
            public void success(String info, CommentPage data) {
                arr.clear();
                if (data.getCurPage() == 0) {
                    getView().stopMore();
                }
                page = 0;
                getView().refresh(data.getComments());
                arr.addAll(Lists.newArrayList(data.getComments()));
            }
        });
    }

    public void loadMore(){
        JobModel.getInstance().getCommentList(id, page, PAGE_COUNT, new DataCallback<CommentPage>() {
            @Override
            public void success(String info, CommentPage data) {
                if (data.getCurPage() == page) {
                    getView().setData(data.getComments());
                    arr.addAll(Lists.newArrayList(data.getComments()));
                    if(data.getTotalCount()%PAGE_COUNT!=0){
                        getView().stopMore();
                    }
//                    if ((data.getTotalCount() - 1) / PAGE_COUNT <= page) {
//                        getView().stopMore();
//                    }
                    page++;
                } else {
                }
            }
        });
    }

    /**
     * 发表评论
     * @param content
     */
    public void submitComment(String content){
        JobModel.getInstance().comment(id, content,new StatusCallback() {
            @Override
            public void success(String info) {

            }

            @Override
            public void result(int status, String info) {
                super.result(status, info);
                switch (status){
                    case 200:
                        Utils.Toast("评论成功");
                        refresh();
                        getView().setCommentEmpty();
                        break;
                }
            }
        });
    }

}
