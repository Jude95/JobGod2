package com.ant.jobgod.jobgod.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ant.jobgod.jobgod.R;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import nucleus.manager.Presenter;

/**
 * Created by zhuchenxi on 15/6/8.
 */
public abstract class BaseRecyclerActivity<T extends Presenter,E> extends BaseActivity<T> {
    protected EasyRecyclerView recyclerView;
    protected DataAdapter adapter;
    private RecyclerArrayAdapter.ItemView mEmptyFooter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        recyclerView = $(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapterWithProgress(adapter = new DataAdapter(this));
    }

    public void setRefreshAble(){
        recyclerView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                startRefresh();
            }
        });
    }

    public void setLoadMoreAble(){
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                BaseRecyclerActivity.this.onLoadMore();
            }
        });
    }

    public DataAdapter getAdapter(){
        return adapter;
    }

    protected void onRefresh(){}
    protected void onLoadMore(){}
    protected abstract BaseViewHolder getViewHolder(ViewGroup parent,int viewType);
    protected int getViewType(int position){return 0;}

    public void addData(E[] data){
        adapter.addAll(data);
    }


    //添加数据
    public void addDataWithRefresh(E[] data){
        adapter.clear();
        adapter.addAll(data);
        adapter.setMore(R.layout.view_more, () -> onLoadMore());
    }

    //当最后一页，手动调用
    public void stopLoadMore() {
        adapter.stopMore();
        adapter.addFooter(mEmptyFooter = new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup viewGroup) {
                return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_nomore, viewGroup, false);
            }

            @Override
            public void onBindView(View view) {

            }
        });
    }

    //可以主动调用
    public void startRefresh() {
        recyclerView.getSwipeToRefresh().setRefreshing(true);
        adapter.removeFooter(mEmptyFooter);
        adapter.stopMore();
        onRefresh();
    }


    protected class DataAdapter extends RecyclerArrayAdapter<E> {

        public DataAdapter(Context context) {
            super(context);
        }

        @Override
        public int getViewType(int position) {
            return BaseRecyclerActivity.this.getViewType(position);
        }

        @Override
        public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
            return getViewHolder(parent,viewType);
        }
    }
}
