package com.ant.jobgod.jobgod.module.main.joblist;

import android.app.Activity;
import android.os.Bundle;

import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.LocationModel;
import com.ant.jobgod.jobgod.model.bean.Region;
import com.ant.jobgod.jobgod.model.bean.Trade;
import com.facebook.common.internal.Lists;
import com.jude.beam.bijection.Presenter;

import java.util.ArrayList;

/**
 * Created by Mr.Jude on 2015/7/11.
 */
public class FiltratePresenter extends Presenter<FiltrateActivity> {
    private ArrayList<Trade> mTrades;
    private ArrayList<Region> mRegion;
    private int mSort;

    private boolean changed =false;

    public static final String[] SORT_NAME = {
            "最近发布","关注最多"
    };

    @Override
    protected void onCreate(FiltrateActivity view,Bundle savedState) {
        super.onCreate(view,savedState);
        mTrades = Lists.newArrayList(JobModel.getInstance().getFiltrateTrade());
        mRegion = Lists.newArrayList(JobModel.getInstance().getFiltrateRegion());
        mSort = JobModel.getInstance().getFiltrateSort();
    }

    @Override
    protected void onCreateView(FiltrateActivity view) {
        super.onCreateView(view);
        getView().setTrade(mTrades.toArray(new Trade[0]));
        getView().setCity(mRegion.toArray(new Region[0]));
        getView().setSort(SORT_NAME[mSort]);
    }

    public void beginAddTrade(){
        Trade[] trades = JobModel.getInstance().getTrade();
        Trade[] lastTrades = new Trade[trades.length-mTrades.size()];
        out:for (int i = 0,count = 0 ; i < trades.length ; i++ ){
            for (Trade t:mTrades){
                if (t.getId() == trades[i].getId())continue out;
            }
            lastTrades[count] = trades[i];
            count++;
        }
        getView().showTradeDialog(lastTrades);
    }

    public void beginAddCity(){
        if (mRegion.size() == 0)getView().showCityDialog(LocationModel.getInstance().getCurLocation().getRegionCode());
        else getView().showCityDialog(mRegion.get(mRegion.size()-1).getCid());
    }

    public void beginSetSort(){
        getView().showSortDialog(SORT_NAME);
    }

    public void finishSetSort(int index){
        if (mSort!=index){
            mSort = index;
            changed = true;
            getView().setSort(SORT_NAME[mSort]);
        }
    }

    public void finishAddTrade(Trade[] trades){
        if (trades.length!=0){
            mTrades.addAll(Lists.newArrayList(trades));
            getView().setTrade(mTrades.toArray(new Trade[0]));
            changed = true;
        }
    }

    public void finishAddCity(Region regions){
        for (Region r:mRegion) {
            if (r.equals(regions)){
                return;
            }
        }
        mRegion.add(regions);
        getView().setCity(mRegion.toArray(new Region[0]));
        changed = true;
    }

    public void deleteTrade(int position){
        mTrades.remove(position);
        getView().setTrade(mTrades.toArray(new Trade[0]));
        changed = true;
    }

    public void deleteCity(int position){
        mRegion.remove(position);
        getView().setCity(mRegion.toArray(new Region[0]));
        changed = true;
    }

    public void saveAndExit(){
        if (changed){
            JobModel.getInstance().saveFiltrateSort(mSort);
            JobModel.getInstance().saveFiltrateRegion(mRegion.toArray(new Region[0]));
            JobModel.getInstance().saveFiltrateTrade(mTrades.toArray(new Trade[0]));
            getView().setResult(Activity.RESULT_OK);
        }
        getView().finish();
    }

}
