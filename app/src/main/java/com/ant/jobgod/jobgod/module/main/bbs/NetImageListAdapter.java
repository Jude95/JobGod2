package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.util.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.comm.core.beans.ImageItem;

import java.util.List;

/**
 * Created by alien on 2015/7/30.
 */
public class NetImageListAdapter extends ArrayAdapter<ImageItem> {

    public NetImageListAdapter(Context context,List<ImageItem> list) {
        super(context, 0);
        addAll(list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleDraweeView img = new SimpleDraweeView(getContext());
        img.setLayoutParams(new AbsListView.LayoutParams(Utils.dip2px(80), Utils.dip2px(80)));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(getItem(position).originImageUrl!=null){
            img.setImageURI(Uri.parse(getItem(position).thumbnail));
        }
        return img;
    }

}
