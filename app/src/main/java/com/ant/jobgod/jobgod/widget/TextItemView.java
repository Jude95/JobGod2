package com.ant.jobgod.jobgod.widget;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ant.jobgod.jobgod.util.Utils;

/**
 * Created by Mr.Jude on 2015/7/20.
 */
public class TextItemView extends TextView {

    public TextItemView(Context ctx,String text){
        super(ctx);
        setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.dip2px(48)));
        setText(text);
        setGravity(Gravity.CENTER);
    }

}
