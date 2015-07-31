package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;

import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.comm.core.beans.ImageItem;

/**
 * Created by alien on 2015/7/30.
 */
public class ImgListAdapter extends ArrayAdapter<ImageItem> {

    public ImgListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDraweeView img=new SimpleDraweeView(getContext());
        img.setLayoutParams(new AbsListView.LayoutParams(Utils.dip2px(80), Utils.dip2px(80)));
        if(getItem(position).thumbnail!=null){
            img.setImageURI(Uri.parse(getItem(position).thumbnail));
        }
        else
            img.setImageURI(Uri.parse("http://avatar.csdn.net/6/A/5/1_hitlion2008.jpg"));
        return img;
    }

}
