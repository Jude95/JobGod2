package com.ant.jobgod.jobgod.module.main.joblist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ant.jobgod.jobgod.R;

/**
 * Created by Mr.Jude on 2015/7/11.
 */
public class FiltrateAdapter extends BaseAdapter {
    String[] texts;
    onCloseListener listener;
    public interface onCloseListener{
        void onClose(int position);
    }

    public FiltrateAdapter(onCloseListener listener) {
        this.listener = listener;
    }

    public void setTexts(String[] texts){
        this.texts = texts;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return texts == null?0:texts.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View root = LayoutInflater.from(convertView.getContext()).inflate(R.layout.main_item_filtrate,parent,false);
        ((TextView)root.findViewById(R.id.text)).setText(texts[position]);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClose(position);
            }
        });
        return root;
    }
}
