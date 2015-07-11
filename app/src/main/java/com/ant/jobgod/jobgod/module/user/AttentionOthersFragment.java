package com.ant.jobgod.jobgod.module.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.model.bean.PersonBrief;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

@RequiresPresenter(AttentionOthersPresenter.class)
public class AttentionOthersFragment extends NucleusFragment<AttentionOthersPresenter> {


    @InjectView(R.id.recycler)
    RecyclerView recycler;

    private UserCyclerAdapter adapter;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        adapter=new UserCyclerAdapter(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment_othersattention, container, false);
        ButterKnife.inject(this, view);
        recycler.setAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    public void setUersData(PersonBrief[] data){
        adapter.clear();
        adapter.addAll(data);
    }
}
