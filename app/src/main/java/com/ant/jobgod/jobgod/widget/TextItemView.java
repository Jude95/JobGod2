package com.ant.jobgod.jobgod.widget;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.util.Utils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Mr.Jude on 2015/7/20.
 */
public class TextItemView implements RecyclerArrayAdapter.ItemView {
    private String text;
    public TextItemView(String text){
        this.text = text;
    }

    @Override
    public View onCreateView(ViewGroup viewGroup) {
        TextView textView = new TextView(viewGroup.getContext());
        textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(48)));
        textView.setText(text);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    @Override
    public void onBindView(View view) {

    }
}
