package com.ant.jobgod.jobgod.module.main.joblist;

import android.database.DataSetObserver;
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
    ViewGroup container;
    public interface onCloseListener{
        void onClose(int position);
    }

    public FiltrateAdapter(onCloseListener listener,ViewGroup container) {
        this.listener = listener;
        this.container = container;

        registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                container.removeAllViews();
                for (int i = 0 ; i < getCount() ; i++){
                    container.addView(getView(i,null,container));
                }
            }
        });
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
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item_filtrate,parent,false);
        ((TextView)root.findViewById(R.id.title)).setText(texts[position]);
        root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClose(position);
            }
        });
        return root;
    }
}
