package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.util.Utils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.umeng.comm.core.beans.Comment;

/**
 * Created by alien on 2015/8/1.
 */
public class CommentAdapter extends RecyclerArrayAdapter<Comment> {
    private boolean OPEN;

    public CommentAdapter(Context context) {
        super(context);
        OPEN=true;
    }


    @Override
    public int getViewType(int position) {
        if(position>4&&position==getCount()-1){
            return 1;
        }
        else
            return 0;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        if(i==0)
            return new CommentViewHolder(viewGroup);
        else{
            TextView textView=new TextView(getContext());
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setText("点击展开");
            return new ShinkViewHolder(textView);
        }
    }

    class CommentViewHolder extends BaseViewHolder<Comment>{

        private TextView comment;
        private TextView name;

        public CommentViewHolder(ViewGroup viewGroup) {
            super(viewGroup,R.layout.bbs_item_comment);
            name= (TextView) itemView.findViewById(R.id.name);
            comment= (TextView) itemView.findViewById(R.id.content);
        }

        @Override
        public void setData(Comment data) {
            super.setData(data);
            comment.setText(data.text);
            String str= Utils.base64ToString(data.creator.name);
            String[] arry=str.split("_",2);
            if(arry.length==1){
                name.setText(arry[0]);
            }else
                name.setText(arry[1]);
        }
    }

    class ShinkViewHolder extends BaseViewHolder{
        public ShinkViewHolder(View itemview) {
            super(itemview);
        }
    }
}
