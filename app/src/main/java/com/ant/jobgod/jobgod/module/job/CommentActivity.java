package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.jude.easyrecyclerview.EasyRecyclerView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by alien on 2015/7/20.
 */
@RequiresPresenter(CommentPresenter.class)
public class CommentActivity extends BaseActivity<CommentPresenter> {


    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.btnSubmit)
    TextView btnSubmit;
    @InjectView(R.id.recyclerview)
    EasyRecyclerView recyclerview;

    private CommentAdapter adapter = new CommentAdapter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_comment);
        ButterKnife.inject(this);

        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setRefreshListener(() -> getPresenter().refresh());
        adapter.setMore(R.layout.view_more, () -> {
            getPresenter().loadMore();
        });

        recyclerview.setAdapter(adapter);

        btnSubmit.setOnClickListener(v -> getPresenter().submitComment(content.getText().toString()));
    }


    public void setData(Comment[] data) {
        adapter.addAll(data);
    }

    public void refresh(Comment[] data) {
        adapter.clear();
        adapter.addAll(data);
    }

    public void stopMore() {
        adapter.stopMore();
    }

    public void setCommentEmpty(){
        content.setText("");
    }

}
