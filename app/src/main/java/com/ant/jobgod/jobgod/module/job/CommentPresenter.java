package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.ant.jobgod.jobgod.model.bean.CommentPage;
import com.ant.jobgod.jobgod.model.callback.DataCallback;
import com.facebook.common.internal.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentPresenter extends BasePresenter<CommentActivity> {
    private static final int PAGE_COUNT = 20;
    private String id;
    private static int page = 0;
    private List<Comment> arr=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        id = getView().getIntent().getStringExtra("id");
        refresh();
    }

    @Override
    protected void onCreateView(CommentActivity view) {
        super.onCreateView(view);
        if(arr.size()>0){
            getView().addData((Comment[]) arr.toArray());
        }
    }

    public void refresh(){
        JobModel.getInstance().getCommentList(id, 0, PAGE_COUNT, new DataCallback<CommentPage>() {
            @Override
            public void success(String info, CommentPage data) {
                arr.clear();
                if (data.getTotalCount() == 1) {
                    getView().stopLoadMore();
                }
                page = 0;
                getView().addDataWithRefresh(data.getComments());
                arr.addAll(Lists.newArrayList(data.getComments()));
            }
        });
    }

    public void loadMore(){
        JobModel.getInstance().getCommentList(id, page, PAGE_COUNT, new DataCallback<CommentPage>() {
            @Override
            public void success(String info, CommentPage data) {
                if (data.getCurPage() == page + 1) {
                    getView().addData(data.getComments());
                    arr.addAll(Lists.newArrayList(data.getComments()));
                    if ((data.getTotalCount() - 1) / PAGE_COUNT <= page) {
                        getView().stopLoadMore();
                    }
                    page++;
                } else {
                }
            }
        });
    }
}
