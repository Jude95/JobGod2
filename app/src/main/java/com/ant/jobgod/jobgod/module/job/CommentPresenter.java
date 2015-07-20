package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.bean.Comment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentPresenter extends BasePresenter<CommentActivity> {

    private List<Comment> comments=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        refresh();
    }

    @Override
    protected void onCreateView(CommentActivity view) {
        super.onCreateView(view);
        if(comments.size()>0){
            getView().addData((Comment[]) comments.toArray());
        }
    }

    public void refresh(){

    }

    public void loadMore(){

    }
}
