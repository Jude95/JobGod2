package com.ant.jobgod.jobgod.module.job;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.model.bean.Comment;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by alien on 2015/7/20.
 */
public class CommentAdapter extends RecyclerArrayAdapter<Comment> {

    private TextView textMore,textStop;
    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CommentViewHolder(viewGroup);
    }

    public void setLoadMore(OnLoadMoreListener listener){
        textMore=new TextView(getContext());
        textMore.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(32)));
        textMore.setGravity(Gravity.CENTER);
        textMore.setText("正在加载.......");
        setMore(textMore, listener);
    }

    public void setFooter(){
        textStop=new TextView(getContext());
        textStop.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(32)));
        textStop.setGravity(Gravity.CENTER);
        textStop.setText("(*-w-)没有更多了");
        addFooter((ItemView) textStop);
    }

}
