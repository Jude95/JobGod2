package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;
import com.ant.jobgod.jobgod.widget.TextItemView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

@RequiresPresenter(AttentionToMePresenter.class)
public class AttentionToMeFragment extends NucleusFragment<AttentionToMePresenter> {

    @InjectView(R.id.recycler)
    RecyclerView recycler;

    private PersonAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter=new PersonAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_attention_tome, container,false);
        ButterKnife.inject(this, view);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        recycler.setAdapter(adapter);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setUsersData(PersonBrief[] data){
        adapter.clear();
        adapter.addAll(data);
    }

    public void setNull(){
        adapter.addFooter(new TextItemView("还没有人关注可怜的你~"));
        adapter.notifyDataSetChanged();
    }
}
