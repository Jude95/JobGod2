package com.ant.jobgod.jobgod.module.main;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.JobBrief;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

/**
 * Created by alien on 2015/7/7.
 */
public class HotJobAdapter extends StaticPagerAdapter {

    SimpleDraweeView sdvHotJobImg;
    TextView tvTitle;
    private Context context;
    private JobBrief[] data;

    public HotJobAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        for(int i=0;i<3;i++){
            View item = inflater.inflate(R.layout.main_item_hotjob, container, false);
            item.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT,1));
            sdvHotJobImg= (SimpleDraweeView) item.findViewById(R.id.sdvHotJobImg);
            tvTitle= (TextView) item.findViewById(R.id.tvTitle);
            int index = position * 3+i;
            if (index<data.length){
                sdvHotJobImg.setImageURI(Uri.parse(data[index].getImg()));
                tvTitle.setText(data[index].getTitle());
            }
            linearLayout.addView(item);
        }
        return linearLayout;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.length % 3 == 0 ? data.length / 3 : data.length / 3 + 1;
    }

    public void setData(JobBrief[] data) {
        this.data = data;
        notifyDataSetChanged();
    }
}
