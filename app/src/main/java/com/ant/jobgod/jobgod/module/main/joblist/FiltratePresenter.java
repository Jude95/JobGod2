package com.ant.jobgod.jobgod.module.main.joblist;

import android.os.Bundle;

import com.ant.jobgod.jobgod.app.BasePresenter;
import com.ant.jobgod.jobgod.model.JobModel;
import com.ant.jobgod.jobgod.model.RegionModel;
import com.ant.jobgod.jobgod.model.bean.Region;
import com.ant.jobgod.jobgod.model.bean.Trade;

/**
 * Created by Mr.Jude on 2015/7/11.
 */
public class FiltratePresenter extends BasePresenter<FiltrateActivity> {
    private Trade[] mTrades;
    private Region[] mRegion;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        mTrades = JobModel.getInstance().getTrade();
        mRegion = new Region[]{RegionModel.getInstance().findRegionByCode(500100)};
    }

    public void onAddTrade(){
        getView().showTradeDialog(mTrades);
    }

    public void onAddCity(){
        getView().showCityDialog(mRegion[mRegion.length-1].getCid());
    }

    public void addTrade(Trade[] trades){

    }

    public void addCity(Region regions){

    }

    public void deleteTrade(int position){

    }

    public void deleteCity(int position){

    }

}
