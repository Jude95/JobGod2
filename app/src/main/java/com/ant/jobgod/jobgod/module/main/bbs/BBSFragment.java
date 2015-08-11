package com.ant.jobgod.jobgod.module.main.bbs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.umeng.comm.core.beans.FeedItem;

import java.util.List;

import nucleus.factory.RequiresPresenter;
import nucleus.view.NucleusFragment;

/**
 * Created by Mr.Jude on 2015/7/10.
 */
@RequiresPresenter(BBSPresenter.class)
public class BBSFragment extends NucleusFragment<BBSPresenter> {

    private EasyRecyclerView recyclerView;
    private BBSAdapter adapter;
    private String nextUrl;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        adapter=new BBSAdapter(getActivity());
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bbs_frament,container,false);
        recyclerView= (EasyRecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapterWithProgress(adapter);
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                getPresenter().loadMore(nextUrl);
            }
        });
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getPresenter().setData();
            }
        });
        return view;
    }

    public void setData(List<FeedItem> data){
        if(data.size()==0){
            return;
        }
        adapter.clear();
        adapter.addAll(data);
        if(data.get(0).nextPageUrl!=null){
            nextUrl=data.get(0).nextPageUrl;
        }
    }

    public void loadMore(List<FeedItem> data){
        adapter.addAll(data);
        if(data!=null&&data.get(0)!=null){
            nextUrl=data.get(0).nextPageUrl;
        }
    }

    public void stopMore(){
        adapter.stopMore();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_bbs, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.publish){
            startActivity(new Intent(getActivity(),PublishFeedActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
