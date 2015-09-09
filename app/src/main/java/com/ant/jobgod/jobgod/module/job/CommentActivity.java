package com.ant.jobgod.jobgod.module.job;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Comment;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.beam.bijection.RequiresPresenter;
import com.jude.beam.expansion.list.BeamListActivity;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/20.
 */
@RequiresPresenter(CommentPresenter.class)
public class CommentActivity extends BeamListActivity<CommentPresenter,Comment> {


    @InjectView(R.id.content)
    EditText content;
    @InjectView(R.id.btnSubmit)
    TextView btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);

        btnSubmit.setOnClickListener(v -> {
            if (content.getText().toString().isEmpty()) {
                Utils.Toast("不能为空");
                return;
            }
            getPresenter().submitComment(content.getText().toString());
        });
    }

    @Override
    public int getLayout() {
        return R.layout.job_activity_comment;
    }

    @Override
    protected BaseViewHolder getViewHolder(ViewGroup viewGroup, int i) {
        return new CommentViewHolder(viewGroup);
    }


    public void setCommentEmpty(){
        content.setText("");
    }

}
