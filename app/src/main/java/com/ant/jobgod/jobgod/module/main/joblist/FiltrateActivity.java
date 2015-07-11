package com.ant.jobgod.jobgod.module.main.joblist;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.ant.jobgod.jobgod.R;
import com.ant.jobgod.jobgod.app.BaseActivity;
import com.ant.jobgod.jobgod.model.bean.Region;
import com.ant.jobgod.jobgod.model.bean.Trade;

import butterknife.ButterKnife;
import butterknife.InjectView;
import nucleus.factory.RequiresPresenter;

/**
 * Created by Mr.Jude on 2015/7/11.
 */
@RequiresPresenter(FiltratePresenter.class)
public class FiltrateActivity extends BaseActivity<FiltratePresenter> {
    @InjectView(R.id.img_trade_add)
    ImageView imgTradeAdd;
    @InjectView(R.id.grid_trade)
    GridView gridTrade;
    @InjectView(R.id.img_city_add)
    ImageView imgCityAdd;
    @InjectView(R.id.grid_city)
    GridView gridCity;
    @InjectView(R.id.tv_sort)
    TextView tvSort;

    private FiltrateAdapter mTradeAdapter;
    private FiltrateAdapter mCityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_filtrate);
        ButterKnife.inject(this);
        gridTrade.setAdapter(mTradeAdapter = new FiltrateAdapter(position -> getPresenter().deleteTrade(position)));
        gridCity.setAdapter(mCityAdapter = new FiltrateAdapter(position -> getPresenter().deleteCity(position)));
        imgTradeAdd.setOnClickListener(v -> getPresenter().onAddTrade());
        imgCityAdd.setOnClickListener(v->getPresenter().onAddCity());
    }

    private void setTrade(Trade[] trades){
        String[] texts = new String[trades.length];
        for (int i = 0; i < trades.length ; i++){
            texts[i] = trades[i].getName();
        }
        mTradeAdapter.setTexts(texts);
    }

    private void setCity(Region[] regions){
        String[] texts = new String[regions.length];
        for (int i = 0; i < regions.length ; i++){
            texts[i] = regions[i].getName();
        }
        mCityAdapter.setTexts(texts);
    }

    public void showTradeDialog(final Trade[] trades){
        String[] texts = new String[trades.length];
        for (int i = 0; i < trades.length ; i++){
            texts[i] = trades[i].getName();
        }
        new MaterialDialog.Builder(this)
                .title("选择感兴趣的行业")
                .items(texts)
                .itemsCallbackMultiChoice(null, new MaterialDialog.ListCallbackMultiChoice() {
                    @Override
                    public boolean onSelection(MaterialDialog materialDialog, Integer[] integers, CharSequence[] charSequences) {
                        Trade[] add = new Trade[integers.length];
                        for (int i = 0; i < integers.length; i++) {
                            add[i] = trades[integers[i]];
                        }
                        getPresenter().addTrade(add);
                        return false;
                    }
                })
                .show();
    }

    public void showCityDialog(int laseRegionCode){
        RegionView view = new RegionView(this, new RegionView.RegionSelectCallback() {
            @Override
            public void selected(Region region) {
                getPresenter().addCity(region);
            }
        }, laseRegionCode);
        new MaterialDialog.Builder(this)
                .title("选择感兴趣的地区")
                .customView(view,false)
                .show();

    }



}
