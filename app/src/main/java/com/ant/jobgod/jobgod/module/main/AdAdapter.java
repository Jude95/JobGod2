package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.Banner;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.view.jpagerview.JPagerAdapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by alien on 2015/7/7.
 */
public class AdAdapter extends JPagerAdapter {

    @InjectView(R.id.sdvAdImg)
    SimpleDraweeView sdvAdImg;
    private Banner[] banners;
    private Context context;

    public AdAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.main_item_ad, container, false);
        ButterKnife.inject(this, view);
        sdvAdImg.setImageURI(Uri.parse(banners[position].getImg()));
        return view;
    }

    @Override
    public int getCount() {
        return banners==null?0:banners.length;
    }

    public void setData(Banner[] banners) {
        this.banners = banners;
        notifyDataSetChanged();
    }
}
