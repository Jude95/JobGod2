package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

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


    @InjectView(R.id.recyclerview)
    EasyRecyclerView recyclerview;
    //    @InjectView(R.id.content)
//    EditText content;
//    @InjectView(R.id.btnSubmit)
//    TextView btnSubmit;
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.job_activity_comment);
        ButterKnife.inject(this);
        adapter = new CommentAdapter(this);

        recyclerview.setRefreshListener(() -> {
            getSwipeBackLayout().setEnabled(false);
            recyclerview.getSwipeToRefresh().setRefreshing(true);
            getPresenter().refresh();
        });
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
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

}
