package com.ant.jobgod.jobgod.module.user;

import android.content.Context;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by alien on 2015/7/10.
 */
public class PersonAdapter extends RecyclerArrayAdapter<PersonBrief> {


    public PersonAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonBriefViewHolder(parent);
    }

}
