package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.ant.jobgod.jobgod.util.Utils;
import com.jude.library.imageprovider.ImageProvider;
import com.umeng.comm.core.beans.ImageItem;

/**
 * Created by alien on 2015/7/30.
 */
public class LocalImageListAdapter extends ArrayAdapter<ImageItem> {

    public LocalImageListAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView img = new ImageView(getContext());
        img.setLayoutParams(new AbsListView.LayoutParams(Utils.dip2px(80), Utils.dip2px(80)));
        img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        if(getItem(position).originImageUrl!=null){
            img.setImageBitmap(ImageProvider.readImageWithSize(Uri.parse(getItem(position).originImageUrl), 300, 300));
        }
        return img;
    }

}
